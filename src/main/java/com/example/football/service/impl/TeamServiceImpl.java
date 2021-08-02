package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.dto.TownSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {
    private final Gson gson;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    public static final String TEAMS_FOLDER_ROOT = "src/main/resources/files/json/teams.json";
    private final TeamRepository teamRepository;
    private final TownService townService;

    public TeamServiceImpl(Gson gson, ValidationUtil validation, ModelMapper modelMapper, TeamRepository teamRepository, TownService townService) {
        this.gson = gson;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_FOLDER_ROOT));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();
        TeamSeedDto[] teamSeedDtos = this.gson.fromJson(readTeamsFileContent(), TeamSeedDto[].class);

        Arrays.stream(teamSeedDtos)
                .filter(team -> {
                    boolean valid = validation.isValid(team) && !teamExists(team.getName());
                    if (valid) {
                        sb.append(String.format("Successfully imported Team %s - %d", team.getName(), team.getFanBase()));
                    } else {
                        sb.append("Invalid Team");
                    }
                    sb.append(System.getProperty("line.separator"));
                    return valid;
                })
                .map(team -> {
                    Team mappedTeam = modelMapper.map(team, Team.class);
                    mappedTeam.setTown(this.townService.findTownByName(team.getName()));
                    return mappedTeam;
                })
                .forEach(this.teamRepository::save);
        System.out.println(sb);
        return sb.toString();
    }

    @Override
    public Team findTeamByName(String teamName) {
        return this.teamRepository.findByName(teamName);
    }

    private boolean teamExists(String teamName) {
        return this.teamRepository.existsByName(teamName);
    }
}
