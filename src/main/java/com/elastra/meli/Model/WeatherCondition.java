package com.elastra.meli.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "WEATHER_CONDITION")
public class WeatherCondition {

    @Id
    @Column(name = "WEATHER_CONDITION_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "DESC")
    private String description;

    /*
    @Column(name = "TYPE")
    private String type;
    */

    public WeatherCondition(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public WeatherCondition() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherCondition that = (WeatherCondition) o;
        return Objects.equals(id, that.id) &&
                description == that.description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
