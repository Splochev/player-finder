package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="players")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootPlayerSeedDto {

    @XmlElement(name="player")
    List<PlayerSeedDto> players;

    public RootPlayerSeedDto() {
    }

    public RootPlayerSeedDto(List<PlayerSeedDto> players) {
        this.players = players;
    }

    public List<PlayerSeedDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerSeedDto> players) {
        this.players = players;
    }
}
