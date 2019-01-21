package com.elastra.meli.Controller;

import com.elastra.meli.Model.WeatherPrediction;
import com.elastra.meli.Service.WeatherPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prediction")
public class WeatherPredictionController {

    @Autowired
    WeatherPredictionService weatherPredictionService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,path = "/get-prediction/{day}")
    public WeatherPrediction findWeatherpredictionByDay(@PathVariable(value = "day") Long day) throws Exception {
        if (day != null) return weatherPredictionService.findPredictionByDay(day);
        else {
            throw new Exception("El dia ingresado no puede ser nulo o vacio");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generate-predictions/{years}")
    public void generatePredictions(@PathVariable int years) throws Exception {
        weatherPredictionService.calculateAndPesistPredictions(years);
    }
}
