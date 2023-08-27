package com.meetingroom.Meeting.Room.Constants;

public class AppConstants {

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    public static final String secret = "jwtTokenKey";


    public static final Integer NORMAL_USER = 502;
    public static final Integer ADMIN_USER = 1;

    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/**"
    };

    public static final String AUTHORIZATION_HEADER = "Authorization";


    public static final int MEETING_WINDOW_TIME = 45;

}
