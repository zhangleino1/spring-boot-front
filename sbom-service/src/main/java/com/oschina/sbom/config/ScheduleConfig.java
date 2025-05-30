package com.oschina.sbom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {
    // 定时任务配置，默认使用Spring的@Scheduled注解实现
}
