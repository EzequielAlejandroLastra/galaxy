package com.elastra.meli.Utils;

import com.elastra.meli.Model.Enum.ConditionType;
import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherCondition;

import java.math.BigDecimal;

public enum PredictionStrategy {

    LINEAR((p1, p2, p3) -> {
        Double result = CoordinatesCalculator.areAlignedWithSun().calculate(p1, p2, p3);
        return result == BigDecimal.ONE.doubleValue() ? ConditionType.SEQUIA.condition() : ConditionType.OPTIMO.condition();
    }),
    TRIANGULAR((p1, p2, p3) -> {
        Double result = CoordinatesCalculator.isSunInTriangle().calculate(p1, p2, p3);
        return result == BigDecimal.ONE.doubleValue() ? ConditionType.LLUVIA.condition() : ConditionType.NORMAL.condition();
    });

    private PredictCondition predictCondition;

    PredictionStrategy (PredictCondition predictCondition) {
        this.predictCondition = predictCondition;
    }

    public static WeatherCondition calculateCondition(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3) {
        PredictCondition weatherMan = CoordinatesCalculator.areAligned().calculate(p1,p2,p3) == BigDecimal.ZERO.doubleValue()
                ? LINEAR.predictCondition
                : TRIANGULAR.predictCondition;
        return weatherMan.predictCondition(p1,p2,p3);
    }


}
