package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.Product;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.ProductCategoryService;
import com.ekko.easy.buy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @GetMapping("product/list/{id}")
    public String products(@PathVariable Integer id, Model model){
        model.addAttribute("productCategories",productCategoryService.getByType(1));
        model.addAttribute("productPageInfo",productService.selectPagesByCategoryId(id,1));
        return "product_list";
    }

    @GetMapping("admin/product/list")
    public String productsList(Model model){
        model.addAttribute("productList", productService.getList());
        return "admin_product_list";
    }

    @GetMapping("products/{id}")
    public String product(@PathVariable Integer id, Model model) {
        model.addAttribute("productCategories",productCategoryService.getByType(1));
        model.addAttribute("product", productService.selectByIdWithCategoryName(id));
        return "product_detail";
    }

    @GetMapping("admin/product/form/{id}")
    public String productForm(@PathVariable Integer id,Model model){
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("menu",5);
        addProductCategoryInfo(model);
        return "admin_product_form";
    }

    @GetMapping("admin/product/form")
    public String productForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("menu",6);
        addProductCategoryInfo(model);
        return "admin_product_form";
    }

    @DeleteMapping("products/{id}")
    public ResponseResult delete(@PathVariable Integer id){
        productService.delete(id);
        return new ResponseResult(HttpStatus.OK.value());
    }

    @PostMapping("products")
    public ResponseResult save(Product product){
        productService.save(product);
        return new ResponseResult(HttpStatus.OK.value());
    }


    private void addProductCategoryInfo(Model model){
        model.addAttribute("productCategoryLevel1List", productCategoryService.getByType(1));
        model.addAttribute("productCategoryLevel2List", productCategoryService.getByType(2));
        model.addAttribute("productCategoryLevel3List", productCategoryService.getByType(3));
    }

    @PostMapping("admin/products")
    public String saveProduct(HttpServletRequest request, @RequestParam("photo") MultipartFile multipartFile,Product product){
        if (!multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString().replaceAll("-","").toUpperCase() + originalFilename.substring(originalFilename.indexOf('.'));
            File file = null;
            try {
                File uploadPath = new File(fileUploadPath + "/images");
                if (!uploadPath.exists()) uploadPath.mkdirs();
                file = new File(uploadPath,newFileName);
                System.out.println(file.getAbsolutePath());
                multipartFile.transferTo(file);
                product.setFilename(newFileName);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/products/" + productService.save(product);
    }

    @GetMapping("/test")
    @ResponseBody
    public String file() throws FileNotFoundException {
        File file = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(file.getAbsolutePath(),"static/images/upload");
        return upload.getAbsolutePath();
    }


}
