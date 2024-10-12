package com.neo4j_ecom.demo.model.projection;

import java.math.BigDecimal;

public interface ProductVariantProjection {
    BigDecimal getSellingPrice();
    BigDecimal getDiscountedPrice();
}
