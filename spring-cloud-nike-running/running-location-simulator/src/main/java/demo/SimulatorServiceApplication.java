package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SimulatorServiceApplication {
    static void main(String[] args){
        SpringApplication.run(SimulatorServiceApplication.class, args);
    }

    // spring 框架自带的线程池管理方法
    //todo: 1 这个返回类型和方程定义的类型好像不一致
    //      2. https://stackoverflow.com/questions/50839243/concurrent-scheduled-method-in-spring-boot
    //      上面网址提到了类似的实现, 没有太仔细地看 (@EnableScheduling vs @EnableAsync)
    @Bean
    public AsyncTaskExecutor taskExecutor(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;

    }
}
