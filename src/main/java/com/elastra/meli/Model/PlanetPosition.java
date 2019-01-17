package com.elastra.meli.Model;

import com.elastra.meli.Utils.RoundingUtil;

import javax.persistence.*;
import java.math.RoundingMode;

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

    private PlanetPosition(PlanetPositionBuilder builder) {
        this.planet = builder.planet;
        this.axisX = builder.calculateAxisX();
        this.axisY = builder.calculateAxisY();
        this.angle = builder.planet.getAngle();
        this.day = builder.d;
    }

    public static PlanetPositionBuilder builder() {
        return new PlanetPositionBuilder();
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


    public static class PlanetPositionBuilder {

        private Planet planet;
        private long d;

        private Double calculateAxisX(){
            return RoundingUtil.getRoundedNumberHalfUp(this.planet.getRadious() * Math.cos(Math.toRadians(this.planet.getAngle())) , 4);
        }

        private Double calculateAxisY(){
            return RoundingUtil.getRoundedNumberHalfUp(this.planet.getRadious() * Math.sin(Math.toRadians(this.planet.getAngle())) , 4);
        }

        public PlanetPositionBuilder(){
        }

        public PlanetPositionBuilder planet(Planet p) {
            this.planet = p;
            return this;
        }

        public PlanetPositionBuilder day(Long d) {
           this.d = d;
           return this;
        }

        public PlanetPosition build(){
            PlanetPosition planetPosition = new PlanetPosition(this);
            return planetPosition;
        }
    }
}


