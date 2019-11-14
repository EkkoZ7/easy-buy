package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.UserAddress;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ekko.easy.buy.mapper.UserAddressMapper;
import com.ekko.easy.buy.service.UserAddressService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService{

    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getByUserId(Integer userId) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("userId", userId);
        return userAddressMapper.selectByExample(example);
    }

    @Override
    public void Insert(UserAddress userAddress) {
        userAddressMapper.insert(userAddress);
    }
}
