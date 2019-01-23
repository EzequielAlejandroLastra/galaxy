package com.elastra.meli.Model;

import com.elastra.meli.Processor.PredictAndPersistProccesor;
import com.elastra.meli.Utils.RoundingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "PLANET")
public class Planet implements Serializable {

    private static Double EXTREME = 360D;
    private static Double DAY_OF_YEAR = 365D;
    private static final Logger logger = LoggerFactory.getLogger(PredictAndPersistProccesor.class);

    @Id
    @Column(name = "PLANET_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "CIVILIZATION")
    private String civilization;

    @Column(name = "ANGULAR_VELOCITY")
    private Double angularVelocity;

    @Column(name = "RADIOUS")
    private Double radious;

    @Transient
    private double angle;


    public Long id() {
        return id;
    }

    public void id(Long id) {
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


    public Double simulateOneDayMovement(){
        if( this.angle >= EXTREME ) this.angle -= EXTREME ;
        if( this.angle <= -EXTREME ) this.angle += EXTREME ;
        this.angle += this.angularVelocity;
        return this.angle;
    }

    public Double calculateInitialPositionForYear(int year){
        Double daysToCompleteTurning = RoundingUtil.getRoundedNumberHalfUp(EXTREME / this.angularVelocity, 2);
        Double initialAngleForYear = (DAY_OF_YEAR % daysToCompleteTurning) * this.angularVelocity;
        this.angle = initialAngleForYear * (year-1);
        return this.angle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) &&
                Objects.equals(civilization, planet.civilization) &&
                Objects.equals(angularVelocity, planet.angularVelocity) &&
                Objects.equals(radious, planet.radious) &&
                Objects.equals(angle, planet.angle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, civilization, angularVelocity, radious, angle);
    }

}
