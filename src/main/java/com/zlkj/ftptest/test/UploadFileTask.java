package com.zlkj.ftptest.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author sunny
 * @Date 2019-04-04 11:39
 */
//@Component
public class UploadFileTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedDelayString = "${jobs.fixedDelay}")
    @Scheduled(fixedDelayString = "2000")
    public void getTask1() {
        System.out.println("任务1,从配置文件加载任务信息，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${jobs.cron}")
    public void getTask2() {
        System.out.println("任务2,从配置文件加载任务信息，当前时间：" + dateFormat.format(new Date()));
    }

}
