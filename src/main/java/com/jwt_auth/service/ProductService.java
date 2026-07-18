package com.jwt_auth.service;

import com.jwt_auth.dto.request.NameRequest;
import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ApiResponse<ProductResponse> addProduct(ProductRequest productRequest);
    ApiResponse<List<ProductResponse>> getAllProducts();
    ApiResponse<ProductResponse> getProductById (Long id);
    ApiResponse<List<ProductResponse>> getProductByName (NameRequest searchProductRequest);
    ApiResponse<ProductResponse> updateProduct (ProductRequest productRequest, Long id);
    ApiResponse<Map<String, Long>> deleteProduct(Long id);
}
