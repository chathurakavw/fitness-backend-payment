package com.fitness.payment.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.payment.dto.PaymentDto;
import com.fitness.payment.dto.ResponseDto;
import com.fitness.payment.dto.UserDto;
import com.fitness.payment.entity.Payment;
import com.fitness.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.auth.user:NA}")
    private String apiAuthUrl;


    @PostMapping(path = "/create-payment")
    public ResponseDto<?> createPayment(@RequestBody PaymentDto paymentDto) throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.getForEntity(apiAuthUrl + paymentDto.getUserId(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        if (response.getBody() != null) {
            JsonNode root = mapper.readTree(response.getBody());
            UserDto userDto = mapper.treeToValue(root, UserDto.class);

            Payment payment = new Payment();
            payment.setPayAmount(paymentDto.getPayAmount());
            payment.setPayForMonth(paymentDto.getPayForMonth());
            payment.setPayDate(new Timestamp(System.currentTimeMillis()));
            payment.setUserId(userDto.getId());
            paymentRepository.save(payment);
            return ResponseDto.builder().status(true).message("Payment has been successfully created").build();
        } else {
            return ResponseDto.builder().status(false).message("User not found").build();
        }
    }

    @GetMapping(path = "/payment-history/{userId}")
    public ResponseDto<?> paymentList(@PathVariable int userId) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(apiAuthUrl + userId, String.class);
        if (response.getBody() != null) {
            List<Payment> paymentList = paymentRepository.findAllByUserId(userId);
            if (!paymentList.isEmpty()) {
                return ResponseDto.builder().status(true).data(paymentList).build();
            }
        } else {
            return ResponseDto.builder().status(false).message("User not found").build();
        }
        return null;
    }
}
