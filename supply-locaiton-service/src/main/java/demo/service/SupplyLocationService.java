package demo.service;

import demo.domain.SupplyLocation;
import org.springframework.data.geo.Point;

import java.util.List;

public interface SupplyLocationService {
    SupplyLocation findFirstByLocation(Point point);

    void delete();

    void save(List<SupplyLocation> locations);
}
