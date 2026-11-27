package com.vti.payment_api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "wallets")
@NoArgsConstructor 
@Getter 
@Setter 
public class Wallet {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="code", length = 10, nullable = false, unique = true )
    private String code;

    @Column (name="balance", precision = 18, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column (name ="created_at", nullable = false, updatable = false)
    @CreationTimestamp 
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp 
    private  LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private  User user;

    @OneToMany(mappedBy = "sentWallet")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receivedWallet")
    private List<Transaction> receivedTransactions;
}
