package com.elastra.meli.Service;

import com.elastra.meli.Model.WeatherPrediction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherPredictionServiceTest {

    @Autowired
    PlanetPositionService planetPositionService;

    @Autowired
    WeatherPredictionService weatherPredictionService;

    @Test
    public void when_run_calculateAndPesistPredictions_with_ten_years_should_persist_3650_registers() throws Exception {

        int year = 10;
        weatherPredictionService.calculateAndPesistPredictions(year);
        List<WeatherPrediction> list = weatherPredictionService.getPredictions();

        assertThat(list.size(), is(3650));

    }
}
