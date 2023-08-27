package com.meetingroom.Meeting.Room.Service;

import com.meetingroom.Meeting.Room.Entitty.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean singup(User user);
    boolean login(User user);

}
