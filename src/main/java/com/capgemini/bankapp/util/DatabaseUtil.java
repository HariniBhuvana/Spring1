package com.capgemini.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ap.properties")
public class DatabaseUtil {

//	@Autowired
//	private Environment env;

//	public String getUrl() {
////		System.out.println(env.getProperty("db.url"));
//		return env.getProperty("db.url");
//	}
//
//	public String getUserName() {
//		return env.getProperty("db.username");
//	}
//
//	public String getPassword() {
//		return env.getProperty("db.password");
//	}
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	
	
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection;
			System.out.println(dbUrl);
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			System.out.println("Connected");
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
