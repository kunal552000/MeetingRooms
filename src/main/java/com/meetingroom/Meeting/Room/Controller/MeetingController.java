package com.meetingroom.Meeting.Room.Controller;


import com.meetingroom.Meeting.Room.Entitty.Meeting;
import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Payload.MeetingDto;
import com.meetingroom.Meeting.Room.Payload.TransferHostDto;
import com.meetingroom.Meeting.Room.Service.MeetingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meetingroom/v1/api/")
public class MeetingController {


    @Autowired
    private MeetingService meetingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createmeeting")
    public ResponseEntity<MeetingDto> createMeeting(@RequestBody MeetingDto createMeetingDto,@AuthenticationPrincipal User user) {

        Meeting meeting = meetingService.createMeeting(createMeetingDto, user.getId());

        AttendeeDto attendeeDto = new AttendeeDto();
        attendeeDto.setEmail(user.getUsername());
        attendeeDto.setWaitingRoom(meeting.isWaitingRoomEnabled());
        attendeeDto.setUser_id(user.getId());
        attendeeDto.setName(user.getName());
        meetingService.addAttendee(meeting.getId(),attendeeDto);

        MeetingDto meetingDto = this.modelMapper.map(meeting,MeetingDto.class);
        List<AttendeeDto> attendeeDtoList = meeting.getAttendees().stream().map(attendee -> modelMapper.map(attendee,AttendeeDto.class)).collect(Collectors.toList());
        meetingDto.setAttendeeDtoList(attendeeDtoList);
        return new ResponseEntity<>(meetingDto, HttpStatus.CREATED);
    }

    @PostMapping("/{meetingId}/attendees")
    public ResponseEntity<?> joinMeeting(@PathVariable String meetingId, @AuthenticationPrincipal User user) {

         AttendeeDto attendeeDto = meetingService.getCurrentAttendee(meetingId,user);
         meetingService.addAttendee(meetingId, attendeeDto);
        return new ResponseEntity<>("Attendee Joined Successfully", HttpStatus.CREATED);
    }
//    @DeleteMapping("/{meetingId}/attendees/{attendeeId}")
//    public ResponseEntity<Void> removeAttendee(@PathVariable String meetingId,
//                                               @PathVariable Long attendeeId,
//                                               @RequestParam int currentHostId) throws Exception {
//        meetingService.removeAttendee(meetingId, attendeeId, currentHostId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/{meetingId}/transfer-host")
//    public ResponseEntity<Void> transferHost(@PathVariable String meetingId,
//                                             @RequestBody TransferHostDto transferHostDto) throws Exception {
//        meetingService.transferHost(meetingId, transferHostDto.getNewHostId(), transferHostDto.getCurrentHostId());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
