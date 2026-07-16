package com.jwt_auth.service.impl;

import com.jwt_auth.constants.ApiMessages;
import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.entity.Product;
import com.jwt_auth.repository.ProductRepository;
import com.jwt_auth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
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
}
