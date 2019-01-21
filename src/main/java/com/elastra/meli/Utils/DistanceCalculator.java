package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;

@FunctionalInterface
public interface DistanceCalculator {
    Double calculate(PlanetPosition p1, PlanetPosition p2);
}
