package com.elastra.meli.Task;

import com.elastra.meli.Service.WeatherPredictionService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneratePredictionTasklet implements Tasklet {


    @Autowired
    WeatherPredictionService weatherPredictionService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
           // weatherPredictionService.calculateAndPesistPredictions(10);
        return RepeatStatus.FINISHED;
    }
}
