package demo.service.impl;

import demo.model.CurrentPosition;
import demo.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j //todo  log的方便方法
public class PositionServiceImpl implements PositionService {

    private RestTemplate restTemplate = new RestTemplate(); //todo 文档中说这是springboot常用的rest方法,
    // 通过url获得rest, 可以实现CRUD的操作

    @Value("$(com.shuyan.running.location.distribution)")
    String runningLocationDistribution;

    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition, boolean exportPositionsToMessaging) {
        //String runningLocationDistribution = "http://running-location-distribution";
        assert (runningLocationDistribution.equals("http://running-location-distribution"));
        if (exportPositionsToMessaging) {
            log.info(String.format("Thread %s simulator is calling distribution API", Thread.currentThread().getId()));
            restTemplate.postForLocation(runningLocationDistribution+ "api/locations", currentPosition);
        }
    }
}
