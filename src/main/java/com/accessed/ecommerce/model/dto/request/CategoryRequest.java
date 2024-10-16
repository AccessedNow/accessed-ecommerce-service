package com.accessed.ecommerce.model.dto.request;

import com.accessed.ecommerce.utils.enums.ProductType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {

    @NotNull(message = "Category name is required")
    String name;
    String parent;
    String icon;
    Integer level;
    List<String> children;
    List<ProductType> variantOptions;
    List<ProductType> specificationOptions;
    Boolean isFeatured;

}
