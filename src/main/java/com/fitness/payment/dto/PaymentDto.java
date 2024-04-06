package com.fitness.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PaymentDto {
    private double payAmount;
    private Timestamp payForMonth;
    private Timestamp payDate;
    private Integer userId;
}
