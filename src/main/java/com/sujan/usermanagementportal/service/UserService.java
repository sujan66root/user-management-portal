package com.sujan.usermanagementportal.service;

import com.sujan.usermanagementportal.model.Users;
import com.sujan.usermanagementportal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //method to find a user
    public Optional<Users> findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }
//    method to register a user
    public Users registerUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }
}
