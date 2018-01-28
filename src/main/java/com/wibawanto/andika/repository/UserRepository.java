package com.wibawanto.andika.repository;

import com.wibawanto.andika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author andika
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(@Param("username") String username);
}
