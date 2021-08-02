package com.example.football.models.dto;

import com.example.football.config.LocalDateAdapter;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.models.enums.Position;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {

    @XmlElement(name="first-name")
    private String firstName;

    @XmlElement(name="last-name")
    private String lastName;

    @XmlElement(name="email")
    private String email;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name="birth-date")
    private LocalDate birthDate;

    @XmlElement(name="position")
    private Position position;

    @XmlElement(name="stat")
    private StatPlayerSeedDto stat;

    @XmlElement(name="team")
    private TeamPlayerSeedDto team;

    @XmlElement(name = "town")
    private TownPlayerSeedDto town;

    public PlayerSeedDto() {
    }

    public PlayerSeedDto(String firstName, String lastName, String email, LocalDate birthDate, Position position, StatPlayerSeedDto stat, TeamPlayerSeedDto team, TownPlayerSeedDto town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
        this.stat = stat;
        this.team = team;
        this.town = town;
    }

    @Size(min=2)
    public String getFirstName() {
        return firstName;
    }

    @Size(min=2)
    public String getLastName() {
        return lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

//    @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]\\d{4}$")
    public LocalDate getBirthDate() {
        return birthDate;
    }

//    @Pattern(regexp = "ATT|MID|DEF")
    public Position getPosition() {
        return position;
    }

    public StatPlayerSeedDto getStat() {
        return stat;
    }

    public TeamPlayerSeedDto getTeam() {
        return team;
    }

    public TownPlayerSeedDto getTown() {
        return town;
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

    public void setStat(StatPlayerSeedDto stat) {
        this.stat = stat;
    }

    public void setTeam(TeamPlayerSeedDto team) {
        this.team = team;
    }

    public void setTown(TownPlayerSeedDto town) {
        this.town = town;
    }
}
