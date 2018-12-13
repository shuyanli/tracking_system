package demo.service.impl;

import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import demo.service.SupplyLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplyLocationServiceImpl implements SupplyLocationService {
    SupplyLocationRepository supplyLocationRepository;

    @Autowired
    public SupplyLocationServiceImpl(SupplyLocationRepository supplyLocationRepository){
        this.supplyLocationRepository = supplyLocationRepository;
    }

    @Override
    public SupplyLocation findFirstByLocation(Point point) {
        return supplyLocationRepository.findFirstByLocationNear(point);
    }

    @Override
    public void delete() {
        supplyLocationRepository.deleteAll();
    }

    @Override
    public void save(List<SupplyLocation> locations) {
        supplyLocationRepository.save(locations);
    }
}
