package com.example.football.models.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//•	id – accepts integer values, a primary identification field, an auto incremented field.
//        •	shooting – a floating point number. The value must be positive (larger than 0).
//        •	passing – a floating point number. The value must be positive (larger than 0).
//        •	endurance – a floating point number. The value must be positive (larger than 0).
//


@Entity
@Table(name="stats")
public class Stat extends BaseEntity {
    private double shooting;
    private double passing;
    private double endurance;


    public Stat(double shooting, double passing, double endurance) {
        this.shooting = shooting;
        this.passing = passing;
        this.endurance = endurance;
    }

    public Stat() {
    }

    @Column(name="shooting",columnDefinition = "FLOAT")
    public double getShooting() {
        return shooting;
    }

    @Column(name="passing",columnDefinition = "FLOAT")
    public double getPassing() {
        return passing;
    }

    @Column(name="endurance",columnDefinition = "FLOAT")
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
