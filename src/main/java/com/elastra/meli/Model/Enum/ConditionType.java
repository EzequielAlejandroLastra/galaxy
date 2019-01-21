package com.elastra.meli.Model.Enum;

import com.elastra.meli.Model.WeatherCondition;

public enum ConditionType {

    LLUVIA(new WeatherCondition(1L, "LLUVIA")),
    LLUVIA_INTENSA(new WeatherCondition( 2L, "LLUVIA_INTENSA")),
    SEQUIA(new WeatherCondition(3L, "SEQUIA")),
    OPTIMO(new WeatherCondition(4L, "OPTIMO")),
    NORMAL(new WeatherCondition(5L, "NORMAL"));

    private WeatherCondition condition;

    ConditionType(WeatherCondition condition) {
        this.condition = condition;
    }

    public WeatherCondition condition() {
        return this.condition;
    }
}
