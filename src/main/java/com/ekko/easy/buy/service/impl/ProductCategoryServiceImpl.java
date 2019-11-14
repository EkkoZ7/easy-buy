package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.ProductCategory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ekko.easy.buy.mapper.ProductCategoryMapper;
import com.ekko.easy.buy.service.ProductCategoryService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> getByType(Integer integer) {
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("type",integer);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<ProductCategory> getByParentId(Integer integer) {
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("parentId",integer);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<ProductCategory> getListWithParentName() {
        return productCategoryMapper.selectListWithParentName();
    }

    @Override
    public ProductCategory getById(Integer id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(ProductCategory productCategory) {
        productCategoryMapper.insert(productCategory);
    }

    @Override
    public void delete(Integer id) {
        productCategoryMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<ProductCategory> getRootCategory(){
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("parentId", 0);
        return productCategoryMapper.selectByExample(example);
    }

}
