package com.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.sources.JDBCConfigurationSource;

import ExternalConfiguration.ExternalConfig.JDBCConfigController;
import ExternalConfiguration.ExternalConfig.LogConfigurationController;
import ExternalConfiguration.ExternalConfig.PropertyConfigurationController;




@RestController

public class ConfigReadController {

	
	@Autowired
	public DataSource dataSource;
	
	@RequestMapping(value ="/readConfigProperty")
	public Map<String, String> readConfigFile() {
		
		String filePath = "file:///Users/madandale/Documents/Silenium/config/application.properties";
		
		PropertyConfigurationController config = new PropertyConfigurationController(filePath);
		System.out.print("value is == "+ config.readConfigByKey("app.message"));
		return config.readConfigByKey("app.message");
	}

	@RequestMapping(value ="/readlogConfigProperty")
	public Map<String, String> readLogConfigFile() {
		
	String filePath = "file:///Users/madandale/Documents/Silenium/config/log.properties";
		
		LogConfigurationController config = new LogConfigurationController(filePath);
		System.out.print("value is == "+ config.readConfigByKey("app.message"));
		return config.readConfigByKey("app.message");
	}
	
	@GetMapping("/readJDBCConfigProperty")
	public Map<String, String> ReadConfigFile() {
		
		
//		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	        dataSource.setDriverClassName("org.h2.Driver");
//	        dataSource.setUrl("jdbc:h2:file:~/data/config");
//	        dataSource.setUsername("sa");
//	        dataSource.setPassword("");
//		  
			
		JDBCConfigController config = new JDBCConfigController(this.dataSource,"select distinct property_key, property_value from MySiteProperties",
                "property_key", "property_value");
			System.out.print("value is == "+ config.readJDBCPropertyByKey("app.message"));
			return config.readJDBCPropertyByKey("app.message");
	}
}
