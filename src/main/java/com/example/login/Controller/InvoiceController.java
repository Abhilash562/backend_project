package com.example.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.DTO.InvoiceDTO;
import com.example.login.Entity.Invoice;
import com.example.login.Service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
    private InvoiceService service;

    @PostMapping
    public ApiResponse<Invoice> create(@RequestBody InvoiceDTO dto) {
        return new ApiResponse<>(true, "Invoice created", service.createInvoice(dto));
    }

    @GetMapping
    public ApiResponse<List<Invoice>> getAll() {
        return new ApiResponse<>(true, "Invoice list", service.getAllInvoices());
    }

    @PutMapping("/{id}")
    public ApiResponse<Invoice> update(@PathVariable Long id, @RequestBody InvoiceDTO dto) {
        return new ApiResponse<>(true, "Invoice updated", service.updateInvoice(id, dto));
    }
}