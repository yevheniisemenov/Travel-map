package com.map.common;

/**
 * @author Andrew Pasika
 */
public interface Constants {

    interface Web {
        String HOME_URI = "/";
        String LOGIN_URI = "/login";
        String LOGOUT_URI = "/logout";
        String REGISTER_URI = "/register";
        String ERROR_URI = "/error";
        // Base path
        String USER_BASE_PATH = "/user";
    }

    interface Common {
        String ROLE_PREFIX = "ROLE_";
    }
}
