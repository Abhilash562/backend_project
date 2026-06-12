package com.example.login.DTO;

import lombok.Data;

@Data
public class InvoiceItemDTO {
    private String productName;
    private Integer quantity;
    private Double price;
}
