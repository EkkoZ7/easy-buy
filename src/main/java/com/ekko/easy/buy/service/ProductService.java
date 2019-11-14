package com.ekko.easy.buy.service;

import com.ekko.easy.buy.domain.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService{
    PageInfo<Product> selectPagesByCategoryId(Integer id, Integer pageNum);

    Product selectByIdWithCategoryName(Integer id);

    Product selectById(Integer id);

    List<Product> selectListByIds(List<Integer> id);

    List<Product> getList();

    List<List<Product>> getRootCategoryProduct();

    Product getById(Integer id);

    void delete(Integer id);

    Integer save(Product product);
}
