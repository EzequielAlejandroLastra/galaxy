package com.elastra.meli.Repository;

import com.elastra.meli.Model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlanetRepository extends JpaRepository<Planet, Long> {

  // Planet findById(Long id);
}
