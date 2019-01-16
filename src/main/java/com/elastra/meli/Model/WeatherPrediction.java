package com.elastra.meli.Model;

import javax.persistence.*;

@Entity
public class WeatherPrediction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long weatherPredictionId;

    @ManyToOne
    @JoinColumn(name="GALAXY_ID")
    private Galaxy galaxy;

    @Column(name = "DAY")
    private long day;

    @ManyToOne
    @JoinColumn(name="CONDITION_ID")
    private WeatherCondition type;


    public WeatherPrediction(Galaxy galaxy, long day, WeatherCondition type) {
        this.galaxy = galaxy;
        this.day = day;
        this.type = type;
    }

    public Long getWeatherPredictionId() {
        return weatherPredictionId;
    }

    public void setWeatherPredictionId(Long weatherPredictionId) {
        this.weatherPredictionId = weatherPredictionId;
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

    public WeatherCondition getType() {
        return type;
    }

    public void setType(WeatherCondition type) {
        this.type = type;
    }
}
