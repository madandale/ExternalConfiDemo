package com.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import ExternalConfiguration.ExternalConfig.PropertyConfigurationController;

@SpringBootApplication
public class ExternalConfigDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExternalConfigDemoApplication.class, args);
	}

	
	@Bean
	public DataSource externalDataSource() {
		
		
			String filePath = "file:///Users/madandale/Documents/Silenium/config/application.properties";
		
		PropertyConfigurationController config = new PropertyConfigurationController(filePath);
		
		String classPath = config.getPropertyValue("spring.datasource.driverClassName",String.class);
		String url = config.getPropertyValue("spring.datasource.url",String.class);

		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(classPath);
	        dataSource.setUrl(url);
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
	        
		return dataSource;
	}
	
}
