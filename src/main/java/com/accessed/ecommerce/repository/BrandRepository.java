package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BrandRepository extends MongoRepository<Brand, String> {
    boolean existsByName(String name);

    Brand findByName(String brandName);
}
