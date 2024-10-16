package com.accessed.ecommerce.model.mapper;

import com.accessed.ecommerce.model.dto.request.ProductVariantRequest;
import com.accessed.ecommerce.model.entity.Product;
import com.accessed.ecommerce.model.entity.ProductVariant.ProductVariant;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantMapper {

    public ProductVariant toEntity(ProductVariantRequest productVariantRequest) {

        ProductVariant productVariant = new ProductVariant();
        productVariant.setQuantityAvailable(productVariantRequest.getQuantityAvailable());
        productVariant.setSku(productVariantRequest.getSku() == null ? null : productVariantRequest.getSku().toUpperCase());
        productVariant.setSellingPrice(productVariantRequest.getSellingPrice());
        productVariant.setImages(productVariantRequest.getImages());
        productVariant.setVariantOptions(productVariantRequest.getVariantOptions());

        if (productVariantRequest.getOriginalPrice() != null) {
            productVariant.setOriginalPrice(productVariantRequest.getOriginalPrice());
        }

        if (productVariantRequest.getDiscountedPrice() != null) {
            productVariant.setDiscountedPrice(productVariantRequest.getDiscountedPrice());
        }

        if (productVariantRequest.getSoldQuantity() != null) {
            productVariant.setSoldQuantity(productVariantRequest.getSoldQuantity());
        }

        return productVariant;
    }
}
