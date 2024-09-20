package com.sujan.usermanagementportal.repository;

import com.sujan.usermanagementportal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
