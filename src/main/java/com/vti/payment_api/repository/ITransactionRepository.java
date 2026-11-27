package com.vti.payment_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.payment_api.entity.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySentWalletIdOrReceivedWalletIdOrderByCreatedAtDesc(Long senderId, Long receiverId);
}
