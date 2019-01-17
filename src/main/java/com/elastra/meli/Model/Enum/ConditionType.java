package com.elastra.meli.Model.Enum;

import com.elastra.meli.Model.WeatherCondition;

public enum ConditionType {

    LLUVIA(new WeatherCondition()),
    LLUVIA_INTENSA(new WeatherCondition()),
    SEQUIA(new WeatherCondition()),
    OPTIMO(new WeatherCondition()),
    NORMAL(new WeatherCondition());

    private WeatherCondition condition;

    ConditionType(WeatherCondition condition) {
        this.condition = condition;
    }

    public WeatherCondition condition() {
        return this.condition;
    }
}
