package com.elastra.meli.Utils;

import com.elastra.meli.Model.Planet;
import com.elastra.meli.Model.PlanetPosition;

import java.math.BigDecimal;

@FunctionalInterface
public interface CoordinatesCalculator {

    Double calculate(PlanetPosition p1, PlanetPosition p2, PlanetPosition p3);


    /**
     * CALCULO PERMITE SABER SI EL EJE DE COORDENADAS SE ENCUENTRA DENTRO DEL TRIANGULO QUE FORMAN LOS 3 PUNTOS EN EL PLANO
     * @return  Devuelve un uno(1) si es que el eje de coordenas se encuentra dentro del triangulo. En caso contrario devuelve cero(0)
     * @author Ezequiel Lastra
     */
    static CoordinatesCalculator isSunInTriangle() {
        return (p1, p2, p3) -> {

            PlanetPosition sun = PlanetPosition.sun();
            CoordinatesCalculator calculator = calculateTriangleArea();
            Double p1p2p3 = calculator.calculate(p1, p2, p3);
            Double p1p2Sun = calculator.calculate(p1, p2, sun);
            Double p1p3Sun = calculator.calculate(p1, p3, sun);
            Double p2p3Sun = calculator.calculate(p2, p3, sun);
            return p1p2p3.equals(p1p2Sun + p1p3Sun + p2p3Sun)
                    ? BigDecimal.ONE.doubleValue()
                    : BigDecimal.ZERO.doubleValue();
            };

    }

    /**
     * CALCULO DEL PERIMETRO DEL TRIANGULO QUE FORMAN TRES PUNTOS EN EL PLANO.
     * @return  Devuelve un Double que representa el valor del preimetro del triangulo.
     * @author Ezequiel Lastra
     */
    static CoordinatesCalculator calculatePerimeter() {
        return (p1, p2, p3) -> {

            Double dist1 = DistanceCalculator.distanceTwoPosition().calculate(p1,p2);
            Double dist2 = DistanceCalculator.distanceTwoPosition().calculate(p2,p3);
            Double dist3 = DistanceCalculator.distanceTwoPosition().calculate(p3,p1);

            return (dist1 + dist2 + dist3);
        };
    }

    /**
     * CALCULO
     * @return  Devuelve un double que representa el valor del preimetro del triangulo.
     * @author Ezequiel Lastra
     */
    static CoordinatesCalculator calculateTriangleOrientation() {
        return (p1, p2, p3) -> ((p1.getAxisX() - p3.getAxisX()) * (p2.getAxisY() - p3.getAxisY())) - ((p1.getAxisY() - p3.getAxisY()) * (p2.getAxisX() - p3.getAxisX()));
    }

    static CoordinatesCalculator calculateTriangleArea() {
        return (p1, p2, p3) -> {

            Double dist1 = DistanceCalculator.distanceTwoPosition().calculate(p1,p2);
            Double dist2 = DistanceCalculator.distanceTwoPosition().calculate(p2,p3);
            Double dist3 = DistanceCalculator.distanceTwoPosition().calculate(p3,p1);
            Double p = (dist1 + dist2 + dist3)/2;
            Double result = Math.sqrt( p * (p - dist1) * (p - dist2) * (p - dist3) );
            return RoundingUtil.getRoundedNumberHalfUp(result,8);
        };

    }

    /**
     * CALCULO DE TRES PUNTOS ALINEADO CON EL EJE DE COORDENADAS
     * @return  Devuelve uno(1) si los tres PlanetPosition se encuentran alineados con el sol, cero(0) en caso contrario.
     * @author Ezequiel Lastra
     */
    static CoordinatesCalculator areAlignedWithSun() {
        return (p1, p2, p3) -> {
            if (p1.getAxisY().equals(PlanetPosition.sun().getAxisY()) && p2.getAxisY().equals(PlanetPosition.sun().getAxisY()) && p3.getAxisY().equals(PlanetPosition.sun().getAxisY())
                    || p1.getAxisX().equals(PlanetPosition.sun().getAxisX()) && p2.getAxisX().equals(PlanetPosition.sun().getAxisX()) && p3.getAxisX().equals(PlanetPosition.sun().getAxisX())
                    || calculatePending(p1).equals(calculatePending(p2)) && calculatePending(p2).equals(calculatePending(p3))) {
                return BigDecimal.ONE.doubleValue();
            }
            return BigDecimal.ZERO.doubleValue();
        };
    }

    /**
     * CALCULO DE TRES PUNTOS ALINEADO ES UN PLANO
     * @return Devuelve cero(0) si los tres PlanetPosition se encuentran alineados. En caso contrario devuelve un valor distinto a cero(0)
     * @author Ezequiel Lastra
     */
    static CoordinatesCalculator areAligned() {
        return (p1, p2, p3) -> {
            Double result = Math.abs((p2.getAxisX() - p1.getAxisX()) * (p3.getAxisY() - p2.getAxisY()))
                    - ((p2.getAxisY() - p1.getAxisY()) * (p3.getAxisX() - p2.getAxisX()));
            return result.equals(0D) ? 0.00 : 1.00;
        };
    }



    /**
     * CALCULO DE PENDIENTE
     * Determina la pendiente de un punto, evaluandola contra el punto  de coordenanadas x=0 y=0.
     * @return Devuelve un Double que representa la pendiente .
     * @param p: PlantetPosition que contiene las coordenas cartecianas necesarias para calcular la pendiente.
     * @author Ezequiel Lastra
     */
    static Double calculatePending(PlanetPosition p) {
        if (p.getAxisX() != BigDecimal.ZERO.doubleValue()) {
            return RoundingUtil.getRoundedNumberHalfUp((p.getAxisY() / p.getAxisX()),
                    8);
        } else {
            return BigDecimal.ZERO.doubleValue();
        }
    }

}
