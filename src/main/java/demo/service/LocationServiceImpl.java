package demo.service;

import demo.domain.Location;
import demo.domain.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    @Autowired //todo : constructor inject
    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Location> saveRunningLocations(List<Location> locations) {
        return locationRepository.save(locations);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }

    @Override
    public Page<Location> findByUnitInfoRunningId(String id, Pageable pageable) {
        return locationRepository.findByUnitInfoRunningId(id, pageable);
    }
}
