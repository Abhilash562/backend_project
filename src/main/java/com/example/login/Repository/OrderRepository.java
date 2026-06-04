package com.example.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.Order;

public interface OrderRepository extends JpaRepository <Order, Long>{

}
