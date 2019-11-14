package com.ekko.easy.buy.mapper;

import com.ekko.easy.buy.domain.Order;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface OrderMapper extends MyMapper<Order> {
    List<Order> selectWithOrderDetailList();

    List<Order> selectByUerIdWithOrderDetailList(Integer id);
}