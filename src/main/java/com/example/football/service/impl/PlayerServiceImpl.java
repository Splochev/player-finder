package com.example.football.service.impl;

import com.example.football.models.dto.RootPlayerSeedDto;
import com.example.football.models.entity.Player;
import com.example.football.models.enums.Position;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlProcessor;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    public static final String PLAYER_FOLDER_ROOT = "src/main/resources/files/xml/players.xml";
    private final PlayerRepository playerRepository;
    private final XmlProcessor xmlProcessor;
    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(ValidationUtil validation, ModelMapper modelMapper, PlayerRepository playerRepository, XmlProcessor xmlProcessor, TownService townService, TeamService teamService, StatService statService) {
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
        this.xmlProcessor = xmlProcessor;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count()>0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYER_FOLDER_ROOT));
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();
        RootPlayerSeedDto rootPlayerSeedDto = xmlProcessor.deserialize(readPlayersFileContent(), RootPlayerSeedDto.class);

        rootPlayerSeedDto.getPlayers()
                .stream()
                .filter(player -> {
                    boolean valid = validation.isValid(player) && !emailExists(player.getEmail());
                    if (valid) {
                        sb.append(String.format("Successfully imported Player %s %s - %s",player.getFirstName(),player.getLastName(),player.getPosition().toString()));
                    } else {
                        sb.append("Invalid Player");
                    }
                    sb.append(System.getProperty("line.separator"));
                    return valid;
                })
                .map(player -> {
                    Player mappedPlayer = modelMapper.map(player, Player.class);
                    mappedPlayer.setStat(this.statService.findStatById(player.getStat().getId()));
                    mappedPlayer.setTeam(this.teamService.findTeamByName(player.getTeam().getName()));
                    mappedPlayer.setTown(this.townService.findTownByName(player.getTown().getName()));


                    return mappedPlayer;
                })
                .forEach(this.playerRepository::save);
        System.out.println(sb);
        return sb.toString();
    }

  private boolean emailExists(String email){
        return this.playerRepository.existsByEmail(email);
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        LocalDate afterDate = LocalDate.parse("01-01-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate beforeDate = LocalDate.parse("01-01-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Object[]> players = this.playerRepository.getPlayers(afterDate,beforeDate);
        for (Object[] player : players) {
            String firstName= (String) player[0];
            String lastName = (String) player[1];
            Position position = (Position) player[2];

            String teamName = (String) player[3];
            String stadium = (String) player[4];
            sb.append(String.format("Player - %s %s%n",firstName,lastName));
            sb.append("\t");
            sb.append(String.format("Position - %s%n",position.toString()));
            sb.append("\t");
            sb.append(String.format("Team - %s%n",teamName));
            sb.append("\t");
            sb.append(String.format("Stadium - %s%n",stadium));
        }
        return sb.toString();
    }
}
