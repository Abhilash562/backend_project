package com.example.login.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.Entity.Invoice;
import com.example.login.Entity.Payment;
import com.example.login.Repository.InvoiceRepository;
import com.example.login.Repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repo;
	
	@Autowired
	private InvoiceRepository invoiceRepo;

	public List<Payment> getPaymentDetails(String invoiceNumber) {
        Invoice invoice = invoiceRepo.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return repo.findByInvoiceId(invoice.getId());
    }

}
