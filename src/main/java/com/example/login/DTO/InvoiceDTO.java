package com.example.login.DTO;

import java.time.LocalDate;
import java.util.List;

import com.example.login.Entity.InvoiceStatus;

import lombok.Data;

@Data
public class InvoiceDTO {
    
    private String vendorName;
    private String supplierName;
    private List<InvoiceItemDTO> items;
    private Double tax;
    private Double totalAmount;

    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private InvoiceStatus status;
}
