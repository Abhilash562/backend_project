package com.example.login.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.InvoiceDTO;
import com.example.login.DTO.InvoiceItemDTO;
import com.example.login.Entity.Invoice;
import com.example.login.Entity.InvoiceItem;
import com.example.login.Entity.InvoiceStatus;
import com.example.login.Repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository repo;

	public Invoice createInvoice(InvoiceDTO dto) {

	    Invoice inv = new Invoice();
	    inv.setInvoiceNumber("INV-" + System.currentTimeMillis());
	    inv.setVendorName(dto.getVendorName());
	    inv.setSupplierName(dto.getSupplierName());
	    inv.setInvoiceDate(dto.getInvoiceDate());
	    inv.setDueDate(dto.getDueDate());

	    inv.setStatus(dto.getStatus() != null ? dto.getStatus() : InvoiceStatus.DRAFT);

	    double totalAmount = 0;

	    List<InvoiceItem> items = new ArrayList<>();

	    for (InvoiceItemDTO itemDto : dto.getItems()) {

	        InvoiceItem item = new InvoiceItem();
	        item.setProductName(itemDto.getProductName());
	        item.setQuantity(itemDto.getQuantity());
	        item.setPrice(itemDto.getPrice());

	        double itemTotal = itemDto.getPrice() * itemDto.getQuantity();
	        item.setTotal(itemTotal);

	        totalAmount += itemTotal;
	        items.add(item);
	    }

	    inv.setItems(items);

	    double tax = dto.getTax() != null ? dto.getTax() : 0;

	    inv.setTax(tax);
	    inv.setTotalAmount(totalAmount + tax);

	    return repo.save(inv);
	}

    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }

    public Invoice updateInvoice(Long id, InvoiceDTO dto) {

        Invoice inv = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        inv.setVendorName(dto.getVendorName());
        inv.setSupplierName(dto.getSupplierName());
        inv.setInvoiceDate(dto.getInvoiceDate());
        inv.setDueDate(dto.getDueDate());
        inv.setStatus(dto.getStatus() != null ? dto.getStatus() : inv.getStatus());
        inv.setTax(dto.getTax() != null ? dto.getTax() : inv.getTax());

        // Remove items not in the new DTO
        inv.getItems().clear();

        double totalAmount = 0;
        for (InvoiceItemDTO itemDto : dto.getItems()) {
            InvoiceItem item = new InvoiceItem();
            item.setProductName(itemDto.getProductName());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            double itemTotal = itemDto.getPrice() * itemDto.getQuantity();
            item.setTotal(itemTotal);
            totalAmount += itemTotal;

            inv.getItems().add(item); // Add to existing managed collection
        }

        totalAmount += inv.getTax();
        inv.setTotalAmount(totalAmount);

        return repo.save(inv);
    }
}
