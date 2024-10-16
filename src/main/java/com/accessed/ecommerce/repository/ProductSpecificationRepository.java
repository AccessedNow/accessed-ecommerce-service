package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.Specfication.ProductSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSpecificationRepository extends MongoRepository<ProductSpecification, String> {
}
