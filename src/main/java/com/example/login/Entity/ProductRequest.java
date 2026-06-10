package com.example.login.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendor_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorName;

    private Long productId;

    private String productName;

    private Integer quantity;

    private String message;

    private String status; // Pending / Approved / Rejected

    private LocalDate requestedDate;
}