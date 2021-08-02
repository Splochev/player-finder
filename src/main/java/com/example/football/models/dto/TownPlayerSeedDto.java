package com.example.football.models.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownPlayerSeedDto {
    @XmlElement(name="name")
    private String name;

    public TownPlayerSeedDto(String name) {

        this.name = name;
    }

    public TownPlayerSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
