package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.ProductDimension;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductDimensionRepository extends MongoRepository<ProductDimension, String> {

}
