package com.example.login.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	List<Payment> findByInvoiceId(Long invoiceId);

}
