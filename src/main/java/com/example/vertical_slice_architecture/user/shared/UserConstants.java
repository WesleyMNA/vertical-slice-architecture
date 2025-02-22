package com.example.vertical_slice_architecture.user.shared;

public class UserConstants {

    private UserConstants() {
    }

    public static final String BASE_V1_URI = "/v1/users";

    public static final String USER_NOT_FOUND = "user not found";
    public static final String EMAIL_ALREADY_EXISTS = "email already exists";
    public static final String USER_DOES_NOT_HAVE_PERMISSION = "user does not have permission";
}
