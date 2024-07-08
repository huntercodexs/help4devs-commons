//package com.huntercodexs.demo.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Slf4j
//@Configuration
//public class DataSourceConfig {
//
//	@Primary
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource springDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "h2.datasource")
//	public DataSource h2DataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "mysql.datasource")
//	public DataSource mysqlDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "oraclelinux.datasource")
//	public DataSource oraclelinuxDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "sqlserver.datasource")
//	public DataSource sqlserverDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "mongodb.datasource")
//	public DataSource mongodbDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "postgres.datasource")
//	public DataSource postgresDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//}
