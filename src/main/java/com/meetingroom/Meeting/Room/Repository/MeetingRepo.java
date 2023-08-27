package com.meetingroom.Meeting.Room.Repository;

import com.meetingroom.Meeting.Room.Entitty.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepo extends JpaRepository<Meeting, String> {



}
