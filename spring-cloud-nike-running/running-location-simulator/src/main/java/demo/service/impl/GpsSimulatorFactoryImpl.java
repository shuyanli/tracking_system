package demo.service.impl;

import demo.model.GpsSimulatorRequest;
import demo.model.Leg;
import demo.model.Point;
import demo.service.GpsSimulatorFactory;
import demo.service.PositionService;
import demo.support.NavUtils;
import demo.task.LocationSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GpsSimulatorFactoryImpl implements GpsSimulatorFactory {

    @Autowired
    private PositionService positionService;

    private final AtomicLong instanceCounter = new AtomicLong();


    @Override
    public LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        final LocationSimulator locationSimulator = new LocationSimulator(gpsSimulatorRequest);
        locationSimulator.setId(instanceCounter.incrementAndGet());
        locationSimulator.setPositionService(this.positionService);
        final List<Point> points = NavUtils.decodePolyline(gpsSimulatorRequest.getPolyline());
        locationSimulator.setStartPoint(points.get(0)); //todo  老师用了points.iterator().next(), 为啥呢?
        return prepareGpsSimulator(locationSimulator, points);

    }

    @Override
    public LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points) {
        locationSimulator.setPositionInfo(null); // todo , 这里不是本来就是null了吗?

        final List<Leg> legs = createLegList(points);
        locationSimulator.setLegs(legs);
        locationSimulator.setStartPosition();
        return locationSimulator;

    }

    private List<Leg> createLegList(List<Point> points) {
        final List<Leg> result = new ArrayList<>();

        for (int i =0 ; i < result.size()-1; i++) {
            Leg curLeg = new Leg();
            curLeg.setId(i);
            curLeg.setStartPoint(points.get(i));
            curLeg.setEndPoint(points.get(i+1));
            curLeg.setHeading(NavUtils.getBearing(points.get(i), points.get(i+1)));
            curLeg.setLength(NavUtils.getDistance(points.get(i), points.get(i+1)));
            result.add(curLeg);
        }
        return result;

    }
}
