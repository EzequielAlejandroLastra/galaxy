package com.elastra.meli.ServiceImpl;

import com.elastra.meli.Model.*;
import com.elastra.meli.Model.Enum.ConditionType;
import com.elastra.meli.Processor.PredictAndPersistProccesor;
import com.elastra.meli.Repository.GalaxyRepository;
import com.elastra.meli.Repository.PlanetPositionRepository;
import com.elastra.meli.Repository.PlanetRepository;
import com.elastra.meli.Repository.WeatherPredictionRepository;
import com.elastra.meli.Service.WeatherPredictionService;
import com.elastra.meli.Utils.CoordinatesCalculator;
import com.elastra.meli.Utils.PredictionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class WeatherPredictionServiceImpl implements WeatherPredictionService {

    private static Long FERENGI = 1L;
    private static Long VULCANO = 2L;
    private static Long BETASOIDE = 3L;

    @Autowired
    WeatherPredictionRepository weatherPredictionRepository;

    @Autowired
    GalaxyRepository galaxyRepository;

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    PlanetPositionRepository planetPositionRepository;

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Autowired
    private ApplicationContext applicationContext;


    private Galaxy galaxy;

    @PostConstruct
    private void init() {
        galaxy = galaxyRepository.findOne(1L);
    }

    @Override
    public WeatherPrediction findPredictionByDay(Long day)  {
        try {
            return weatherPredictionRepository.findByDay(day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public synchronized List<WeatherPrediction> presistPredictions(List<WeatherPrediction> list) {
        return weatherPredictionRepository.save(list);
    }



    @Override
    public WeatherPrediction generateWeatherPrediction(PlanetPosition pp1, PlanetPosition pp2, PlanetPosition pp3, long day) {
        WeatherCondition weatherCondition = PredictionStrategy.calculateCondition(pp1,pp2,pp3);
        Double perimeter = ConditionType.LLUVIA.name().equals(weatherCondition.getDescription()) ? CoordinatesCalculator.calculatePerimeter().calculate(pp1,pp2,pp3) : null;
        WeatherPrediction weatherPrediction = new WeatherPrediction(this.galaxy, day, weatherCondition, perimeter);
        return weatherPrediction;
    }

    @Override
    public String calculateAndPesistPredictions(int years) {
        try {
            weatherPredictionRepository.cleanPredictions();

            Planet ferengi = planetRepository.findOne(FERENGI);
            Planet vulcano = planetRepository.findOne(VULCANO);
            Planet betasoide = planetRepository.findOne(BETASOIDE);
            executor.initialize();

            IntStream.range(1,years + 1).forEach(i -> {

                PredictAndPersistProccesor proccesor = procesorBuilder(ferengi.clone(), vulcano.clone(), betasoide.clone(), i);

                executor.execute(proccesor);
            });
            executor.shutdown();

            Double maxPerimeter = weatherPredictionRepository.findMaxPerimeter();
            weatherPredictionRepository.updateConditionByPerimeter(maxPerimeter);
            return  "Proceso Finalizado Exitosamente!!!";
        }catch (Exception e) {
            return "Proceso de Generacion de Predicciones Fallo, Por favor comuniquese con el Desarrollador";
        }
    }

    @Override
    public List<WeatherPrediction> getPredictions() {
        return weatherPredictionRepository.findAllByOrderByDayAsc();
    }

    @Override
    public String cleanPredictions() {
        try {
            planetPositionRepository.cleanPositions();
            weatherPredictionRepository.cleanPredictions();
            return "Se limpiaron las tablas Exitosamente";
        }catch (Exception e){
           return "Debido a un error no se pudieron limpiar las tablas para generar prediciones, Contacte al desarrollador";
        }
    }

    private PredictAndPersistProccesor procesorBuilder(Planet ferengi, Planet vulcano, Planet betasoide, int i) {
        PredictAndPersistProccesor proccesor = (PredictAndPersistProccesor) applicationContext.getBean(PredictAndPersistProccesor.NAME);
        proccesor.setFerengi(ferengi);
        proccesor.setVulcano(vulcano);
        proccesor.setBetasoide(betasoide);
        proccesor.setYear(i);
        return proccesor;
    }


}
