package com.zlkj.ftptest.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;


/**
 * @Description 所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
 * @Author sunny
 * @Date 2019-04-04 13:51
 */

//@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度20的定时任务线程池
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(20);
        taskRegistrar.setScheduler(executor);
    }
}