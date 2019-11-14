package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.News;
import com.ekko.easy.buy.domain.ProductCategory;
import com.ekko.easy.buy.service.NewsService;
import com.ekko.easy.buy.service.ProductCategoryService;
import com.ekko.easy.buy.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private NewsService newsService;

    @GetMapping(value = {"","/index"})
    public String index(Model model){
        model.addAttribute("productCategories",productCategoryService.getByType(1));
        PageInfo<News> createTime_desc = PageHelper.startPage(1, 5).setOrderBy("createTime desc").doSelectPageInfo(() -> newsService.getAllList());
        model.addAttribute("rootCategoryProductList", productService.getRootCategoryProduct());
        model.addAttribute("newsList", createTime_desc.getList());
        model.addAttribute("rootCategoryList", productCategoryService.getRootCategory());
        return "index";
    }

    @GetMapping("/products/categories/{id}")
    @ResponseBody
    public List<ProductCategory> getProductCategoryByParentId(@PathVariable Integer id){
        return productCategoryService.getByParentId(id);
    }

}
