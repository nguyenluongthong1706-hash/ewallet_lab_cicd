package com.vti.payment_api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.vti.payment_api.enums.ETransactionStatus;
import com.vti.payment_api.enums.ETransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "transactions")
@NoArgsConstructor 
@Setter 
@Getter
public class Transaction {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "amount", precision = 18, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "note", nullable = true)
    private String note;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ETransactionType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ETransactionStatus status;

    @Column (name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp 
    private LocalDateTime createdAt;

    @Column (name = "updated_at", nullable = false)
    @UpdateTimestamp 
    private  LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="sender_wallet_id", referencedColumnName = "id", nullable = true)
    private  Wallet sentWallet;

    @ManyToOne
    @JoinColumn(name="receiver_wallet_id", referencedColumnName = "id", nullable = true)
    private  Wallet receivedWallet;
}
