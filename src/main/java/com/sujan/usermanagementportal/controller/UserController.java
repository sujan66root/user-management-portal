package com.sujan.usermanagementportal.controller;

import com.sujan.usermanagementportal.dto.LoginReqResDto;
import com.sujan.usermanagementportal.dto.RegisterRequest;
import com.sujan.usermanagementportal.dto.UserReqResDto;
import com.sujan.usermanagementportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqResDto loginReqResDto) {
        return ResponseEntity.ok(userService.loginUser(loginReqResDto));
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.updateUser(id, registerRequest));
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<?> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserReqResDto responseObj = userService.getMyInfo(username);
        return ResponseEntity.status(responseObj.getStatusCode()).body(responseObj);
    }
}
