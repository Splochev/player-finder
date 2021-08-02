package com.example.football.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownSeedDto {
    @Expose
    private String name;

    @Expose
    private int population;

    @Expose
    private String travelGuide;

    public TownSeedDto(String name, int population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    public TownSeedDto() {
    }

    @Size(min=2)
    public String getName() {
        return name;
    }

    @Positive
    public int getPopulation() {
        return population;
    }

    @Size(min = 10)
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
