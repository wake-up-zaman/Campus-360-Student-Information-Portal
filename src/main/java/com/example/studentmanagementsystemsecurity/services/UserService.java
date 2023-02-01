package com.example.studentmanagementsystemsecurity.services;
import com.example.studentmanagementsystemsecurity.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
