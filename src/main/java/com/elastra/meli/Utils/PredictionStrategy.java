package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherCondition;

public enum PredictionStrategy implements PredictCondition {

    LINEAR() {
        @Override
        public WeatherCondition predictCondition(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3) {
            return null;
        }
    },
    TRIANGULAR() {
        @Override
        public WeatherCondition predictCondition(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3) {
            return null;
        }
    };

}
