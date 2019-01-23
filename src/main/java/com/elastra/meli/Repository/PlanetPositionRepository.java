package com.elastra.meli.Repository;

import com.elastra.meli.Model.PlanetPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlanetPositionRepository  extends JpaRepository<PlanetPosition,Long> {


    @Query("from PLANET_POSITION pp where pp.planet.id = :id")
    List<PlanetPosition> findByPlanetId(@Param("id")Long id);

    @Query("select count(pp) from PLANET_POSITION pp where pp.planet.id = :id")
    Long countByPlanetId(@Param("id")Long id);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PLANET_POSITION", nativeQuery = true)
    void cleanPositions();

    List<PlanetPosition> findAllByOrderByDayAsc();

}
