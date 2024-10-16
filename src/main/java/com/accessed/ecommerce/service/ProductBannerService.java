package com.accessed.ecommerce.service;

import com.accessed.ecommerce.model.dto.request.ProductBannerRequest;
import com.accessed.ecommerce.model.dto.response.ProductBannerResponse;
import com.accessed.ecommerce.model.entity.ProductBanner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ProductBannerService {

    ProductBanner handleCreateBanner(ProductBanner request);

    ProductBanner handleGetBannerById(String bannerId);

    ProductBanner handleUpdateBanner(String bannerId, ProductBanner request);

    Void handleDeleteBannerById(String bannerId);

    List<ProductBanner> handleGetBannersByQuantity(int quantity);

    List<String> handleGetBannerImagesByQuantity(int quantity);

    List<ProductBanner> handleGetBanners();
}
