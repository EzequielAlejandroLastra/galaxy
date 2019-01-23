package com.elastra.meli.Service;

import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Model.WeatherPrediction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherPredictionServiceTest {

    @Autowired
    PlanetPositionService planetPositionService;

    @Autowired
    WeatherPredictionService weatherPredictionService;

    @Test
    public void saveListPlanetPosition(){

        int year = 10;
        weatherPredictionService.calculateAndPesistPredictions(year);
        List<WeatherPrediction> list = weatherPredictionService.getPredictions();

        for (WeatherPrediction p: list) {
            System.out.println(p.toString());
        }

        planetPositionService.report(1L);
        System.out.println("-------------");
       /* planetPositionService.report(2L);
        System.out.println("-------------");
        planetPositionService.report(3L);*/
        //logger.info("TERMINO");
    }
}
