package com.oschina.sbom.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 示例定时任务类
 */
@Slf4j
@Component
public class DemoTask {

    /**
     * 示例定时任务，每30分钟执行一次
     */
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void demoTask() {
        log.info("定时任务执行：当前时间 {}", System.currentTimeMillis());
    }

    /**
     * 示例Cron定时任务，每天凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void demoCronTask() {
        log.info("Cron定时任务执行：当前时间 {}", System.currentTimeMillis());
    }
}
