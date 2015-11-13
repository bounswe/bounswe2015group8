package api;

/**
 * Created by Goktug on 12.11.2015.
 */
public interface ErrorCodes {
    // Login
    final int USER_DOES_NOT_EXIST_ERROR_CODE = -1;
    final int WRONG_PASSWORD_ERROR_CODE = -2;

    // Register
    final int USERNAME_NULL = -101;
    final int PASSWORD_NULL = -102;
    final int EMAIL_NULL = -103;

    // Heritage
    final int HERITAGE_DOES_NOT_EXIST = -201;
    final int POST_DOES_NOT_EXIST = -202;
    final int FILE_UPLOAD_FAILED = -203;

}
