package com.elastra.meli.Processor;

import com.elastra.meli.Model.Planet;
import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherPrediction;
import com.elastra.meli.Service.PlanetPositionService;
import com.elastra.meli.Service.WeatherPredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Component("predictionProccesor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PredictAndPersistProccesor implements Runnable, Cloneable {


    public static final String NAME = "predictionProccesor";
    private static final Logger logger = LoggerFactory.getLogger(PredictAndPersistProccesor.class);


    @Autowired
    PlanetPositionService planetPositionService;

    @Autowired
    WeatherPredictionService weatherPredictionService;

    private final static Integer DAY_OF_YEAR = 365;
    private Planet ferengi;
    private Planet vulcano;
    private Planet betasoide;
    private int year;

    @Override
    public void run() {
        logger.info(" EXECUTE RUN()");
        List<PlanetPosition> planetPositionList = new ArrayList<PlanetPosition>();
        List<WeatherPrediction> predictionList = new ArrayList<WeatherPrediction>();

        calculateInitialPositionForYearAllPlanet();

        LongStream.range((DAY_OF_YEAR * (year - 1) + 1), ((DAY_OF_YEAR * year) + 1)).forEach(i -> {
            simulateOneDayMoveForAllPlanet();

            PlanetPosition planetPositionFerengi = PlanetPosition.builder()
                    .planet(ferengi)
                    .day(Long.valueOf(i)).build();

            PlanetPosition planetPositionVulcano = PlanetPosition.builder()
                    .planet(vulcano)
                    .day(Long.valueOf(i)).build();

            PlanetPosition planetPositionBetasoide = PlanetPosition.builder()
                    .planet(betasoide)
                    .day(Long.valueOf(i)).build();

            planetPositionList.add(planetPositionFerengi);
            planetPositionList.add(planetPositionVulcano);
            planetPositionList.add(planetPositionBetasoide);

            WeatherPrediction weatherPrediction = weatherPredictionService.
                    generateWeatherPrediction(planetPositionFerengi, planetPositionVulcano, planetPositionBetasoide, i);
            predictionList.add(weatherPrediction);

            if ((i == ((DAY_OF_YEAR * year) ))) {
                planetPositionService.persistList(planetPositionList);
                weatherPredictionService.presistPredictions(predictionList);
            }
        });
    }

    private void simulateOneDayMoveForAllPlanet() {
        ferengi.simulateOneDayMovement();
        vulcano.simulateOneDayMovement();
        betasoide.simulateOneDayMovement();
    }

    private void calculateInitialPositionForYearAllPlanet() {
        this.ferengi.calculateInitialPositionForYear(this.year);
        this.vulcano.calculateInitialPositionForYear(this.year);
        this.betasoide.calculateInitialPositionForYear(this.year);
    }

    public static PredictAndPersistProccesorBuilder builder(){
        return new PredictAndPersistProccesorBuilder();
    }

    public Planet getFerengi() {
        return ferengi;
    }

    public void setFerengi(Planet ferengi) {
        this.ferengi = ferengi;
    }

    public Planet getVulcano() {
        return vulcano;
    }

    public void setVulcano(Planet vulcano) {
        this.vulcano = vulcano;
    }

    public Planet getBetasoide() {
        return betasoide;
    }

    public void setBetasoide(Planet betasoide) {
        this.betasoide = betasoide;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static class PredictAndPersistProccesorBuilder{

        private Planet ferengi;
        private Planet vulcano;
        private Planet betasoide;
        private int year;

        public PredictAndPersistProccesorBuilder() {
        }

        public PredictAndPersistProccesorBuilder Ferengi(Planet ferengi) {
            this.ferengi = ferengi;
            return this;
        }

        public PredictAndPersistProccesorBuilder Vulcano(Planet vulcano) {
            this.vulcano = vulcano;
            return this;
        }

        public PredictAndPersistProccesorBuilder Betasoide(Planet betasoide) {
            this.betasoide = betasoide;
            return this;
        }

        public PredictAndPersistProccesorBuilder Year(int year) {
            this.year = year;
            return this;
        }
    }
}
