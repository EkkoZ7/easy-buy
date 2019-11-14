package com.ekko.easy.buy.service;


import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;

import java.util.List;

public interface UserService{
    ResponseResult<User> login(User user);
    ResponseResult register(User user);
    List<User> getList();
    User getById(Integer id);
    void save(User user);
    void deleteById(Integer id);
}
