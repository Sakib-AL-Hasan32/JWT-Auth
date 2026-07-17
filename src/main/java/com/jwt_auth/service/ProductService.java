package com.jwt_auth.service;

import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

import java.util.List;

public interface ProductService {
    ApiResponse<ProductResponse> addProduct(ProductRequest productRequest);
    ApiResponse<List<ProductResponse>> getAllProducts();
    ApiResponse<ProductResponse> getProductById (Long id);
}
