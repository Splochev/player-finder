package com.example.football.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {

    @XmlElement(name="shooting")
    private double shooting;

    @XmlElement(name="passing")
    private double passing;

    @XmlElement(name="endurance")
    private double endurance;

    public StatSeedDto() {
    }

    public StatSeedDto(double shooting, double passing, double endurance) {
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    @Positive
    public double getShooting() {
        return shooting;
    }

    @Positive
    public double getPassing() {
        return passing;
    }

    @Positive
    public double getEndurance() {
        return endurance;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public void setPassing(double passing) {
        this.passing = passing;
    }

    public void setEndurance(double endurance) {
        this.endurance = endurance;
    }
}

