package com.meetingroom.Meeting.Room.Payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MeetingDto {
    private String id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int hostId;
    private boolean waitingRoomEnabled;
    private boolean isActive;

    private List<AttendeeDto> attendeeDtoList;
}
