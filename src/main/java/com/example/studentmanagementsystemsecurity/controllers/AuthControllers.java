package com.example.studentmanagementsystemsecurity.controllers;

import com.example.studentmanagementsystemsecurity.exceptions.ApiException;
import com.example.studentmanagementsystemsecurity.payloads.JwtAuthRequest;
import com.example.studentmanagementsystemsecurity.payloads.UserDto;
import com.example.studentmanagementsystemsecurity.security.JwtAuthResponse;
import com.example.studentmanagementsystemsecurity.security.JwtTokenHelper;
import com.example.studentmanagementsystemsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
@Component
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class AuthControllers {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManagers;

    @Autowired
    private UserService userService;


    //User Login
    @RequestMapping(value={"/auth/login"},method= RequestMethod.POST)
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
            this.authenticationManagers.authenticate(usernamePasswordAuthenticationToken);

    }

    //User Registration
    @RequestMapping(value={"/auth/register"},method=RequestMethod.POST)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
