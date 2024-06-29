package com.whd.soccerapi.serversoccerapi.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="country")
@NoArgsConstructor
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("name_en")
    private String nameEn;


    public Country(String nameEn) {
        this.nameEn = nameEn;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
