package com.whd.soccerapi.serversoccerapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEvent;
    private String eventName;
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLeague")
    private League league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeason")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHomeTeam")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAwayTeam")
    private Team awayTeam;

    private Integer   homeScore;
    private Integer round;
    private Integer awayScore;
    private Integer spectators;
    private LocalDate dateEvent;
    private LocalTime timeEvent;

}
