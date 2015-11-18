package com.cmpe.bounswe2015group8.westory.back;

/**
 * Created by xyllan on 16.11.2015.
 */
public class ErrorCodes {
    // Login
    final String USER_DOES_NOT_EXIST_ERROR_CODE = "user-does-not-exist";
    final String WRONG_PASSWORD_ERROR_CODE = "wrong-password";

    // Register
    final int USERNAME_NULL = -101;
    final int PASSWORD_NULL = -102;
    final int EMAIL_NULL = -103;

    // Heritage
    final int HERITAGE_DOES_NOT_EXIST = -201;
    final int POST_DOES_NOT_EXIST = -202;
    final int FILE_UPLOAD_FAILED = -203;
}
