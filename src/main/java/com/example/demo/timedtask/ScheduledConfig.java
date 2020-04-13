package com.example.demo.timedtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

//@Configuration
public class ScheduledConfig implements SchedulingConfigurer {


//    private TaskScheduler myThreadPoolTaskScheduler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
//        scheduledTaskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler);
        //可以实现动态调整定时任务的执行频率
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () ->{
                    System.out.println("添加任务内容" + Thread.currentThread().getId());
                } ,
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库动态获取执行周期
                    String cron = "0/5 * * * * ? ";
                    //2.2 合法性校验.
                    //                    if (StringUtils.isEmpty(cron)) {
                    //                        // Omitted Code ..
                    //                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
