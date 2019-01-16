package com.elastra.meli.Repository;

import com.elastra.meli.Model.WeatherPrediction;
import org.springframework.data.repository.CrudRepository;

public interface WeatherPredictionRepository extends CrudRepository<WeatherPrediction,Long> {
}
