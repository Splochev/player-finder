package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootStatSeedDto {

    @XmlElement(name="stat")
    List<StatSeedDto> stats;

    public RootStatSeedDto() {
    }

    public RootStatSeedDto(List<StatSeedDto> stats) {
        this.stats = stats;
    }

    public List<StatSeedDto> getStats() {
        return stats;
    }

    public void setStats(List<StatSeedDto> stats) {
        this.stats = stats;
    }
}
