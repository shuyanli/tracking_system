package demo.rest;

import demo.domain.SupplyLocation;
import demo.service.impl.SupplyLocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplyLocationRestController {

    private SupplyLocationServiceImpl supplyLocationService;

    @Autowired
    public SupplyLocationRestController(SupplyLocationServiceImpl supplyLocationService){
        this.supplyLocationService = supplyLocationService;
    }

    @RequestMapping(value = "/bulk/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    SupplyLocation findFirstByLocation(@RequestParam(value = "point") Point point){
        return supplyLocationService.findFirstByLocation(point);
    }

    @RequestMapping(value = "/bulk/supplyLocations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<SupplyLocation> locations){
        this.supplyLocationService.save(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(){
        this.supplyLocationService.delete();
    }
}
