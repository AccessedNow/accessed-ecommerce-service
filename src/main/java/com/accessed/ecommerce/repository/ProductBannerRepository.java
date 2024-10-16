package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.ProductBanner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface ProductBannerRepository extends MongoRepository<ProductBanner, String> {
    List<ProductBanner> findAllByOrderByIdDesc(int quantity);

//    @Query("MATCH (p:ProductBanner) RETURN p LIMIT $quantity")
//    List<ProductBanner> getBannersByQuantity(int quantity);
}
