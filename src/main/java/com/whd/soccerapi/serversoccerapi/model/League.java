package com.whd.soccerapi.serversoccerapi.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="league")
public class League {

    public League() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLeague;
    private Long idAPIfootball;
    private String strSport;
    private String strLeague;
    private int  intDivision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCountry")
    @Expose(serialize = false, deserialize = false)
    private Country country;

    @Transient
    private String strCountry;


}
