package com.example.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.Entity.BillingHistory;

public interface BillingHistoryRepository extends JpaRepository<BillingHistory, Long>{

}
