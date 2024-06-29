package com.whd.soccerapi.serversoccerapi.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="season")
public class Season {
    public Season(Long id, String seasonName, League league) {
        this.id = id;
        this.seasonName = seasonName;
        this.league = league;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  seasonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLeague")
    @Expose(serialize = false, deserialize = false)
    private League league;


}
