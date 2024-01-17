package com.example.blog.serviceImpl;

import com.example.blog.dto.UserDto;
import com.example.blog.entity.Users;
import com.example.blog.enums.Role;
import com.example.blog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserDetailsService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Function<UserDto, Users> saveUser = (userDto)->{
        Users user = new ObjectMapper().convertValue(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(Role.ROLE_USER);
        return userRepository.save(user);
    };


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "+username));
    }

    public  void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void banUser(Long userId) {
        Optional<Users> userOptional = userRepository.findById(userId);
        Users user = userOptional.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
//        user.setBanned(true);
        userRepository.save(user);
    }

    public Users findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username" + username));
    }

    public Users findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
    }

}