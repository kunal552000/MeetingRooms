package com.meetingroom.Meeting.Room.WebRtc.Payload;

import lombok.Data;

@Data
public class SignallingService {
    private String id;
    private String type;
    private String sdp;
}
