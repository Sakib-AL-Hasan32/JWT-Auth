package com.jwt_auth.service;

import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

public interface ProductService {
    ApiResponse<ProductResponse> addProduct(ProductRequest productRequest);
}
