package com.example.login.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

}
