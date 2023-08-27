package com.meetingroom.Meeting.Room.Service.Impl;

import com.meetingroom.Meeting.Room.Constants.AppConstants;
import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Entitty.Meeting;
import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Payload.MeetingDto;
import com.meetingroom.Meeting.Room.Repository.AttendeeRepo;
import com.meetingroom.Meeting.Room.Repository.MeetingRepo;
import com.meetingroom.Meeting.Room.Service.AttendeeService;
import com.meetingroom.Meeting.Room.Service.MeetingService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    AttendeeService attendeeService;

    @Autowired
    MeetingRepo meetingRepo;
    @Autowired
     ModelMapper modelMapper;

    @Override
    public Meeting createMeeting(MeetingDto meetingDTO, int hostId) {
        Meeting meeting = this.modelMapper.map(meetingDTO,Meeting.class);
        meeting.setId(UUID.randomUUID().toString().substring(0, 10));
        LocalDateTime startTime = LocalDateTime.now();
        meeting.setStartTime(startTime);
        meeting.setEndTime(startTime.plusMinutes(AppConstants.MEETING_WINDOW_TIME));
        meeting.setHostId(hostId);
        meeting.setActive(true);
        return meetingRepo.save(meeting);
    }
    @Override
    public Meeting getMeeting(String id) {

        Meeting meeting = this.meetingRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Meeting not found"));

        if(!meeting.isActive()){
            throw new EntityNotFoundException("Meeting not found");
        }
        return meeting;
    }

    @Override
    public List<Attendee> getAttendees(String meetingId) {

        Meeting meeting = getMeeting(meetingId);
        return meeting.getAttendees();

    }

    @Override
    public void addAttendee(String meetingId, AttendeeDto attendeeDTO) {
        Meeting meeting = getMeeting(meetingId);

         Attendee attendee = this.modelMapper.map(attendeeDTO,Attendee.class);
         meeting.getAttendees().add(attendee);
         attendee.setMeeting(meeting);

         meetingRepo.save(meeting);
    }

    @Override
    public void removeAttendee(String meetingId, Long attendeeId, int hostId) throws Exception {


        Meeting meeting = getMeeting(meetingId);

        if(meeting.getHostId() == (hostId)){
            Attendee attendee = attendeeService.getAttendee(attendeeId);

            meeting.getAttendees().remove(attendee);
            attendeeService.deleteAttendee(attendeeId);
            meetingRepo.save(meeting);
        }else{

            throw new Exception("Only the host can remove attendees");
        }
    }

    @Override
    public void transferHost(String meetingId, int newHostId, int currentHostId) throws Exception {

            Meeting meeting = getMeeting(meetingId);
            if(meeting.getHostId() == (currentHostId)){

                Attendee newHost =  meeting.getAttendees().stream()
                        .filter(attendee -> attendee.getId().equals(newHostId))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Attendee not found"));

                meeting.setHostId(newHostId);
                meetingRepo.save(meeting);
            }else{
                throw new Exception("Only the host can change Host");
            }
    }

    @Override
    public AttendeeDto getCurrentAttendee(String meetingId, User user) {

        Meeting meeting = getMeeting(meetingId);
        AttendeeDto attendeeDto = new AttendeeDto();
        attendeeDto.setEmail(user.getUsername());
        attendeeDto.setWaitingRoom(meeting.isWaitingRoomEnabled());
        attendeeDto.setUser_id(user.getId());
        attendeeDto.setName(user.getName());
        return  attendeeDto;
    }
}
