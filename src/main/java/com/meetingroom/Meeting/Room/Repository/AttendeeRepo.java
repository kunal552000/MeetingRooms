package com.meetingroom.Meeting.Room.Repository;

import com.meetingroom.Meeting.Room.Entitty.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepo extends JpaRepository<Attendee, Long> {


}
