package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;

@FunctionalInterface
public interface DistanceCalculator {
    Double calculate(PlanetPosition p1, PlanetPosition p2);

    static DistanceCalculator distanceTwoPosition(){
        return (p1,p2) -> (RoundingUtil.getRoundedNumberHalfUp(
                Math.sqrt(Math.pow((p2.getAxisX() - p1.getAxisX()), 2) + Math.pow((p2.getAxisY() - p1.getAxisY()), 2)), 16));
    }

}
