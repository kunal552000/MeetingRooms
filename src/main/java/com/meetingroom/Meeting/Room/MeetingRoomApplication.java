package com.meetingroom.Meeting.Room;

import com.meetingroom.Meeting.Room.Entitty.Role;
import com.meetingroom.Meeting.Room.Repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MeetingRoomApplication implements  CommandLineRunner{

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomApplication.class, args);
	}

	@Autowired
	RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		Role role = new Role();
		role.setId(1);
		role.setName("ADMIN");

		try{
			roleRepo.save(role);
		}catch (Exception e){
			System.out.println("Role Already Added");
		}

	}
}
