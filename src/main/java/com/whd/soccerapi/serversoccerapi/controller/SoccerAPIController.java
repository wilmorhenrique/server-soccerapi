package com.whd.soccerapi.serversoccerapi.controller;

import com.whd.soccerapi.serversoccerapi.model.EventResultDTO;
import com.whd.soccerapi.serversoccerapi.model.Team;
import com.whd.soccerapi.serversoccerapi.repository.TeamRepository;
import com.whd.soccerapi.serversoccerapi.service.EventResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController("/")
public class SoccerAPIController {

    @Autowired
    EventResultService eventResultService;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public String imAlive() {
        log.info("entrei e vai esperar 10 Segundos");
        try {

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "I'm alive!!!";
    }



    @GetMapping("/event-results")
    public Mono<ResponseEntity<List<EventResultDTO>>> getEventResultsByLeagueAndSeason(
            @RequestParam("leagueId") Long leagueId, @RequestParam("seasonId") Long seasonId) {

        Mono<List<EventResultDTO>> eventResultsMono = Mono.fromSupplier(() -> eventResultService.getAllEventsFromLeagueAndSeason(leagueId, seasonId));

        Mono<ResponseEntity<List<EventResultDTO>>> responseEntityMono = eventResultsMono
                .map(eventResults -> ResponseEntity.ok(eventResults))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

        return responseEntityMono;

    }

    @GetMapping("/leggue-season-stats")
    public String  getStatsByLeagueAndSeason(
            @RequestParam("leagueId") Long leagueId, @RequestParam("seasonId") Long seasonId) {

        return eventResultService.getStatsByLeagueAndSeson(leagueId, seasonId);
    }

    @GetMapping("/team-all-time-stats")
    public String  getStatsByLeagueAndSeason(
            @RequestParam("teamId") Long teamId) {

        return eventResultService.getStatsByTeam(teamId);
    }

    @GetMapping("/list-teams")
    public List<Team> listTeams() {
        return teamRepository.findAll();
    }



}
