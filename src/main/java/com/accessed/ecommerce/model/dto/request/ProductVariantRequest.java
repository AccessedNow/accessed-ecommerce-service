package com.accessed.ecommerce.model.dto.request;

import com.accessed.ecommerce.model.entity.Specfication.ProductSpecification;
import com.accessed.ecommerce.model.entity.ProductVariant.VariantOption;
import lombok.*;


import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class ProductVariantRequest {

    private long quantityAvailable;
    private String sku;
    private BigDecimal sellingPrice;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private Long soldQuantity;
    private List<String> images;
    private List<VariantOption> variantOptions;

}
