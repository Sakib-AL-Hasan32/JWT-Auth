package com.jwt_auth.dto.response;

import com.jwt_auth.entity.enums.Stock;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductResponse(
        @NotBlank(message = "Product Name Can Not Be Blanked.")
        String name,

        @NotBlank(message = "Product Description Can Not Be Blanked.")
        String description,

        @NotNull(message = "Product Quantity Can Not Be Null.")
        @Min(value = 0, message = "Product Quantity Cannot Be Negative")
        Long quantity,

        @NotNull(message = "Product Price Can Not Be Null.")
        @Positive(message = "Product Price Must Be Greater Than 0")
        Long price,

        @NotNull(message = "Product Stock Can Not Be Null.")
        Stock stock
) {
}
