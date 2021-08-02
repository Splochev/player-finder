package com.example.football.models.entity;


import com.example.football.models.enums.Position;

import javax.persistence.*;
import java.time.LocalDate;

//•	id – accepts integer values, a primary identification field, an auto incremented field.
//        •	first name – accepts char sequences as values where their character length value higher than 2.
//        •	last name – accepts char sequences as values where their character length value higher than 2.
//        •	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
//        •	birth date – a date in the "dd/MM/yyyy" format.
//        •	position – one of the following – ATT, MID, DEF.
//        o	Note: The players table has relations with the towns, teams and stats tables.
//
//        NOTES:
//        •	Name the entities and their class members exactly in the format stated above.
//        •	All fields are NOT NULL unless explicitly stated to be nullable.


@Entity
@Table(name="players")
public class Player  extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private Position position;
    private Stat stat; //manyToOne
    private Team team; //manyToOne
    private Town town; //manyToOne

    public Player() {
    }

    public Player(String firstName, String lastName, String email, LocalDate birthDate, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
    }

    @ManyToOne
    public Stat getStat() {
        return stat;
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    @ManyToOne
    public Town getTown() {
        return town;
    }

    @Column(name="first_dame")
    public String getFirstName() {
        return firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name="email",unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name="birth_date")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Column(name="position")
    @Enumerated(EnumType.ORDINAL)
    public Position getPosition() {
        return position;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
