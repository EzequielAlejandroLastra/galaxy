package com.elastra.meli.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "WEATHER_PREDICTION")
public class WeatherPrediction {

    @Id
    @Column(name = "WEATHER_PREDICTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GALAXY_ID")
    private Galaxy galaxy;

    @Column(name = "DAY")
    private long day;

    @ManyToOne
    @JoinColumn(name = "WEATHER_CONDITION_ID")
    private WeatherCondition type;

    @Column(name = "PERIMETER")
    private Double perimeter;


    public WeatherPrediction() {
    }

    public WeatherPrediction(Galaxy galaxy, long day, WeatherCondition type, Double perimeter) {
        this.galaxy = galaxy;
        this.day = day;
        this.type = type;
        this.perimeter = perimeter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherPrediction that = (WeatherPrediction) o;
        return day == that.day &&
                Objects.equals(id, that.id) &&
                Objects.equals(galaxy, that.galaxy) &&
                Objects.equals(type, that.type) &&
                Objects.equals(perimeter, that.perimeter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, galaxy, day, type, perimeter);
    }

    @Override
    public String toString() {
        return " Dia = " + day + ", Clima = " + type.getDescription();
    }


}


