package com.oschina.sbom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SBOM应用程序入口
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class SbomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbomApplication.class, args);
	}

}
