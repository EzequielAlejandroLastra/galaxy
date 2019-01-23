package com.elastra.meli.ServiceImpl;

import com.elastra.meli.Model.PlanetPosition;
import com.elastra.meli.Repository.PlanetPositionRepository;
import com.elastra.meli.Repository.PlanetRepository;
import com.elastra.meli.Service.PlanetPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PlanetPositionServiceImpl implements PlanetPositionService {


    @Autowired
    PlanetPositionRepository planetPositionRepository;

    @Autowired
    PlanetRepository planetRepository;


    @Override
    @Transactional
    public synchronized List<PlanetPosition> persistList(List<PlanetPosition> list) {
        return planetPositionRepository.save(list);
    }


    @Override
    public void report(Long id) {
        List<PlanetPosition> list = planetPositionRepository.findAllByOrderByDayAsc();
        Collections.sort(list,new ComparatorPosition());

        for (PlanetPosition p : list) {
            System.out.println(p.toString());
        }
    }

    public class ComparatorPosition implements Comparator<PlanetPosition>{

        @Override
        public int compare(PlanetPosition o1, PlanetPosition o2) {
            return (int) (o1.getDay() < o2.getDay() ? o1.getDay() : o2.getDay()) ;
        }
    }

}
