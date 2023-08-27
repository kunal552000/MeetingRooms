package com.meetingroom.Meeting.Room.Service;

import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import org.springframework.stereotype.Service;

@Service
public interface AttendeeService {

    public Attendee getAttendee(Long id);

    public void updateAttendee(Long id, AttendeeDto attendeeDTO);

    public void deleteAttendee(Long id);

}
