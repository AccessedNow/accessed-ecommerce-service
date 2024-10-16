package com.accessed.ecommerce.service;


import com.accessed.ecommerce.model.dto.request.CategoryRequest;
import com.accessed.ecommerce.model.dto.response.CategoryResponse;
import com.accessed.ecommerce.model.dto.response.category.CategoryResponseTopSold;
import com.accessed.ecommerce.model.dto.response.pagination.PaginationResponse;
import com.accessed.ecommerce.model.entity.Category;


import java.util.List;

public interface CategoryService{
    List<Category> handleGetAllCategoriesFeatured(boolean isFeatured);
    List<Category> handleGetAllCategories();

    Category handleCreateCategory(CategoryRequest request);

    Category handleGetCategoryById(String id);

    Category handleUpdateCategory(String id, CategoryRequest request);

    Void handleDeleteCategory(String id);

    List<Category> handleGetAllCategoriesByParentId(String parentId);

    Category handleGetCategoryByName(String name);

    List<CategoryResponseTopSold> handleGetAllCategoriesBySoldQuantity();

    List<Category> handleGetCategoriesByLevel(Integer level);

    PaginationResponse handleGetProductsByCategoryId(String categoryId, Integer pageInt, Integer sizeInt);

    PaginationResponse handleGetAllCategoriesFeaturedWithProducts(Integer pageInt, Integer sizeInt);
}
