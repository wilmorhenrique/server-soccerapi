package com.whd.soccerapi.serversoccerapi.service;

import com.whd.soccerapi.serversoccerapi.model.EventResultDTO;
import com.whd.soccerapi.serversoccerapi.repository.EventResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventResultService {

    @Autowired
    EventResultRepository repository;

    public List<EventResultDTO> getAllEventsFromLeagueAndSeason(Long leagueId, Long seasonId) {
        List<EventResultDTO> list = repository.findEventResultsByLeagueAndSeason(leagueId, seasonId);
        return list;
    }

    public String  getStatsByLeagueAndSeson(Long leagueId, Long seasonId) {
        List<EventResultDTO> list = this.getAllEventsFromLeagueAndSeason(leagueId, seasonId);
        return listDataFromLeagueAndSeason(list);
    }

    private String  listDataFromLeagueAndSeason(List<EventResultDTO> list) {
        StringJoiner joiner = new StringJoiner( "\n");

        EventResultDTO event = list.get(0);

        joiner.add(String.join("", "\n\nLeague:", event.getLeague()," - Season:", event.getSeason(), "\n"));

        joiner.add(String.join("", "Total of Events:", String.valueOf(list.size())));


        printBiggestPublic(list, joiner);

        printSmallerPublic(list, joiner);

        printBiggestHomeScore(list, joiner);

        printBiggetsAwayScore(list, joiner);

        return joiner.toString();
    }

    public String  getStatsByTeam(Long teamId) {
        StringJoiner joiner = new StringJoiner( "\n");

        List<EventResultDTO> homeEvents = repository.findEventsByTeamAtHome(teamId);
        List<EventResultDTO> awayEvents = repository.findEventsByTeamAway(teamId);
        List<EventResultDTO> list = new ArrayList<>(homeEvents);
        list.addAll(awayEvents);

        joiner.add(String.join("", "Total of Events:", String.valueOf(list.size())));
        joiner.add(String.join("", "Total of Events at Home :", String.valueOf(homeEvents.size())));
        joiner.add(String.join("", "Total of Events away:", String.valueOf(awayEvents.size())));

        printBiggestPublic(list, joiner);

        printSmallerPublic(list, joiner);

        // more goas scored at Home
        printBiggestHomeScore(homeEvents, joiner);

        // more goals scored away
        printBiggetsAwayScore(awayEvents, joiner);

       List<EventResultDTO> sortedList = list.stream()
                .sorted(Comparator.comparing(EventResultDTO::getOrderKey))
                .collect(Collectors.toCollection(LinkedList::new));

// Criar o mapa agrupando os elementos classificados pela chave
        Map<String, List<EventResultDTO>> map = sortedList.stream()
                .collect(Collectors.groupingBy(EventResultDTO::getKey));


        map.forEach((key, value) -> {
            joiner.add(String.join("", "\n\nLeague:", value.get(0).getLeague()," - Season:", value.get(0).getSeason(), "\n"));
            joiner.add(String.join("", "Total of Events:", String.valueOf(value.size())));
            value.forEach(event -> {
                joiner.add(event.printEventShort());
            });
        });

        return joiner.toString();
    }


    private static void printBiggetsAwayScore(List<EventResultDTO> list, StringJoiner joiner) {
        Optional<EventResultDTO> biggestAwayScore = list.stream().max((o1, o2) -> o1.getAwayScore().compareTo(o2.getAwayScore()));
        if (biggestAwayScore.isPresent()) {
            joiner.add("\nMore gols scorered by Away team");
            joiner.add(biggestAwayScore.get().printEventShort());
        }
    }

    private static void printBiggestHomeScore(List<EventResultDTO> list, StringJoiner joiner) {
        Optional<EventResultDTO> biggestHomeScore = list.stream().max((o1, o2) -> o1.getHomescore().compareTo(o2.getHomescore()));
        if (biggestHomeScore.isPresent()) {
            joiner.add("\nMore gols scorered by Home team");
            joiner.add(biggestHomeScore.get().printEventShort());
        }
    }

    private static void printSmallerPublic(List<EventResultDTO> list, StringJoiner joiner) {
        Optional<EventResultDTO> smallerPublic = list.stream().min((o1, o2) -> o1.getSpectators().compareTo(o2.getSpectators()));;
        if (smallerPublic.isPresent()) {
            joiner.add("\nSmaller Public");
            joiner.add(smallerPublic.get().printEvent());
        }
    }

    private static void printBiggestPublic(List<EventResultDTO> list, StringJoiner joiner) {
        Optional<EventResultDTO> biggestPublic = list.stream().max((o1, o2) -> o1.getSpectators().compareTo(o2.getSpectators()));
        if (biggestPublic.isPresent()) {
            joiner.add("\nMore Public");
            joiner.add(biggestPublic.get().printEvent());
        }
    }


}
