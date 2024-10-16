package com.accessed.ecommerce.model.dto.response.review;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.accessed.ecommerce.model.entity.Review.ProductReview;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse {

    float avgRating;

    int countOfReviews;

    List<ProductReview> reviews;
}
