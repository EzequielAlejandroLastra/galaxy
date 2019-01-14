package com.elastra.meli.Model;

import javax.persistence.*;

@Entity
public class PlanetPosition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="PLANET_ID")
    private Planet planet;

    @Column(name = "AXIS_X")
    private Double axisX;

    @Column(name = "AXIS_Y")
    private Double axisY;

    @Column(name = "ANGLE")
    private Double angle;

    @Column(name = "DAY")
    private long day;

    public PlanetPosition(Planet planet, Double axisX, Double axisY, Double angle, long day) {
        this.planet = planet;
        this.axisX = axisX;
        this.axisY = axisY;
        this.angle = angle;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Double getAxisX() {
        return axisX;
    }

    public void setAxisX(Double axisX) {
        this.axisX = axisX;
    }

    public Double getAxisY() {
        return axisY;
    }

    public void setAxisY(Double axisY) {
        this.axisY = axisY;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }
}
