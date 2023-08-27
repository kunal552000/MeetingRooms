package com.meetingroom.Meeting.Room.Service.Impl;

import com.meetingroom.Meeting.Room.Entitty.Attendee;
import com.meetingroom.Meeting.Room.Payload.AttendeeDto;
import com.meetingroom.Meeting.Room.Repository.AttendeeRepo;
import com.meetingroom.Meeting.Room.Service.AttendeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttendeeServiceImpl implements AttendeeService {


    @Autowired
    private AttendeeRepo attendeeRepo;


    @Override
    public Attendee getAttendee(Long id) {
        Attendee attendee = attendeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Attendee not found"));
        return attendee;
    }

    @Override
    public void updateAttendee(Long id, AttendeeDto attendeeDTO) {

        Attendee attendee = attendeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Attendee not found"));
        attendee.setName(attendeeDTO.getName());
        attendee.setEmail(attendeeDTO.getEmail());
        attendee.setWaitingRoom(attendeeDTO.isWaitingRoom());
        attendeeRepo.save(attendee);
      return;
    }

    @Override
    public void deleteAttendee(Long id) {
        attendeeRepo.deleteById(id);
    }
}
