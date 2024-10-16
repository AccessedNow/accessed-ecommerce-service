package com.accessed.ecommerce.model.projection;

import java.math.BigDecimal;

public interface ProductVariantProjection {
    BigDecimal getSellingPrice();
    BigDecimal getDiscountedPrice();
}
