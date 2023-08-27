package com.meetingroom.Meeting.Room.Controller;

import com.meetingroom.Meeting.Room.Config.JwtTokenHelper;
import com.meetingroom.Meeting.Room.Entitty.User;
import com.meetingroom.Meeting.Room.Payload.JwtAuthRequest;
import com.meetingroom.Meeting.Room.Payload.JwtAuthResponse;
import com.meetingroom.Meeting.Room.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request
    ) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(request.getUsername());


        String token = this.jwtTokenHelper.generateToken(userDetails);


        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);




        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try{
            this.authenticationManager.authenticate(authenticationToken);

        }catch (BadCredentialsException e){
            System.out.println("Invalid Details");
            throw  new Exception("Invalid Credentials");
        }catch (Exception e){
            System.out.println("Exception in authentication");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        System.out.println(user);
        boolean registeredUser =  this.userService.singup(user);
        if(registeredUser)
            return new ResponseEntity<>("User Created Successfully",HttpStatus.CREATED);


        return  new ResponseEntity<>("User Can't be Created",HttpStatus.CONFLICT);
    }

}
