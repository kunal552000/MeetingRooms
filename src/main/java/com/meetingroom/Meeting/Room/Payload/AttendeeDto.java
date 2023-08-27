package com.meetingroom.Meeting.Room.Payload;

import lombok.Data;

@Data
public class AttendeeDto {

    private Long id;
    private String name;
    private String email;
    private int user_id;
    private boolean isHost;
    private boolean waitingRoom;

}
