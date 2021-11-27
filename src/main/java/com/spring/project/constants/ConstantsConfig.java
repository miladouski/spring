package com.spring.project.constants;

public class ConstantsConfig {

    public static final String DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/tickets";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";

    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String VIEWS_PATH = "/WEB-INF/views/";
    public static final String VIEWS_SUFFIX = ".html";

    public static final String COMPONENT_SCAN = "com.spring.project";
    public static final String GET_SERVLET_MAPPING = "/";
    public static final String METHOD_FILTER = "hiddenHttpMethodFilter";
    public static final String URL_PATTERN = "/*";
    public static final String ENCODING_FILTER = "characterEncoding";

    public static final String SECURITY_URL = "/routes/**";
    public static final String DEFAULT_URL = "/routes";
    public static final String LOGIN_PAGE = "/login";
    public static final String FAILURE_URL = "/login?error";
    public static final String USERNAME_PARAMETER = "username";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String LOGOUT_URL = "/login?logout";

}
