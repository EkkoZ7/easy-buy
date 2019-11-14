package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.Cart;
import com.ekko.easy.buy.domain.Product;
import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.ProductService;
import com.ekko.easy.buy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/cart")
    @ResponseBody
    public ResponseResult<Map<String, Object>> addCart(Integer productId, Integer quantity, HttpServletRequest request) {
        Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
        Cart cart = sessionCart == null ? new Cart() : sessionCart;
        cart.setUserId(((User) request.getSession().getAttribute("user")).getId());
        // 如果商品不存在购物车中就添加到购物车
        if (!cart.getItems().contains(productId))
            cart.getItems().add(productId);
        // 如果已存在该产品编号就合并属性，否则就新增
        if (cart.getItemQuantity().containsKey(productId)) {
            cart.getItemQuantity().merge(productId, quantity, Integer::sum);
        } else {
            cart.getItemQuantity().put(productId, quantity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("products", productService.selectListByIds(cart.getItems()));
        map.put("quantity", cart.getItemQuantity());
        request.getSession().setAttribute("cart", cart);
        return new ResponseResult<>(200, "添加成功", map);
    }

    @GetMapping(value = "/cart")
    public String toCart(HttpServletRequest request, Model model) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        model.addAttribute("cartInfo", getCartInfo(cart));
        return "cart";
    }

    @GetMapping("/cart_info")
    @ResponseBody
    public ResponseResult getCart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        // 用户未登录
        if (user == null) {
            return new ResponseResult(HttpStatus.FORBIDDEN.value());
        }
        // 购物车为空
        else if (cart == null) {
            return new ResponseResult(HttpStatus.NOT_FOUND.value());
        }
        // 已登录、并且购物车不为空
        else {
            return new ResponseResult<>(HttpStatus.OK.value(), getCartInfo(cart));
        }
    }

    @PatchMapping("/cart")
    @ResponseBody
    public ResponseResult changeQuantity(Integer productId, Integer quantity, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.getItemQuantity().put(productId, quantity);
        return new ResponseResult(HttpStatus.OK.value());
    }

    @DeleteMapping("/cart")
    @ResponseBody
    public ResponseResult deleteCart(Integer productId, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Iterator<Integer> iterator = cart.getItems().iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next.equals(productId)){
                iterator.remove();
                cart.getItemQuantity().remove(next);
                return new ResponseResult(HttpStatus.OK.value());
            }
        }
        return new ResponseResult(HttpStatus.NOT_FOUND.value());
    }

    @GetMapping("/order")
    public String order(Model model, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("carInfo",getCartInfo(cart));
        model.addAttribute("userAddresses", userAddressService.getByUserId(user.getId()));
        return "order";
    }

    private Map getCartInfo(Cart cart){
        Map<String, Object> map = new HashMap<>();
        Map<Integer, Integer> itemQuantity = cart.getItemQuantity();
        List<Product> products = productService.selectListByIds(cart.getItems());
        int priceSum = 0;
        for (Product product : products) {
            priceSum += product.getPrice() * itemQuantity.get(product.getId());
        }
        map.put("products", productService.selectListByIds(cart.getItems()));
        map.put("quantity", cart.getItemQuantity());
        map.put("priceSum", priceSum);
        return map;
    }

}
