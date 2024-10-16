package com.accessed.ecommerce.model.dto.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPopular {

    @Id
    String id;
    String name;
    String image;
    String brandName;
    Float avgRating;
    Long sumSoldQuantity;
    BigDecimal minSellingPrice;
    BigDecimal maxSellingPrice;
    BigDecimal minDiscountedPrice;
    BigDecimal maxDiscountedPrice;

    public BigDecimal getMinSellingPrice() {
        return minSellingPrice;
    }

    public BigDecimal getMaxSellingPrice() {
        return maxSellingPrice;
    }

    public BigDecimal getMinDiscountedPrice() {
        return minDiscountedPrice;
    }

    public BigDecimal getMaxDiscountedPrice() {
        return maxDiscountedPrice;
    }
}
