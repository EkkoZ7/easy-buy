package com.ekko.easy.buy.service;

import com.ekko.easy.buy.domain.UserAddress;

import java.util.List;

public interface UserAddressService{
    List<UserAddress> getByUserId(Integer userId);

    void Insert(UserAddress userAddress);
}
