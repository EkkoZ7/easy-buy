package com.ekko.easy.buy.mapper;

import com.ekko.easy.buy.domain.Product;
import tk.mybatis.mapper.MyMapper;

public interface ProductMapper extends MyMapper<Product> {
    Product selectByIdWithCategoryName(Integer id);
}