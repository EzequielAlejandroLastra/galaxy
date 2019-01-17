package com.elastra.meli.Repository;

import com.elastra.meli.Model.WeatherPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherPredictionRepository extends JpaRepository<WeatherPrediction,Long> {
}
