package com.elastra.meli.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {

    public static Double getRoundedNumberHalfUp(Double number, int decimals) {
        return new BigDecimal(number).setScale(decimals, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double getRoundedNumberHalfDown(Double number, int decimals) {
        return new BigDecimal(number).setScale(decimals, RoundingMode.HALF_DOWN).doubleValue();
    }

    public static Double getRoundedNumberHalfEven(Double number, int decimals) {
        return new BigDecimal(number).setScale(decimals, RoundingMode.HALF_EVEN).doubleValue();
    }


}