package com.elastra.meli.Repository;

import com.elastra.meli.Model.Planet;
import org.springframework.data.repository.CrudRepository;


public interface PlanetRepository extends CrudRepository<Planet, Long> {

  // Planet findById(Long id);
}
