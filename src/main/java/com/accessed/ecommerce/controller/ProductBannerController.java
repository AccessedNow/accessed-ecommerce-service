package com.accessed.ecommerce.controller;

import com.google.protobuf.Api;
import com.accessed.ecommerce.model.dto.request.ProductBannerRequest;
import com.accessed.ecommerce.model.dto.response.ApiResponse;
import com.accessed.ecommerce.model.dto.response.ProductBannerResponse;
import com.accessed.ecommerce.model.entity.ProductBanner;
import com.accessed.ecommerce.service.ProductBannerService;
import com.accessed.ecommerce.utils.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductBannerController {

    private final ProductBannerService productBannerService;

    @PostMapping(value = "/banners")
    public ResponseEntity<ApiResponse<ProductBanner>> handleCreateBanner(
            @Valid
            @RequestBody ProductBanner request
    ) {
        log.info("request: {}", request);
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.CREATED,
                        productBannerService.handleCreateBanner(request )
                ));
    }

    @GetMapping("/banners")
    public ResponseEntity<ApiResponse<List<ProductBanner>>> handleGetBanners() {
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.FETCHED,
                        productBannerService.handleGetBanners()
                )
        );
    }

    @GetMapping("/banners/{bannerId}")
    public ResponseEntity<ApiResponse<ProductBanner>> handleGetBanner(
            @PathVariable String bannerId) {
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.FETCHED,
                        productBannerService.handleGetBannerById(bannerId)
                )
        );
    }

    @GetMapping("banners/quantity/{quantity}")
    public ResponseEntity<ApiResponse<List<ProductBanner>>> handleGetBannersByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.FETCHED,
                        productBannerService.handleGetBannersByQuantity(quantity)
                )
        );
    }

    @GetMapping("banners/images/{quantity}")
    public ResponseEntity<ApiResponse<List<String>>> handleGetBannerImagesByQuantity(@PathVariable int quantity) {
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.FETCHED,
                        productBannerService.handleGetBannerImagesByQuantity(quantity)
                )
        );
    }


    @PutMapping("/banners/{bannerId}")
    public ResponseEntity<ApiResponse<ProductBanner>> handleUpdateBanner(
            @PathVariable String bannerId,
            @Valid
            @RequestBody ProductBanner request

    )  {
        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.UPDATED,
                        productBannerService.handleUpdateBanner(bannerId, request)
                )
        );
    }


    @DeleteMapping("/banners/{bannerId}")
    public ResponseEntity<ApiResponse<Void>> handleDeleteBanner(
            @PathVariable String bannerId) {

        return ResponseEntity.ok(
                ApiResponse.builderResponse(
                        SuccessCode.DELETED,
                        productBannerService.handleDeleteBannerById(bannerId)
                )
        );
    }
}
