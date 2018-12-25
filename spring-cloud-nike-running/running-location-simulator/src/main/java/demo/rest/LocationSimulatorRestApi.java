package demo.rest;


import demo.service.GpsSimulatorFactory;
import demo.service.impl.GpsSimulatorFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LocationSimulatorRestApi {
    //todo 1. 前面两个service都是注入的impl, 这里为什么注入interface也行?
    //     2. impl上面加上了@Service, 否则下面这句就会报错, 显示没有东西可以注入
    //     这一点与前两个service 一致
    @Autowired
    private GpsSimulatorFactory gpsSimulatorFactory;


    @Autowired
    private AsyncTaskExecutor taskExecutor; //来自application定义的bean



}
