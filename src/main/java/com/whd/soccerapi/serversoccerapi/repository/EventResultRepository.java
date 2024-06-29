package com.whd.soccerapi.serversoccerapi.repository;

import com.whd.soccerapi.serversoccerapi.model.EventResultDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventResultRepository {

    @Autowired
    private EntityManager entityManager;

    public List<EventResultDTO> findEventResultsByLeagueAndSeason(Long leagueId, Long seasonId) {
        String jpql = "SELECT new com.whd.soccerapi.serversoccerapi.model.EventResultDTO(" +
                "homeTeam.teamName,  awayTeam.teamName, e.homeScore, e.awayScore, e.spectators, e.dateEvent, league.strLeague, season.seasonName, league.id, season.id) " +
                "FROM FootballEvent e " +
                "JOIN e.homeTeam homeTeam " +
                "JOIN e.awayTeam awayTeam " +
                "JOIN e.league league " +
                "JOIN e.season season " +
                "WHERE league.id = :leagueId AND season.id = :seasonId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("leagueId", leagueId);
        query.setParameter("seasonId", seasonId);

        return query.getResultList();
    }

    public List<EventResultDTO> findEventsByTeamAtHome(Long teamId) {
        String jpql = "SELECT new com.whd.soccerapi.serversoccerapi.model.EventResultDTO(" +
                "homeTeam.teamName,  awayTeam.teamName, e.homeScore, e.awayScore, e.spectators, e.dateEvent, league.strLeague, season.seasonName, league.id, season.id) " +
                "FROM FootballEvent e " +
                "JOIN e.homeTeam homeTeam " +
                "JOIN e.awayTeam awayTeam " +
                "JOIN e.league league " +
                "JOIN e.season season " +
                "WHERE homeTeam.id = :teamId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("teamId", teamId);

        return query.getResultList();
    }

    public List<EventResultDTO> findEventsByTeamAway(Long teamId) {
        String jpql = "SELECT new com.whd.soccerapi.serversoccerapi.model.EventResultDTO(" +
                "homeTeam.teamName,  awayTeam.teamName, e.homeScore, e.awayScore, e.spectators, e.dateEvent, league.strLeague, season.seasonName, league.id, season.id) " +
                "FROM FootballEvent e " +
                "JOIN e.homeTeam homeTeam " +
                "JOIN e.awayTeam awayTeam " +
                "JOIN e.league league " +
                "JOIN e.season season " +
                "WHERE awayTeam.id = :teamId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("teamId", teamId);

        return query.getResultList();
    }


}
