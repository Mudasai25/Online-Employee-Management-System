package com.example;

import org.springframework.boot.SpringApplication;

public class TestEmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeeManagementSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
