package com.ekko.easy.buy.service;

import com.ekko.easy.buy.domain.Order;
import com.ekko.easy.buy.domain.OrderDetail;

import java.util.List;

public interface OrderService{
    Integer insert(Order order, List<OrderDetail> orderDetailList);

    Order getById(Integer id);

    List<Order> getListWithOrderDetailList();

    List<Order> getListByUserIdWithOrderDetailList(Integer userId);
}
