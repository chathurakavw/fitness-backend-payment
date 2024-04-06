package com.fitness.payment.repository;

import com.fitness.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByUserId(Integer userId);
}
