package com.oschina.sbom.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

/**
 * Druid数据源配置
 * 大部分配置已在application.yml中完成
 */
@Configuration
@AutoConfigureBefore(DruidDataSourceAutoConfigure.class)
public class DruidConfig {
    // Druid配置主要通过application.yml完成
    // 如果有特殊的配置需求，可以在这里添加额外的Bean
}
