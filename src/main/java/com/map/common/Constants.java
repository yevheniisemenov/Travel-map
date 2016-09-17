package com.map.common;

/**
 * @author Andrew Pasika
 */
final public class Constants {

    private Constants() {
    }

    final public static class Web {
        public final static String HOME_URI = "/";
        public final static String LOGIN_URI = "/login";
        public final static String LOGOUT_URI = "/logout";
        public final static String REGISTER_URI = "/register";
        public final static String ERROR_URI = "/error";
        // Base path
        public final static String USER_BASE_PATH = "/user";
    }

    final public static class Common {
        public final static String ROLE_PREFIX = "ROLE_";
    }
}
