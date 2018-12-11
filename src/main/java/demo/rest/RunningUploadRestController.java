package demo.rest;


import demo.domain.Location;
import demo.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningUploadRestController {

    private LocationServiceImpl locationService;

    @Autowired
    public RunningUploadRestController(LocationServiceImpl locationService){
        this.locationService = locationService;
    }


//    public void upLoad(List<Location> locationList){
//        locationService.saveRunningLocations(locationList);
//    }

    //todo 使用@RequestBody来引入数据
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upLoad(@RequestBody List<Location> locationList){
        locationService.saveRunningLocations(locationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purge(){
        locationService.deleteAll();
    }


    //todo 将大括号中的数作为参数传入返程argument, 使用@PathVariable标记
    //将url传入的parameter传入page和size, 就变成了 get /running/STOPPED?page=1&&size=5
    //page=1中, page来自param name = xxx, 1 为传入方程的param存入page variable
    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Location> findByRunnerMovementType(@PathVariable String movementType,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "size") int size){
        return locationService.findByRunnerMovementType(movementType, new PageRequest(page, size));//todo 看看分页怎么存的
    }

    @RequestMapping(value = "/running/runningId/{runningId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Location> findByUnitInfoRunningId(@PathVariable String runningId,
                                                  @RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size){
        return locationService.findByUnitInfoRunningId(runningId, new PageRequest(page, size));
    }



}
