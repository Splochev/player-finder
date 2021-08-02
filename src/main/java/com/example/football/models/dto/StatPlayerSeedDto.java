package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatPlayerSeedDto {

    @XmlElement(name="id")
    private long id;

    public StatPlayerSeedDto(long id) {
        this.id = id;
    }

    public StatPlayerSeedDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
