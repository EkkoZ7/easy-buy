package com.ekko.easy.buy.service;

import com.ekko.easy.buy.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService{
    List<ProductCategory> getByType(Integer integer);

    List<ProductCategory> getRootCategory();

    List<ProductCategory> getByParentId(Integer integer);

    List<ProductCategory> getListWithParentName();

    ProductCategory getById(Integer id);

    void insert(ProductCategory productCategory);

    void delete(Integer id);
}
