package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamPlayerSeedDto {
    @XmlElement(name="name")
    private String name;

    public TeamPlayerSeedDto(String name) {
        this.name = name;
    }

    public TeamPlayerSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
