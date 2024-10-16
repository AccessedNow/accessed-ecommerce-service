package com.accessed.ecommerce.service.impl;

import com.accessed.ecommerce.model.entity.Product;
import com.accessed.ecommerce.repository.ProductRepository;
import com.accessed.ecommerce.service.ProductImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductRepository productRepository;

    @Override
    public String createImage(MultipartFile file) {
        return null;
    }

    @Override
    public String updateFileImage(MultipartFile file) {
        return null;
    }

    @Override
    public void deleteFileImage(String Imageurl) {


    }
}
