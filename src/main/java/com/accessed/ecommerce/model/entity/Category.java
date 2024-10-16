package com.accessed.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.accessed.ecommerce.model.entity.ProductVariant.VariantOption;
import com.accessed.ecommerce.model.entity.Specfication.SpecificationOption;
import com.accessed.ecommerce.utils.enums.ProductType;
import com.accessed.ecommerce.utils.enums.Status;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


import java.time.Instant;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "categories")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String icon;
    private Integer level;
    @DocumentReference(lazy = true)
    @JsonIgnoreProperties({ "parent", "children"})
    private Category parent;
    @DocumentReference(lazy = true)
    @JsonIgnoreProperties({"parent", "children"})
    private List<Category> children;
    private List<ProductType> variantOptions;
    private List<ProductType> specificationOptions;
    private Boolean isFeatured;
}
