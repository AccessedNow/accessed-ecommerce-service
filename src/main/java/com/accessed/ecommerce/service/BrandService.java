package com.accessed.ecommerce.service;

import com.accessed.ecommerce.model.dto.response.pagination.PaginationResponse;
import com.accessed.ecommerce.model.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand createBrand(Brand brand);

    List<Brand> getBrands();

    Brand updateBrand(String id, Brand brand);

    Void deleteBrand(String id);

    Brand revertBrand(String id);

    Brand findBrandById(String id);

    PaginationResponse getProductsByBrand(String brandId, int page, int size);
}
