package com.elastra.meli.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "CIVILIZATION")
    private String civilization;

    @Column(name = "ANGULAR_VELOCITY")
    private Double angularVelocity;

    @Column(name = "RADIOUS")
    private Double radious;

    @Transient
    private Double angle;

    public Planet(String civilization, Double angularVelocity, Double radious, Double angle) {
        this.civilization = civilization;
        this.angularVelocity = angularVelocity;
        this.radious = radious;
        this.angle = angle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilization() {
        return civilization;
    }

    public void setCivilization(String civilization) {
        this.civilization = civilization;
    }

    public Double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(Double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public Double getRadious() {
        return radious;
    }

    public void setRadious(Double radious) {
        this.radious = radious;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }
}
