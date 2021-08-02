package com.example.football.service.impl;

import com.example.football.models.dto.TownSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
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
public class TownServiceImpl implements TownService {
    private final Gson gson;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    public static final String TOWNS_FOLDER_ROOT = "src/main/resources/files/json/towns.json";

    public TownServiceImpl(Gson gson, ValidationUtil validation, ModelMapper modelMapper, TownRepository townRepository) {
        this.gson = gson;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FOLDER_ROOT));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        TownSeedDto[] townSeedDtos = this.gson.fromJson(readTownsFileContent(), TownSeedDto[].class);

        Arrays.stream(townSeedDtos)
                .filter(town -> {
                    boolean valid = validation.isValid(town) && !townExists(town.getName());
                    if (valid) {
                        sb.append(String.format("Successfully imported Town %s - %d",town.getName(),town.getPopulation()));
                    } else {
                        sb.append("Invalid Town");
                    }
                    sb.append(System.getProperty("line.separator"));
                    return valid;
                })
                .map(town -> modelMapper.map(town, Town.class))
                .forEach(this.townRepository::save);
        System.out.println(sb);
        return sb.toString();
    }

    @Override
    public Town findTownByName(String name) {
        return this.townRepository.findByName(name);
    }

    private boolean townExists(String town){
        return this.townRepository.existsByName(town);
    }
}
