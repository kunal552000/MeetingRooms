package com.meetingroom.Meeting.Room.Repository;

import com.meetingroom.Meeting.Room.Entitty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {


    @Query(value = "SELECT * FROM user_table WHERE username =:username AND password =:password", nativeQuery = true)
    User findByUserNamePass(String username, String password);
    @Query(value = "SELECT * FROM user_table WHERE username =:username", nativeQuery = true)
    User findByUsername(String username);
}
