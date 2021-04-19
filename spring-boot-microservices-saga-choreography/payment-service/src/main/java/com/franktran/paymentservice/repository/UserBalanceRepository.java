package com.franktran.paymentservice.repository;

import com.franktran.paymentservice.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
