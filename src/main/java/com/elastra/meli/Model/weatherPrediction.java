package com.elastra.meli.Model;

import javax.persistence.*;

@Entity
public class weatherPrediction {

    @ManyToOne
    @JoinColumn(name="GALAXY_ID")
    private Galaxy galaxy;

    @Column(name = "DAY")
    private long day;

    @ManyToOne
    @JoinColumn(name="CONDITION_ID")
    private weatherCondition type;

    public weatherPrediction(Galaxy galaxy, long day, weatherCondition type) {
        this.galaxy = galaxy;
        this.day = day;
        this.type = type;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public weatherCondition getType() {
        return type;
    }

    public void setType(weatherCondition type) {
        this.type = type;
    }
}
