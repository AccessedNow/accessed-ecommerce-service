package com.accessed.ecommerce.service;

import com.accessed.ecommerce.model.dto.request.ProductRequest;
import com.accessed.ecommerce.model.dto.response.pagination.PaginationResponse;
import com.accessed.ecommerce.model.dto.response.product.ProductResponse;
import com.accessed.ecommerce.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductRequest request);
    Optional<Product> findById(String id);
    Map<String, Object> getProductById(String id);

    List<Product> getAllProducts();

    Product updateProduct(String id, ProductRequest request);

    Void deleteProduct(String id);

    PaginationResponse getTopProductsSold( int page, int size);

    Boolean productExists(String name);


}
