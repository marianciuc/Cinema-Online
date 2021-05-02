package com.crudexample.online.repository;

import com.crudexample.online.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    boolean existsByUsername(String username);

}
