package com.accessed.ecommerce.service;

import com.accessed.ecommerce.model.dto.request.ProductReviewRequest;
import com.accessed.ecommerce.model.dto.response.pagination.PaginationResponse;
import com.accessed.ecommerce.model.dto.response.review.ProductReviewResponse;
import com.accessed.ecommerce.model.entity.Review.ProductReview;

import java.util.List;

public interface ProductReviewService {

    ProductReview createReview(String productId, ProductReviewRequest review);

    ProductReview updateReview(String productId, String reviewId, ProductReviewRequest reviewRequest);

    PaginationResponse getAllReviewsByProductId(String productId, int page, int size, String sortBy, String sortOrder);

    PaginationResponse getAllReviewsByProductIdFilter(String productId, int rating, int page, int size);

    Void deleteReview(String productId, String reviewId);

}
