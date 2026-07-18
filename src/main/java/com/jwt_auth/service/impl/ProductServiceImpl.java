package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.PermissionNames;
import com.jwt_auth.dto.request.NameRequest;
import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.entity.Product;
import com.jwt_auth.exception.ResourceNotFoundException;
import com.jwt_auth.repository.ProductRepository;
import com.jwt_auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.ADD_PRODUCT + "')")
    public ApiResponse<ProductResponse> addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .quantity(productRequest.quantity())
                .price(productRequest.price())
                .stock(productRequest.stock())
                .build();
        productRepository.save(product);

        return ApiResponse.<ProductResponse>builder()
                .data(new ProductResponse(
                        product.getId(),
                        productRequest.name(),
                        productRequest.description(),
                        productRequest.quantity(),
                        productRequest.price(),
                        productRequest.stock()
                ))
                .message(ApiMessages.Success.PRODUCT_ADDED)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_ALL_PRODUCT + "')")
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<Product>  products = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product : products) {
            ProductResponse productResponse = new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getStock()
            );
            productResponseList.add(productResponse);
        }
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productResponseList)
                .message(ApiMessages.Success.FETCHED_ALL_PRODUCTS)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_PRODUCT_BY_ID + "')")
    public ApiResponse<ProductResponse> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.PRODUCT_NOT_FOUND));
        return ApiResponse.<ProductResponse>builder()
                .data(new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getStock())
                )
                .message(ApiMessages.Success.PRODUCT_FETCHED_BY_ID)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.GET_PRODUCT_BY_NAME + "')")
    public ApiResponse<List<ProductResponse>> getProductByName(NameRequest searchProductRequest) {
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(searchProductRequest.name());
        if(productList.isEmpty()) {
            throw new ResourceNotFoundException(ApiMessages.Error.PRODUCT_NOT_FOUND);
        }
        List<ProductResponse> responseList = new ArrayList<>();
        for(Product product : productList) {
            ProductResponse productResponse = new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getStock()
            );
            responseList.add(productResponse);
        }
        return ApiResponse.<List<ProductResponse>>builder()
                .data(responseList)
                .message(ApiMessages.Success.PRODUCT_FETCHED_BY_NAME)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.UPDATE_PRODUCT + "')")
    public ApiResponse<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.PRODUCT_NOT_FOUND));
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setQuantity(productRequest.quantity());
        product.setPrice(productRequest.price());
        product.setStock(productRequest.stock());
        productRepository.save(product);
        return ApiResponse.<ProductResponse>builder()
                .data(new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getStock()
                ))
                .message(ApiMessages.Success.PRODUCT_UPDATED)
                .build();
    }

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.DELETE_PRODUCT + "')")
    public ApiResponse<Map<String, Long>> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ApiMessages.Error.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
        return ApiResponse.<Map<String, Long>>builder()
                .data(Map.of("Product ID: ", product.getId()))
                .message(ApiMessages.Success.PRODUCT_DELETED)
                .build();
    }
}
