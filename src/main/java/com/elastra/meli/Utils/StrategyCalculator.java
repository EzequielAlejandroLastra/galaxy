package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherCondition;

@FunctionalInterface
public interface StrategyCalculator {

    PredictionStrategy calculateStrategy(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3);

    static boolean areAligned(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3) {
        return (((p2.getAxisX()-p1.getAxisX())*(p3.getAxisY()-p2.getAxisY()))-((p2.getAxisY()-p1.getAxisY())*(p3.getAxisX()-p2.getAxisX())) == 0.00);
    }

    static <T extends Enum<PredictionStrategy> & PredictCondition> T calculate() {
        return (p1, p2, p3) -> StrategyCalculator.areAligned(p1, p2, p3)
                ? PredictionStrategy.LINEAR.predictCondition(p1, p2, p3)
                : PredictionStrategy.TRIANGULAR(p1, p2, p3);
    }

    static WeatherCondition example() {

        StrategyCalculator sc = (p1,p2,p3) -> StrategyCalculator.calculate();
        PlanetPosition p1 = PlanetPosition.builder().build();
        PlanetPosition p2 = PlanetPosition.builder().build();
        PlanetPosition p3 = PlanetPosition.builder().build();

        return sc.calculateStrategy(p1,p2,p3);
    }

}
