package com.ekko.easy.buy;

import com.ekko.easy.buy.domain.Order;
import com.ekko.easy.buy.mapper.OrderMapper;
import com.ekko.easy.buy.service.ProductCategoryService;
import com.ekko.easy.buy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EasyBuyApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
    }

    @Test
    void orderMapperTest(){
        List<Order> x = orderMapper.selectWithOrderDetailList();
        for (Order order : x) {
            System.out.println(order);
            System.out.println("------------------------------"+order.getOrderDetailList().size()+"----------------------------------------");
        }
    }

    @Test
    void productServiceTest(){
        System.out.println(productService.getRootCategoryProduct());
    }

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    void  productCategoryService(){
        System.out.println(productCategoryService.getListWithParentName());
    }

}
