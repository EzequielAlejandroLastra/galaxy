package com.elastra.meli.Util;

import com.elastra.meli.Model.Planet;
import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Utils.CoordinatesCalculator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CoordinateCalculatorTest {


    @Test
    public void when_planets_are_aligned_the_calculator_should_return_zero(){

        Planet planet1 = new Planet();
        planet1.setAngle(90D);
        planet1.setRadious(100D);
        PlanetPosition position1 = PlanetPosition.builder().planet(planet1).build();

        Planet planet2 = new Planet();
        planet2.setAngle(270D);
        planet2.setRadious(300D);
        PlanetPosition position2 = PlanetPosition.builder().planet(planet2).build();

        Planet planet3 = new Planet();
        planet3.setAngle(-90D);
        planet3.setRadious(500D);
        PlanetPosition position3 = PlanetPosition.builder().planet(planet3).build();

      Double result = CoordinatesCalculator.areAligned().calculate(position1,position2,position3);

        assertThat(result.equals(0D) , is(true));

        planet1.setAngle(180D);
        planet1.setRadious(100D);
        position1 = PlanetPosition.builder().planet(planet1).build();

        planet2.setAngle(180D);
        planet2.setRadious(300D);
        position2 = PlanetPosition.builder().planet(planet2).build();

        planet3.setAngle(0D);
        planet3.setRadious(500D);
        position3 = PlanetPosition.builder().planet(planet3).build();

        result = CoordinatesCalculator.areAligned().calculate(position1,position2,position3);

        assertThat(result.equals(0D) , is(true));

    }

    @Test
    public void when_planets_are_not_aligned_the_calculator_should_return_one(){

        Planet planet1 = new Planet();
        planet1.setAngle(90D);
        planet1.setRadious(100D);
        PlanetPosition position1 = PlanetPosition.builder().planet(planet1).build();

        Planet planet2 = new Planet();
        planet2.setAngle(46D);
        planet2.setRadious(300D);
        PlanetPosition position2 = PlanetPosition.builder().planet(planet2).build();

        Planet planet3 = new Planet();
        planet3.setAngle(-90D);
        planet3.setRadious(500D);
        PlanetPosition position3 = PlanetPosition.builder().planet(planet3).build();

        Double result = CoordinatesCalculator.areAligned().calculate(position1,position2,position3);

        assertThat(result, is(not(0D)));
    }

    @Test
    public void when_planets_are_not_aligned_and_sun_is_in_triangle_the_calculator_should_return_one(){

        Planet planet1 = new Planet();
        planet1.setAngle(90D);
        planet1.setRadious(100D);
        PlanetPosition position1 = PlanetPosition.builder().planet(planet1).build();


        Planet planet2 = new Planet();
        planet2.setAngle(0D);
        planet2.setRadious(300D);
        PlanetPosition position2 = PlanetPosition.builder().planet(planet2).build();

        Planet planet3 = new Planet();
        planet3.setAngle(200D);
        planet3.setRadious(500D);
        PlanetPosition position3 = PlanetPosition.builder().planet(planet3).build();


        Double result = CoordinatesCalculator.isSunInTriangle().calculate(position1,position2,position3);

        assertThat(result, is(1D));
    }


    @Test
    public void when_planets_are_not_aligned_and_sun_is_not_in_triangle_the_calculator_should_return_zero(){

        Planet planet1 = new Planet();
        planet1.setAngle(90D);
        planet1.setRadious(100D);
        PlanetPosition position1 = PlanetPosition.builder().planet(planet1).build();


        Planet planet2 = new Planet();
        planet2.setAngle(45D);
        planet2.setRadious(300D);
        PlanetPosition position2 = PlanetPosition.builder().planet(planet2).build();

        Planet planet3 = new Planet();
        planet3.setAngle(200D);
        planet3.setRadious(500D);
        PlanetPosition position3 = PlanetPosition.builder().planet(planet3).build();


        Double result = CoordinatesCalculator.isSunInTriangle().calculate(position1,position2,position3);

        assertThat(result, is(0D));
    }
}

