package com.ekko.easy.buy.mapper;

import com.ekko.easy.buy.domain.ProductCategory;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface ProductCategoryMapper extends MyMapper<ProductCategory> {
    List<ProductCategory> selectListWithParentName();
}