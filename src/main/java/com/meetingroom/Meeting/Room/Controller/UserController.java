package com.meetingroom.Meeting.Room.Controller;

import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetingroom/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<?> Signup()
    {
          return new ResponseEntity<>("Hi",HttpStatus.OK);

    }

}
