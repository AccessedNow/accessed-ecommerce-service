package com.accessed.ecommerce.repository;

import com.accessed.ecommerce.model.entity.Review.ReviewOption;
import com.accessed.ecommerce.utils.enums.ReviewType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewOptionRepository extends MongoRepository<ReviewOption, String> {
    ReviewOption findByType(ReviewType type);
}
