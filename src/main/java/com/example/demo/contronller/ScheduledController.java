package com.example.demo.contronller;

import com.example.demo.timedtask.SendEmailRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@RestController
public class ScheduledController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private  ScheduledFuture<?>  future;
    private Map<String,ScheduledFuture<?>> futures = new HashMap<>();

    private int id = 0;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @RequestMapping(value = "/startCron",method =  RequestMethod.POST)
    public String startCron() {
        id += 1;

        future = threadPoolTaskScheduler.schedule(new SendEmailRunnable(id), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron()");
        futures.put(String.valueOf(id),future);
        return "startCron";
    }

    @RequestMapping(value = "/stopCron",method =  RequestMethod.GET)
    public String stopCron(Integer id) {

//        if (future != null) {
//            future.cancel(true);
//        }
        future =  futures.get(String.valueOf(id));
        if (futures.size() > 0 && null !=  future) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

    @RequestMapping("/changeCron10")
    public String startCron10() {

//        stopCron();// 先停止，在开启.
//        future = threadPoolTaskScheduler.schedule(new SendEmailRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

}
