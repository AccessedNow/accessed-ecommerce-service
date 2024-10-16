package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.ProductVariant.ProductVariant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductVariantRepository extends MongoRepository<ProductVariant, String> {
}
