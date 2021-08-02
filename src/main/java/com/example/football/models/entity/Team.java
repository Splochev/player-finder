package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//•	id – accepts integer values, a primary identification field, an auto incremented field.
//        •	name – accepts char sequences as values where their character length value higher than or equal to 3.
//        The values are unique in the database.

//        •	stadium name – accepts char sequences as values where their character length value higher than or equal to 3.
//        •	fan base – accepts number values that are more than or equal to 1000.
//        •	history – a long and detailed description of team's history with a character length value higher than or equal to 10.
//        o	Note: The teams table has relation with the towns table.
//

@Entity
@Table(name="teams")
public class Team extends BaseEntity{
    private String name;
    private String stadiumName;
    private int fanBase;
    private String history;
    private Town town; //ManyToOne

    public Team(String name, String stadiumName, int fanBase, String history, Town town) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
        this.town = town;
    }

    public Team() {
    }

    @ManyToOne
    public Town getTown() {
        return town;
    }

    @Column(name="name",unique = true)
    public String getName() {
        return name;
    }

    @Column(name="stadium_name")
    public String getStadiumName() {
        return stadiumName;
    }

    @Column(name="fanBase")
    public int getFanBase() {
        return fanBase;
    }

    @Column(name="history",columnDefinition = "TEXT")
    public String getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
