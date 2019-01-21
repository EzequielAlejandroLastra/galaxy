package com.elastra.meli.Model;

import com.elastra.meli.Utils.RoundingUtil;

import javax.persistence.*;
import java.math.RoundingMode;
import java.util.Objects;

@Entity(name = "PLANET_POSITION")
public class PlanetPosition {

    @Id
    @Column(name = "PLANET_POSITION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PLANET_ID")
    private Planet planet;

    @Column(name = "AXIS_X")
    private Double axisX;

    @Column(name = "AXIS_Y")
    private Double axisY;

    @Column(name = "ANGLE")
    private Double angle;

    @Column(name = "DAY")
    private long day;

    @Column(name = "YEAR")
    private long year;

    private PlanetPosition(){
    }

    private PlanetPosition(PlanetPositionBuilder builder) {
        this.planet = builder.planet;
        this.axisX = builder.calculateAxisX();
        this.axisY = builder.calculateAxisY();
        this.angle = builder.planet.getAngle();
        this.day = builder.d;
        this.year = builder.calculateYear();
    }


    public static PlanetPosition sun() {
        PlanetPosition sun = new PlanetPosition();
        sun.setAxisX(0D);
        sun.setAxisY(0D);
        return sun;

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

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetPosition that = (PlanetPosition) o;
        return day == that.day &&
                Objects.equals(id, that.id) &&
                Objects.equals(planet, that.planet) &&
                Objects.equals(axisX, that.axisX) &&
                Objects.equals(axisY, that.axisY) &&
                Objects.equals(angle, that.angle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planet, axisX, axisY, angle, day);
    }

    @Override
    public String toString() {
        return "PlanetPosition{" +
                ", planet = " + planet.getCivilization() +
                ", axisX = " + axisX +
                ", axisY = " + axisY +
                ", angle = " + angle +
                ", day = " + day +
                ", year = " + year +
                '}';
    }


    public static class PlanetPositionBuilder {

        private Planet planet;
        private long d;

        private Double calculateAxisX() {
            return RoundingUtil.getRoundedNumberHalfUp(this.planet.getRadious() * Math.cos(Math.toRadians(this.planet.getAngle())), 4);
        }

        private Double calculateAxisY() {
            return RoundingUtil.getRoundedNumberHalfUp(this.planet.getRadious() * Math.sin(Math.toRadians(this.planet.getAngle())), 4);
        }

        private long calculateYear() {
            return ((this.d - 1) / 365);
        }


        public PlanetPositionBuilder() {
        }

        public PlanetPositionBuilder planet(Planet p) {
            this.planet = p;
            return this;
        }

        public PlanetPositionBuilder day(Long d) {
            this.d = d;
            return this;
        }

        public PlanetPosition build() {
            PlanetPosition planetPosition = new PlanetPosition(this);
            return planetPosition;
        }
    }
}


