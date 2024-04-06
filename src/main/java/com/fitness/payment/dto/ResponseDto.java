package com.fitness.payment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto<T> {
    private Boolean status;
    private String message;
    private T data;

}
