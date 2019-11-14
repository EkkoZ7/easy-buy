package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.Order;
import com.ekko.easy.buy.domain.OrderDetail;
import com.ekko.easy.buy.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ekko.easy.buy.mapper.OrderMapper;
import com.ekko.easy.buy.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Integer insert(Order order, List<OrderDetail> orderDetailList) {
        orderMapper.insert(order);
        Integer orderId = order.getId();
        for (OrderDetail detail : orderDetailList) {
            detail.setOrderId(orderId);
        }
        orderDetailMapper.insertList(orderDetailList);
        return orderId;
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> getListWithOrderDetailList() {
        return orderMapper.selectWithOrderDetailList();
    }

    @Override
    public List<Order> getListByUserIdWithOrderDetailList(Integer userId) {
        return orderMapper.selectByUerIdWithOrderDetailList(userId);
    }

}
