package com.example.studentmanagementsystemsecurity.controllers;

import com.example.studentmanagementsystemsecurity.payloads.UserDto;
import com.example.studentmanagementsystemsecurity.services.StudentService;
import com.example.studentmanagementsystemsecurity.services.UserService;
import com.example.studentmanagementsystemsecurity.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    //POST -  create user
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"/users"},method=RequestMethod.POST)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"/users/{userId}"},method=RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,@Valid @RequestBody UserDto userDto){
        UserDto updateUserDto = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updateUserDto);
    }

    //DELETE
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"/users/{userId}"},method=RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
    }

    //GET
    @RequestMapping(value={"/users/{userId}"},method=RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //GET All
    @RequestMapping(value={"/user"},method=RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}






