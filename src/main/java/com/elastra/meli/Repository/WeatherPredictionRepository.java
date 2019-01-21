package com.elastra.meli.Repository;

import com.elastra.meli.Model.WeatherPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface WeatherPredictionRepository extends JpaRepository<WeatherPrediction,Long> {

    WeatherPrediction findByDay(Long day);

    @Query(value = "Select max(PERIMETER) from WEATHER_PREDICTION", nativeQuery = true)
    double findMaxPerimeter();

    @Modifying
    @Transactional
    @Query(value = "UPDATE WEATHER_PREDICTION SET WEATHER_CONDITION_ID = 2 WHERE PERIMETER = :perimeter", nativeQuery = true)
    void updateConditionByPerimeter(@Param("perimeter")double perimeter);

}
