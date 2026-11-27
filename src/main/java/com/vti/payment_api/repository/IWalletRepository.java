package com.vti.payment_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vti.payment_api.entity.Wallet;

import jakarta.persistence.LockModeType;

public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    public boolean existsByUserId(Long userId);

    public  Optional<Wallet> findByUserId(Long userId);

    public  Optional<Wallet> findByCode(String code);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Wallet a where a.id = :id")
    Optional<Wallet> findByIdForUpdate(@Param("id") Long id);
}
