package com.accessed.ecommerce.model.entity.Specfication;

import com.accessed.ecommerce.model.entity.ProductVariant.ProductVariant;
import com.accessed.ecommerce.utils.enums.ProductType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
public class ProductSpecification {
    private String description;
    private List<SpecificationOption> specificationOptions;
}
