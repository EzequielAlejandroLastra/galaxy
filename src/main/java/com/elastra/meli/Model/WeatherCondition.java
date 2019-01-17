package com.elastra.meli.Model;

import com.elastra.meli.Model.Enum.ConditionType;

import javax.persistence.*;

@Entity
public class WeatherCondition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "DESC")
    private ConditionType description;

    @Column(name = "TYPE")
    private String type;

    public WeatherCondition(ConditionType description, String type) {
        this.description = description;
        this.type = type;
    }

    public WeatherCondition() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionType getDescription() {
        return description;
    }

    public void setDescription(ConditionType description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
