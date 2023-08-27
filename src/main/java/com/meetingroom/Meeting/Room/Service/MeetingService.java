package com.meetingroom.Meeting.Room.Service;


import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Entitty.Meeting;
import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Payload.MeetingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingService {

    Meeting createMeeting(MeetingDto meetingDTO, int HostId);
    Meeting getMeeting(String id);
    List<Attendee> getAttendees(String meetingId);
    void addAttendee(String meetingId, AttendeeDto attendeeDTO);
    void removeAttendee(String meetingId, Long attendeeId, int hostId) throws Exception;
    void transferHost(String meetingId, int newHostId, int currentHostId) throws Exception;


    AttendeeDto getCurrentAttendee(String meetingId, User user);
}
