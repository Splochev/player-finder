package com.example.football.service.impl;

import com.example.football.models.dto.RootStatSeedDto;
import com.example.football.models.dto.StatSeedDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    public static final String STATS_FOLDER_ROOT = "src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final XmlProcessor xmlProcessor;

    public StatServiceImpl(ValidationUtil validation, ModelMapper modelMapper, StatRepository statRepository, XmlProcessor xmlProcessor) {
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.statRepository = statRepository;
        this.xmlProcessor = xmlProcessor;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_FOLDER_ROOT));
    }

    @Override
    public String importStats() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<StatSeedDto> statDtos = xmlProcessor.deserialize(readStatsFileContent(), RootStatSeedDto.class).getStats();

        statDtos
                .stream()
                .filter(stat -> {
                    boolean valid = validation.isValid(stat) && !statExists(stat.getPassing(), stat.getShooting(), stat.getEndurance());
                    if (valid) {
                        sb.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f", stat.getPassing(), stat.getShooting(), stat.getEndurance()));
                    } else {
                        sb.append("Invalid Stat");
                    }
                    sb.append(System.getProperty("line.separator"));
                    return valid;
                })
                .map(stat -> modelMapper.map(stat, Stat.class))
                .forEach(this.statRepository::save);

        return sb.toString();
    }

    @Override
    public Stat findStatById(long id) {
        return this.statRepository.findById(id).orElse(null);
    }


    private boolean statExists(double passing, double shooting, double endurance) {
        return this.statRepository.existsByPassingAndShootingAndEndurance(passing, shooting, endurance);
    }
}
