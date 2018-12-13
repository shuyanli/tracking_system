package demo.service;

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    //todo 为什么不return void
    //答: repo里面的interface就定义的返回list
    List<Location> saveRunningLocations(List<Location> locations);

    void deleteAll();

    Page<Location> findByRunnerMovementType(String movementType, Pageable pageable);


    Page<Location> findByUnitInfoRunningId(String id, Pageable pageable);
}
