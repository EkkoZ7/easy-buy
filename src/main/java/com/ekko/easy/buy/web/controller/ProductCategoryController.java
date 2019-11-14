package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.ProductCategory;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/admin/product/category/list")
    public String adminProductCategoryList(Model model){
        model.addAttribute("productCategoryList", productCategoryService.getListWithParentName());
        return "admin_product_category_list";
    }

    @GetMapping("/admin/products/categories/{id}")
    @ResponseBody
    public ResponseResult<List<ProductCategory>> productCategoryListByParentId(Model model, @PathVariable Integer id){
        return new ResponseResult<>(HttpStatus.OK.value(),productCategoryService.getByParentId(id));
    }

    @GetMapping("/admin/product/category/form")
    public String adminProductCategoryForm(Model model){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setType(0);
        model.addAttribute("parentProductCategory", productCategory);
        return "admin_product_category_form";
    }

    @GetMapping("/admin/product/category/form/{id}")
    public String adminProductCategoryForm(Model model, @PathVariable  Integer id){
        ProductCategory productCategory = productCategoryService.getById(id);
        model.addAttribute("parentProductCategory", productCategory);
        model.addAttribute("parentTypeProductCategoryList",productCategoryService.getByType(productCategory.getType()));
        return "admin_product_category_form";
    }

    @PostMapping("admin/products/categories")
    @ResponseBody
    public ResponseResult addCategory(ProductCategory productCategory){
        productCategoryService.insert(productCategory);
        return new ResponseResult(HttpStatus.OK.value());
    }

    @DeleteMapping("admin/products/categories/{id}")
    @ResponseBody
    public ResponseResult deleteCategory(@PathVariable Integer id){
        productCategoryService.delete(id);
        return new ResponseResult(HttpStatus.OK.value());
    }

}
