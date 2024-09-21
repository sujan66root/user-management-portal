package com.sujan.usermanagementportal.service;

import com.sujan.usermanagementportal.dto.*;
import com.sujan.usermanagementportal.model.Users;
import com.sujan.usermanagementportal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    //    method to register a user
    public RegisterRequest registerUser(RegisterRequest registerRequest) {
        RegisterRequest responseObj = new RegisterRequest();

        try {
            Users user = new Users();
            user.setUsername(registerRequest.getUsername());
            user.setCity(registerRequest.getCity());
            user.setRole(registerRequest.getRole().toUpperCase());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            Users usersInsertResult = usersRepo.save(user);
            if (usersInsertResult.getId() > 0) {
                responseObj.setStatusCode(200);
                responseObj.setMessage("User Registered Successfully");
                responseObj.setUsers(usersInsertResult);
            }
        } catch (Exception e) {
            responseObj.setError(e.getMessage());
            responseObj.setStatusCode(500);
        }
        return responseObj;
    }

    //    method to login a user
    public LoginReqResDto loginUser(LoginReqResDto loginReqResDto) {
        LoginReqResDto responseObj = new LoginReqResDto();
        try {
//            authenticate the user using authentication manager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReqResDto.getUsername(), loginReqResDto.getPassword()));
            Optional<Users> userOptional = usersRepo.findByUsername(loginReqResDto.getUsername());
            String token = jwtUtils.generateToken(loginReqResDto.getUsername());
            responseObj.setStatusCode(200);
            responseObj.setToken(token);
            responseObj.setRole(userOptional.get().getRole());//getting from userOptional
            responseObj.setExpirationTime("24Hrs");
            responseObj.setMessage("Successfully Logged In");
        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage(e.getMessage());
        }
        return responseObj;
    }

    //    method to get all the users
    public UsersListReqResDto getAllUsers() {
        UsersListReqResDto responseObj = new UsersListReqResDto();

        try {
            List<Users> result = usersRepo.findAll();
            if (!result.isEmpty()) {
                responseObj.setUserslist(result);
                responseObj.setStatusCode(200);
                responseObj.setMessage("Successful");
            } else {
                responseObj.setStatusCode(404);
                responseObj.setMessage("No users found");
            }
            return responseObj;
        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage("Error occurred: " + e.getMessage());
            return responseObj;
        }
    }

    //    method to get user by id
    public UserReqResDto getUserById(long id) {
        UserReqResDto responseObj = new UserReqResDto();
        try {
            Optional<Users> userById = usersRepo.findById(id);
            if (userById.isPresent()) {
                responseObj.setUsers(userById.get());
                responseObj.setStatusCode(200);
                responseObj.setMessage("Users with id '" + id + "' found successfully");
            }
        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage("Error occurred: " + e.getMessage());
        }
        return responseObj;
    }

    //    method to delete a user by id
    public ReqResDto deleteUser(long userId) {
        ReqResDto responseObj = new ReqResDto();
        try {
            Optional<Users> userOptional = usersRepo.findById(userId);
            if (userOptional.isPresent()) {
                usersRepo.deleteById(userId);
                responseObj.setStatusCode(200);
                responseObj.setMessage("User deleted successfully");
            } else {
                responseObj.setStatusCode(404);
                responseObj.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return responseObj;
    }

    //    method to update a user
    public UserReqResDto updateUser(long id, RegisterRequest updatedUser) {
        Optional<Users> userToUpdate = usersRepo.findById(id);
        UserReqResDto responseObj = new UserReqResDto();
        try {
            if (userToUpdate.isPresent()) {
                Users existingUser = userToUpdate.get();
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                Users savedUser = usersRepo.save(existingUser);
                responseObj.setUsers(savedUser);
                responseObj.setStatusCode(200);
                responseObj.setMessage("User updated successfully");
            } else {
                responseObj.setStatusCode(404);
                responseObj.setMessage("User not found for update");
            }
        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return responseObj;
    }

    //    method to get own info
    public UserReqResDto getMyInfo(String username) {
        UserReqResDto responseObj = new UserReqResDto();
        try {
            Optional<Users> userOptional = usersRepo.findByUsername(username);
            if (userOptional.isPresent()) {
                responseObj.setUsers(userOptional.get());
                responseObj.setStatusCode(200);
                responseObj.setMessage("successful");
            } else {
                responseObj.setStatusCode(404);
                responseObj.setMessage("User not found");
            }

        } catch (Exception e) {
            responseObj.setStatusCode(500);
            responseObj.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return responseObj;
    }
}

