package com.example.vertical_slice_architecture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class VerticalSliceArchitectureApplicationTests {

	@Test
	void contextLoads() {
	}

}
