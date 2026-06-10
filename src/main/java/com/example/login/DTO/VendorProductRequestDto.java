package com.example.login.DTO;

import lombok.Data;

@Data
public class VendorProductRequestDto {
	
	private Long productId;
    private Integer quantity;
    private String message;

}
