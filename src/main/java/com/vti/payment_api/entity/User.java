package com.vti.payment_api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "users")
@NoArgsConstructor 
@Getter 
@Setter 
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="full_name", length=100, nullable = false)
    private String fullName;

    @Column(name="email", length=150, nullable = false, unique = true)
    private String email;

    @Column(name="password", length=255, nullable = true)
    private String password;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp 
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp  
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user")
    private Wallet wallet;
}
