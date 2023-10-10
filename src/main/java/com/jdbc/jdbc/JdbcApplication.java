package com.jdbc.jdbc;

import com.jdbc.jdbc.dao.Userdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jdbc.jdbc.Controller")
@ComponentScan(basePackages = "com.jdbc.jdbc")
public class JdbcApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(JdbcApplication.class, args);
	}
}
