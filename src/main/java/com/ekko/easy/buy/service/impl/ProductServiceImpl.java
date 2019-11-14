package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.Product;
import com.ekko.easy.buy.domain.ProductCategory;
import com.ekko.easy.buy.mapper.ProductCategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ekko.easy.buy.mapper.ProductMapper;
import com.ekko.easy.buy.service.ProductService;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public PageInfo<Product> selectPagesByCategoryId(Integer categoryId,Integer pageNum) {
        Example example = new Example(Product.class);
        example.createCriteria().orEqualTo("categoryLevel1Id", categoryId)
                .orEqualTo("categoryLevel2Id", categoryId)
                .orEqualTo("categoryLevel3Id", categoryId);
        return PageHelper.startPage(pageNum, 20)
                .doSelectPageInfo(() -> productMapper.selectByExample(example));
    }

    @Override
    public Product selectByIdWithCategoryName(Integer id) {
        return productMapper.selectByIdWithCategoryName(id);
    }

    @Override
    public Product selectById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectListByIds(List<Integer> ids) {
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id", ids);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<Product> getList() {
        return productMapper.selectAll();
    }

    @Override
    public List<List<Product>> getRootCategoryProduct(){
        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("parentId",0);
        List<ProductCategory> productCategories  = productCategoryMapper.selectByExample(example);
        List<List<Product>> rootProductList = new ArrayList<>();
        RowBounds rowBounds = new RowBounds(0,6);
        for (ProductCategory productCategory : productCategories) {
            Example exampleProduct = new Example(Product.class);
            exampleProduct.createCriteria().orEqualTo("categoryLevel1Id", productCategory.getId());
            exampleProduct.createCriteria().orEqualTo("categoryLevel2Id", productCategory.getId());
            exampleProduct.createCriteria().orEqualTo("categoryLevel3Id", productCategory.getId());
            rootProductList.add(productMapper.selectByExampleAndRowBounds(exampleProduct, rowBounds));
        }
        return rootProductList;
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer save(Product product) {
        if (product.getId() == null){
            productMapper.insert(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }
        return product.getId();
    }

}
