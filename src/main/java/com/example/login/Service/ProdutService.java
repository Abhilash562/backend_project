package com.example.login.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.ProductRequestDto;
import com.example.login.DTO.ProductResponseDto;
import com.example.login.Entity.Product;
import com.example.login.Exceptions.ResourceNotFoundException;
import com.example.login.Repository.ProductRepository;

@Service
public class ProdutService {
	
	@Autowired 
	private ProductRepository repository;
	
	public ProductResponseDto addProduct(ProductRequestDto dto) {

        Product product = Product.builder()
                .productName(dto.getProductName())
                .category(dto.getCategory())
                .stock(dto.getStock())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .imageUrl(dto.getImageUrl())
                .build();

        Product savedProduct = repository.save(product);

        return mapToResponse(savedProduct);
    }

    // Get All Products
    public List<ProductResponseDto> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Product By ID
    public ProductResponseDto getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with ID: " + id));

        return mapToResponse(product);
    }

    // Update Product
    public ProductResponseDto updateProduct(
            Long id,
            ProductRequestDto dto) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with ID: " + id));

        product.setProductName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());
        product.setImageUrl(dto.getImageUrl());

        Product updated = repository.save(product);

        return mapToResponse(updated);
    }

    // Delete Product
    public void deleteProduct(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with ID: " + id));

        repository.delete(product);
    }

    // Entity -> DTO
    private ProductResponseDto mapToResponse(Product product) {

        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .stock(product.getStock())
                .price(product.getPrice())
                .status(product.getStatus())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
