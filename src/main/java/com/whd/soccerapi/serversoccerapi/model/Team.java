package com.whd.soccerapi.serversoccerapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
public class Team {

    public Team(Long id, Long idTeam, Long idAPIfootball, String strTeam, String strTeamShort, String strAlternate, Long intFormedYear, String strSport) {
        this.id = id;
        this.idTeam = idTeam;
        this.idAPIfootball = idAPIfootball;
        this.teamName = strTeam;
        this.teamShortName = strTeamShort;
        this.alternateName = strAlternate;
        this.formedYear = intFormedYear;
        this.sport = strSport;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idTeam;
    private Long idAPIfootball;
    private String teamName;
    private String teamShortName;
    private String alternateName;
    private Long formedYear;
    private String sport;
}
