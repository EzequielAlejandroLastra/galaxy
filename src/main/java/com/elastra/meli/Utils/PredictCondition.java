package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherCondition;

@FunctionalInterface
public interface PredictCondition {

    WeatherCondition predictCondition(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3);

}
