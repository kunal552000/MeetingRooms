package com.meetingroom.Meeting.Room.Payload;

import lombok.Data;

@Data
public class TransferHostDto {

    int NewHostId;
    int CurrentHostId;
}
