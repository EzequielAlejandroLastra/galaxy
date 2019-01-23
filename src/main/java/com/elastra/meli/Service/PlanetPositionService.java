package com.elastra.meli.Service;

import com.elastra.meli.Model.PlanetPosition;

import java.util.List;

public interface PlanetPositionService {

    List<PlanetPosition> persistList(List<PlanetPosition> list);
    List<PlanetPosition> getAllPlanetPositions();

}
