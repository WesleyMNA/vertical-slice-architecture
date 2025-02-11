package com.example.vertical_slice_architecture;

import com.example.vertical_slice_architecture.test_helpers.TestcontainersConfiguration;
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
