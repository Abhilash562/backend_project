package com.example.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
