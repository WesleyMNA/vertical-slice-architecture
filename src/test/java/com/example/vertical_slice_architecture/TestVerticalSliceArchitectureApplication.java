package com.example.vertical_slice_architecture;

import org.springframework.boot.SpringApplication;

public class TestVerticalSliceArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.from(VerticalSliceArchitectureApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
