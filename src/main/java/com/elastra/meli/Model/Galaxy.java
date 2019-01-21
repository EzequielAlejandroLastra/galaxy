package com.elastra.meli.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "GALAXY")
public class Galaxy implements Serializable{

    @Id
    @Column(name = "GALAXY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PLANET_1")
    private Planet vulcano;

    @ManyToOne
    @JoinColumn(name="PLANET_2")
    private Planet Ferengis;

    @ManyToOne
    @JoinColumn(name="PLANET_3")
    private Planet Betasoides;


    public Galaxy() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(id, galaxy.id) &&
                Objects.equals(vulcano, galaxy.vulcano) &&
                Objects.equals(Ferengis, galaxy.Ferengis) &&
                Objects.equals(Betasoides, galaxy.Betasoides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vulcano, Ferengis, Betasoides);
    }
}

