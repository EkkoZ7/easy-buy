package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.*;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.OrderService;
import com.ekko.easy.buy.service.ProductService;
import com.ekko.easy.buy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping("/order")
    @ResponseBody
    public ResponseResult<Integer> order(UserAddress address, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (address.getId().equals(-1)) {
            address.setId(null);
            address.setCreateTime(new Date());
            address.setUserId(user.getId());
            userAddressService.Insert(address);
        }
        Double totalCost = 0d;

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<Product> products = productService.selectListByIds(cart.getItems());

        for (Product product : products) {
            OrderDetail orderDetail = new OrderDetail();
            Integer count = cart.getItemQuantity().get(product.getId());
            Double cost = product.getPrice() * count;
            totalCost += cost;
            orderDetail.setCost(cost);
            orderDetail.setProductId(product.getId());
            orderDetail.setQuantity(count);
            orderDetails.add(orderDetail);
        }
        Order order = new Order();
        order.setUserId(user.getId());
        order.setUserAddress(address.getAddress());
        order.setCreateTime(new Date());
        order.setSerialNumber(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
        order.setCost(totalCost);
        order.setLoginName(user.getLoginName());
        cart.getItemQuantity().clear();
        cart.getItems().clear();
        return new ResponseResult<>(HttpStatus.OK.value(), orderService.insert(order,orderDetails));
    }

    @GetMapping("/order/{id}")
    public String pay(@PathVariable Integer id, Model model){
        model.addAttribute("currentOrder",orderService.getById(id));
        return "pay";
    }

    @GetMapping("admin/order/list")
    public String adminOrderList(Model model){
        model.addAttribute("orderList", orderService.getListWithOrderDetailList());
        model.addAttribute("menu", 8);
        return "order_list";
    }

    @GetMapping("/order/list")
    public String orderList(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("orderList", orderService.getListByUserIdWithOrderDetailList(user.getId()));
        model.addAttribute("menu", 1);
        return "order_list";
    }

}
