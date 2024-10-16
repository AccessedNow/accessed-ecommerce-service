package com.neo4j_ecom.demo.repository;

import com.neo4j_ecom.demo.model.dto.response.ProductCategoryResponse;
import com.neo4j_ecom.demo.model.dto.response.product.ProductPopular;
import com.neo4j_ecom.demo.model.entity.Product;
import com.neo4j_ecom.demo.model.entity.Review.ProductReview;
import com.neo4j_ecom.demo.model.projection.ProductPopularProjection;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByName(String name);

    @Aggregation(pipeline = {
            "{ $project: { " +
                    "_id: 1, " +
                    "name: 1, " +
                    "quantityAvailable: 1, " +
                    "primaryImage: 1, " +
                    "sellingPrice: { $ifNull: ['$sellingPrice', 0] }, " +
                    "soldQuantity: { $sum: { $ifNull: ['$productVariants.soldQuantity', 0] } }, " +
                    "categories: { $map: { input: '$categories', as: 'category', in: { $ifNull: ['$category.name', ''] } } }, " +
                    "avgRating: { $avg: { $ifNull: [{ $map: { input: '$productVariants', as: 'variant', in: { $ifNull: ['$variant.avgRating', 0] } } }, []] } }, " +
                    "createdAt: 1, " +
                    "updatedAt: 1 " +
                    "}}",
            "{ $sort : { updatedAt : -1 } }"
    })
    List<ProductCategoryResponse> findProductsOrderedByLatestUpdateTime();

    @Query("{ 'categories': { $elemMatch: { $eq: ?0 } } }")
    Page<Product> getAllProductsByCategoryId(ObjectId categoryId, Pageable pageable);

    @Query("{ 'productVariants._id' : ?0 }")
    Product findByVariantId(String variantId);

    Optional<Product> findById(String id);

    Page<Product> findByCategories_Id(String categoryId, PageRequest pageRequest);

    Page<Product> findByCategories_IdAndIdNot(String categoryId, String productId, PageRequest pageRequest);
    Page<Product> findByBrandId(String brandId, Pageable pageable);
    List<Product> findByBrandId(String brandId);

    @Query("{ 'brandId' : ?0, 'status' : ?1 }")
    List<Product> findByBrandIdWithStatus(String brandId, String status);

    List<Product> findAllById(String id);

    @Query("{ 'productId' : ?0 }")
    Page<ProductReview> findAllByProductId(String productId, PageRequest pageRequest);

    @Query("{}")
    Page<ProductPopularProjection> findPopularProducts(Pageable pageable);
}


