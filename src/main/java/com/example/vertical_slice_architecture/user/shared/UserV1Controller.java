package com.example.vertical_slice_architecture.user.shared;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(UserConstants.BASE_V1_URI)
public @interface UserV1Controller {
}
