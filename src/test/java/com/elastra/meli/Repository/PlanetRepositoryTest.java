package com.elastra.meli.Repository;

import com.elastra.meli.Model.Planet;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetRepositoryTest {


    @Autowired
    PlanetRepository planetRepository;


    @Test
    public void plantetLoad(){
        Planet ferengi = planetRepository.findById(1L).get();
        assertThat(ferengi != null, Is.is(true));
        assertThat("FERENGI".equals(ferengi.getCivilization()), Is.is(true));

        Planet vulcano = planetRepository.findById(2L).get();
        assertThat(vulcano != null, Is.is(true));
        assertThat("VULCANO".equals(vulcano.getCivilization()), Is.is(true));

        Planet betasoide = planetRepository.findById(3L).get();
        assertThat(betasoide != null, Is.is(true));
        assertThat("BETASOIDE".equals(betasoide.getCivilization()), Is.is(true));

    }
}
