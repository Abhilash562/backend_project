package com.example.login.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.Entity.OrderStatus;
import com.example.login.Repository.OrderRepository;

@Service
public class ReportService {
	
	@Autowired
    private OrderRepository orderRepository;

    public Map<String, Long> getDashboardSummary() {
        Map<String, Long> summary = new HashMap<>();

        summary.put("totalOrders", orderRepository.count());
        summary.put("pendingOrders", orderRepository.countByOrderStatus(OrderStatus.PENDING));
        summary.put("approvedOrders", orderRepository.countByOrderStatus(OrderStatus.APPROVED));
        summary.put("completedOrders", orderRepository.countByOrderStatus(OrderStatus.COMPLETED));
        summary.put("rejectedOrders", orderRepository.countByOrderStatus(OrderStatus.REJECTED));
        summary.put("cancelledOrders", orderRepository.countByOrderStatus(OrderStatus.CANCELLED));

        return summary;
    }
    
    public List<Map<String, Object>> getTopProducts() {

        List<Object[]> results = orderRepository.findTopProducts();

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("productName", row[0]);
            map.put("totalOrders", row[1]);
            response.add(map);
        }

        return response;
    }
    
    public Map<String, Object> getSalesSummary() {

        Long completedOrders = orderRepository.countCompletedOrders();
        Long totalQuantity = orderRepository.totalCompletedQuantity();

        double avgOrderValue = (completedOrders == 0 || totalQuantity == null)
                ? 0
                : (double) totalQuantity / completedOrders;

        Map<String, Object> response = new HashMap<>();
        response.put("totalCompletedOrders", completedOrders);
        response.put("totalSalesQuantity", totalQuantity);
        response.put("averageOrderValue", avgOrderValue);

        return response;
    }

}
