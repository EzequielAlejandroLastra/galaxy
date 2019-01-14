package com.elastra.meli.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Galaxy implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PLANET_ID")
    private Planet vulcano;

    @ManyToOne
    @JoinColumn(name="PLANET_ID")
    private Planet Ferengis;

    @ManyToOne
    @JoinColumn(name="PLANET_ID")
    private Planet Betasoides;

    public Galaxy(Planet vulcano, Planet ferengis, Planet betasoides) {
        this.vulcano = vulcano;
        Ferengis = ferengis;
        Betasoides = betasoides;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getVulcano() {
        return vulcano;
    }

    public void setVulcano(Planet vulcano) {
        this.vulcano = vulcano;
    }

    public Planet getFerengis() {
        return Ferengis;
    }

    public void setFerengis(Planet ferengis) {
        Ferengis = ferengis;
    }

    public Planet getBetasoides() {
        return Betasoides;
    }

    public void setBetasoides(Planet betasoides) {
        Betasoides = betasoides;
    }


}

