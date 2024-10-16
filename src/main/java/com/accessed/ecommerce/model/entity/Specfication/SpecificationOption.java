package com.accessed.ecommerce.model.entity.Specfication;

import com.accessed.ecommerce.utils.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificationOption {

    private ProductType name;

    private String value;

}
