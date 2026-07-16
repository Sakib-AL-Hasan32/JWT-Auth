package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.constants.PermissionNames;
import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.entity.Product;
import com.jwt_auth.repository.ProductRepository;
import com.jwt_auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
