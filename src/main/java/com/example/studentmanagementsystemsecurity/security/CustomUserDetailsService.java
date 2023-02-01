package com.example.studentmanagementsystemsecurity.security;

import com.example.studentmanagementsystemsecurity.models.User;
import com.example.studentmanagementsystemsecurity.exceptions.ResourceNotFoundException;
import com.example.studentmanagementsystemsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email  " + username, 0l));
        return user;
    }
}
