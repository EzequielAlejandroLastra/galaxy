package com.elastra.meli.Processor;

import com.elastra.meli.Model.Planet;
import com.elastra.meli.Model.PlanetPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PlanetCoordinateProccesor implements Runnable{

    private Planet planet;
    private final static Integer DAY_OF_YEAR = 365;

    @Override
    public void run() {
        int count = 0;
        List<PlanetPosition> planetPositionList = new ArrayList<PlanetPosition>();

        IntStream.range(0, DAY_OF_YEAR * 10).forEach(i -> {
            if(i % DAY_OF_YEAR != 0) {
                planet.simulateOneDayMovement();
                PlanetPosition planetPosition = PlanetPosition.builder()
                        .planet(planet)
                        .day(Long.valueOf(i))
                        .build();
                planetPositionList.add(planetPosition);
            }else {
                //TODO service save
                planetPositionList.clear();
            }
        });
        /*
        for (int day = 0; day < 365; day++) {
            count ++;
            if(count <= 365) {
                planet.simulateOneDayMovement();
                PlanetPosition planetPosition = new PlanetPosition.PlanetPositionBuilder()
                    .planet(planet)
                    .day(Long.valueOf(day))
                    .build();
                planetPositionList.add(planetPosition);
            }else {
                //TODO service save
                count = 0;
            }


        }
*/
    }
}
