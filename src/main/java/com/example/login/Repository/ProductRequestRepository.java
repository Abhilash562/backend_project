package com.example.login.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.ProductRequest;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long>{

	List<ProductRequest> findByVendorName(String vendorName);

}
