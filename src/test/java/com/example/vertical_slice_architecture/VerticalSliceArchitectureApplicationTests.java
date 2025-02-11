package com.example.vertical_slice_architecture;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class VerticalSliceArchitectureApplicationTests {

    @Test
    void verifyProjectStructure() {
        ApplicationModules modules = ApplicationModules.of(VerticalSliceArchitectureApplication.class);
        modules.forEach(System.out::println);
        modules.verify();
    }
}
