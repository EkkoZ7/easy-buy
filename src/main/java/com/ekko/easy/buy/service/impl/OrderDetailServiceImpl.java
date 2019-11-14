package com.ekko.easy.buy.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ekko.easy.buy.mapper.OrderDetailMapper;
import com.ekko.easy.buy.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Resource
    private OrderDetailMapper orderDetailMapper;

}
