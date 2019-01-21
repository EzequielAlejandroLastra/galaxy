package com.elastra.meli.Service;

import com.elastra.meli.Model.PlanetPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetPositionServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(PlanetPositionServiceTest.class);

    @Autowired
    PlanetPositionService planetPositionService;

    @Test
    public void saveListPlanetPosition(){

        List<PlanetPosition> list = new ArrayList<PlanetPosition>();
        int year = 5;
        //planetPositionService.calculateCoordinatesInNYears(year);
        planetPositionService.report(1L);
        System.out.println("-------------");
        planetPositionService.report(2L);
        System.out.println("-------------");
        planetPositionService.report(3L);
        logger.info("TERMINO");
    }
}
