package com.example.login.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.Entity.Invoice;
import com.example.login.Entity.Payment;
import com.example.login.Repository.InvoiceRepository;
import com.example.login.Repository.PaymentRepository;

@Service
public class BillingService {
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
    private PaymentRepository paymentRepo;

	public Map<String, Object> getBillingSummary() {

        List<Invoice> invoices = invoiceRepo.findAll();
        List<Payment> payments = paymentRepo.findAll();

        double totalRevenue = invoices.stream().mapToDouble(Invoice::getTotalAmount).sum();

        double paid = payments.stream()
                .filter(p -> "PAID".equalsIgnoreCase(p.getStatus()))
                .mapToDouble(Payment::getAmount).sum();

        double pending = totalRevenue - paid;

        Map<String, Object> map = new HashMap<>();
        map.put("totalRevenue", totalRevenue);
        map.put("paidAmount", paid);
        map.put("pendingAmount", pending);

        return map;
    }

}
