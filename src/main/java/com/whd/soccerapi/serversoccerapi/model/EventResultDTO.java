package com.whd.soccerapi.serversoccerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class EventResultDTO  implements Comparable<EventResultDTO> {

    private String homeTeam;
    private String awayTeam;
    private Integer  homescore;
    private Integer awayScore;
    private Integer spectators;
    private LocalDate eventDate;
    private String league;
    private String season;
    private Long leagueId;
    private Long seasonId;

    @Override
    public int compareTo(EventResultDTO other) {
        return this.getOrderKey().compareTo(other.getOrderKey());
    }


    public String getOrderKey() {
        return getKey() + this.eventDate.format(DateTimeFormatter.BASIC_ISO_DATE) ;
    }

    public String printEvent() {
        StringJoiner joiner = new StringJoiner( "\n");
        joiner.add(this.league + " - " + season);
        joiner.add(this.homeTeam + " " + homescore + " x " + awayScore  + " " + awayTeam);
        joiner.add("Spectators: " + this.spectators);
        joiner.add("Date:" + this.eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        joiner.add("Ids: " + this.leagueId + " - " + seasonId);
        return joiner.toString();
    }

    public String printEventShort() {
        StringJoiner joiner = new StringJoiner( "\n");
        joiner.add("Date:" + this.eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " - " + this.homeTeam + " " + homescore + " x " + awayScore  + " " + awayTeam);
        return joiner.toString();
    }

    public String getKey() {
        return this.getLeague() + "-" + getSeason() ;
    }
}
