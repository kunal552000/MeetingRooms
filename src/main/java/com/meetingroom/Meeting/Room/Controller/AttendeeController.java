package com.meetingroom.Meeting.Room.Controller;

import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Service.AttendeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("meetingroom/vi/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{attendeeId}")
    public ResponseEntity<AttendeeDto> getAttendee(@PathVariable Long attendeeId) {
        Attendee attendee = attendeeService.getAttendee(attendeeId);
        AttendeeDto attendeeDto =  this.modelMapper.map(attendee,AttendeeDto.class);
        return new ResponseEntity<>(attendeeDto, HttpStatus.OK);
    }

    @PutMapping("/{attendeeId}")
    public ResponseEntity<Void> updateAttendee(@PathVariable Long attendeeId, @RequestBody AttendeeDto updateAttendeeDto) {
        attendeeService.updateAttendee(attendeeId, updateAttendeeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
