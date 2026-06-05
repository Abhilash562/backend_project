package com.example.login.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.login.Entity.Order;
import com.example.login.Entity.OrderStatus;

public interface OrderRepository extends JpaRepository <Order, Long>{

	Long countByOrderStatus(OrderStatus OrderStatus);

	
	@Query("SELECT o.productName, COUNT(o) " +
		       "FROM Order o " +
		       "GROUP BY o.productName " +
		       "ORDER BY COUNT(o) DESC")
    List<Object[]> findTopProducts();


    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = 'COMPLETED'")
    Long countCompletedOrders();

    @Query("SELECT SUM(o.quantity) FROM Order o WHERE o.orderStatus = 'COMPLETED'")
    Long totalCompletedQuantity();
}
