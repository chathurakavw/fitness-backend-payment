package com.fitness.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pay_amount")
    private double payAmount;

    @Column(name = "pay_for_month")
    private Timestamp payForMonth;

    @Column(name = "pay_date")
    private Timestamp payDate;

    @Column(name = "user_id")
    private Integer userId;

}
