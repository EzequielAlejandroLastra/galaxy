package com.elastra.meli.Model;


import com.elastra.meli.Repository.PlanetRepository;
import com.elastra.meli.Utils.RoundingUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetTest {


    @Autowired
    PlanetRepository planetRepository;

    @Test
    public void checkInitialPositionYearPLanet(){
        Planet planet = planetRepository.findOne(1L);

        Double position = planet.calculateInitialPositionForYear(4);




    }
}
