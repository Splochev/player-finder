package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    boolean existsByEmail(String email);


    @Query("select p.firstName,p.lastName,p.position,t.name,t.stadiumName from Player p join p.team t join p.stat s where p.birthDate>:afterDate and p.birthDate<:beforeDate order by s.shooting desc,s.passing desc,s.endurance desc,p.lastName")
    List<Object[]> getPlayers(@Param(value = "afterDate")LocalDate afterDate,@Param(value="beforeDate") LocalDate beforeDate);

}
