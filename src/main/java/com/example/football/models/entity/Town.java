package com.example.football.models.entity;


//        •	id – accepts integer values, a primary identification field, an auto incremented field.
//        •	name – accepts char sequences as values where their character length value higher than or equal to 2.
//        The values are unique in the database.
//
//        •	population – accepts number values (must be a positive number), 0 as a value is exclusive.
//        •	travel guide – a long and detailed description of all known places with a character length value higher than or equal to 10.

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="towns")
public class Town extends BaseEntity{

    private String name;
    private int population;
    private String travelGuide; //text

    public Town(String name, int population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    public Town() {
    }

    @Column(name="name",unique = true)
    public String getName() {
        return name;
    }

    @Column(name="population")
    public int getPopulation() {
        return population;
    }

    @Column(name="travel_guide",columnDefinition = "TEXT")
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
