package com.neo4j_ecom.demo.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ProductPopularProjection {

    String getName();
    BigDecimal getSellingPrice();
    BigDecimal getDiscountedPrice();
    @Value("#{target.primaryImage}")
    String getImage();
    @Value("#{target.brand != null ? target.brand.name : null}")
    String getBrandName();
    Float getAvgRating();
    Long getSumSoldQuantity();
    @JsonIgnore
    List<ProductVariantProjection> getProductVariants();

    default BigDecimal getMinSellingPrice() {
        List<BigDecimal> sellingPrices = getProductVariants().stream()
                .map(ProductVariantProjection::getSellingPrice)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (getSellingPrice() != null) {
            sellingPrices.add(getSellingPrice());
        }

        return sellingPrices.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    default BigDecimal getMaxSellingPrice() {
        List<BigDecimal> sellingPrices = getProductVariants().stream()
                .map(ProductVariantProjection::getSellingPrice)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (getSellingPrice() != null) {
            sellingPrices.add(getSellingPrice());
        }

        return sellingPrices.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    // Custom logic to calculate min and max discounted prices
    default BigDecimal getMinDiscountedPrice() {
        List<BigDecimal> discountedPrices = getProductVariants().stream()
                .map(ProductVariantProjection::getDiscountedPrice)
                .filter(p -> p != null && p.compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());

        if (getDiscountedPrice() != null && getDiscountedPrice().compareTo(BigDecimal.ZERO) > 0) {
            discountedPrices.add(getDiscountedPrice());
        }

        return discountedPrices.stream().min(BigDecimal::compareTo).orElse(null);
    }

    default BigDecimal getMaxDiscountedPrice() {
        List<BigDecimal> discountedPrices = getProductVariants().stream()
                .map(ProductVariantProjection::getDiscountedPrice)
                .filter(p -> p != null && p.compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());

        if (getDiscountedPrice() != null && getDiscountedPrice().compareTo(BigDecimal.ZERO) > 0) {
            discountedPrices.add(getDiscountedPrice());
        }

        return discountedPrices.stream().max(BigDecimal::compareTo).orElse(null);
    }
}
