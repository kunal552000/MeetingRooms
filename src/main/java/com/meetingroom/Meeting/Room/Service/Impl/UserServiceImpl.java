package com.meetingroom.Meeting.Room.Service.Impl;

import com.meetingroom.Meeting.Room.Constants.AppConstants;
import com.meetingroom.Meeting.Room.Entitty.Role;
import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Repository.RoleRepo;
import com.meetingroom.Meeting.Room.Repository.UserRepo;
import com.meetingroom.Meeting.Room.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public boolean singup(User user) {

        try{

            try{
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                Role role =  this.roleRepo.findById(AppConstants.ADMIN_USER).get();
                user.getRoles().add(role);
                userRepo.save(user);

            }catch (Exception e){
                log.info("unable to save User Info in Database");
                log.error("Exception: {}", e.getMessage());
                return false;
            }

        }catch (Exception e){
                log.error("UnExpected Error Occured while user Signup");
                log.error("Exception: {}", e.getMessage());
                return false;
        }
        log.info("User Created Successfully");
        return true;
    }

    @Override
    public boolean login(User user) {

//        try{
//            User user1 = userRepo.findByUserNamePass(user.getUsername(), user.getPassword());
//
//            if(user1.)
//        }catch (Exception e){
//
//        }

        return true;
    }
}
