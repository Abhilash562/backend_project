package com.example.login.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
	
	private Long id;
    private String productName;
    private String category;
    private Integer stock;
    private Double price;
    private String status;
    private String imageUrl;

}
