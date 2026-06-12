package com.example.login.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long invoiceId;
    private Double amount;
    private String paymentMethod;
    private String transactionId;
    private String status;
    private LocalDate paymentDate;
}