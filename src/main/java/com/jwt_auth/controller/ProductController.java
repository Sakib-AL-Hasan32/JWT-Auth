package com.jwt_auth.controller;

import com.jwt_auth.constants.ApiEndpoints;
import com.jwt_auth.dto.request.ProductRequest;
import com.jwt_auth.dto.request.SearchProductRequest;
import com.jwt_auth.dto.response.ProductResponse;
import com.jwt_auth.dto.response.common.ApiResponse;
import com.jwt_auth.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Product.BASE)
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiEndpoints.Product.ADD_PRODUCT)
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts () {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getAllProducts());
    }

    @GetMapping(ApiEndpoints.Product.ID)
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductById(id));
    }

    @PostMapping(ApiEndpoints.Product.NAME)
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductByName (@Valid @RequestBody SearchProductRequest searchProductRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(searchProductRequest));
    }

    @PutMapping(ApiEndpoints.Product.ID)
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct (@Valid @RequestBody ProductRequest productRequest,
                                                                       @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productRequest, id));
    }
}
