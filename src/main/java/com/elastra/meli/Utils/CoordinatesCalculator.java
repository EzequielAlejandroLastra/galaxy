package com.elastra.meli.Utils;

import com.elastra.meli.Model.PlanetPosition;

import java.math.BigDecimal;

@FunctionalInterface
public interface CoordinatesCalculator {

    Double calculate(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3);


    // TODO Revisar ----->
    static CoordinatesCalculator isSunInTriangle() {
        return (p1, p2, p3) -> {
            PlanetPosition sun = PlanetPosition.sun();
            CoordinatesCalculator calculator = calculateTriangleOrientation();
            Double p1p2p3 = calculator.calculate(p1, p2, p3);
            Double p1p2Sun = calculator.calculate(p1, p2, sun);
            Double p1p3Sun = calculator.calculate(p1, p3, sun);
            Double p2p3Sun = calculator.calculate(p2, p3, sun);
            return (p1p2p3 < 0 && p1p2Sun < 0 && p1p3Sun < 0 && p2p3Sun < 0) ||
                    (p1p2p3 > 0 && p1p2Sun > 0 && p1p3Sun > 0 && p2p3Sun > 0)
                    ? BigDecimal.ONE.doubleValue()
                    : BigDecimal.ZERO.doubleValue();
        };
    }

    // TODO Revisar ----->
    static CoordinatesCalculator calculatePerimeter() {
        return (p1, p2, p3) -> {
            DistanceCalculator distanceCalculator = (pos1, pos2) ->
                    RoundingUtil.getRoundedNumberHalfUp(
                            Math.sqrt(Math.pow((pos2.getAxisX() - pos1.getAxisX()), 2) + Math.pow((pos2.getAxisY() - pos1.getAxisY()), 2)), 4);

            Double dist1 = distanceCalculator.calculate(p1,p2);
            Double dist2 = distanceCalculator.calculate(p2,p3);
            Double dist3 = distanceCalculator.calculate(p3,p1);

            return (dist1 + dist2 + dist3);
        };
    }

    // TODO Revisar ----->
    static CoordinatesCalculator calculateTriangleOrientation() {
        return (p1, p2, p3) -> ((p1.getAxisX() - p3.getAxisX()) * (p2.getAxisY() - p3.getAxisY())) - ((p1.getAxisY() - p3.getAxisY()) * (p2.getAxisX() - p3.getAxisX()));
    }

    /**
     * @return zero if planet coordinates are aligned with Sun
     */
    static CoordinatesCalculator areAlignedWithSun() {
        return (p1, p2, p3) -> {
            if (p1.getAxisY() == PlanetPosition.sun().getAxisY() && p2.getAxisY() == PlanetPosition.sun().getAxisY() && p3.getAxisY() == PlanetPosition.sun().getAxisY()
                    || p1.getAxisX() == PlanetPosition.sun().getAxisX() && p2.getAxisX() == PlanetPosition.sun().getAxisX() && p3.getAxisX() == PlanetPosition.sun().getAxisX()
                    || calculatePending(p1) == calculatePending(p2) && calculatePending(p2) == calculatePending(p3)) {
                return BigDecimal.ONE.doubleValue();
            }
            return BigDecimal.ZERO.doubleValue();
        };
    }

    /**
     * @return zero if planet coordinates are aligned.
     */
    static CoordinatesCalculator areAligned() {
        return (p1, p2, p3) ->
                Math.abs(p2.getAxisX() - p1.getAxisX()) * (p3.getAxisY() - p2.getAxisY()) - ((p2.getAxisY() - p1.getAxisY()) * (p3.getAxisX() - p2.getAxisX()));
    }


    static Double calculatePending(PlanetPosition pp) {
        if (pp.getAxisX() != BigDecimal.ZERO.doubleValue()) {
            return RoundingUtil.getRoundedNumberHalfUp((pp.getAxisY() / pp.getAxisX()),
                    4);
        } else {
            return BigDecimal.ZERO.doubleValue();
        }
    }

}
