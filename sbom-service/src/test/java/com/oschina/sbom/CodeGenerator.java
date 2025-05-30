package com.oschina.sbom;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:42606/sbom?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456")
                .globalConfig(builder -> builder
                        .author("zzzhanglei")
                        .outputDir("D:\\work\\oschina_code\\sbom_bi\\sbom-service" + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .dateType(DateType.TIME_PACK) // 设置时间类型策略
                )
                .packageConfig(builder -> builder
                        .parent("com.oschina.sbom")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> {
                    builder.addInclude("communities") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
