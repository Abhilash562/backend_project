package com.example.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.Entity.Payment;
import com.example.login.Service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
    private PaymentService service;

	@GetMapping("/{invoiceNumber}")
    public ApiResponse<List<Payment>> getPaymentsByInvoiceNumber(@PathVariable String invoiceNumber) {
        List<Payment> payments = service.getPaymentDetails(invoiceNumber);
        return new ApiResponse<>(true, "Payment details", payments);
    }
}
