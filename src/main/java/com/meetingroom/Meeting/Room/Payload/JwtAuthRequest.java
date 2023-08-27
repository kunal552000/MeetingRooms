package com.meetingroom.Meeting.Room.Payload;


import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;
}
