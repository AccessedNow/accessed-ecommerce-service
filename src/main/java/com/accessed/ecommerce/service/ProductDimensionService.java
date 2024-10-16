package com.accessed.ecommerce.service;

import com.accessed.ecommerce.model.entity.ProductDimension;

public interface ProductDimensionService {

    ProductDimension createProductDimension(ProductDimension request);
    ProductDimension updateProductDimension(ProductDimension current, ProductDimension request);
}
