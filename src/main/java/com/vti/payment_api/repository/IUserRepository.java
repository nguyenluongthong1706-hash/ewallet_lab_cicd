package com.vti.payment_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.payment_api.entity.User;

public interface IUserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
}
