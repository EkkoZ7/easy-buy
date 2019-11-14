package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.mapper.UserMapper;
import com.ekko.easy.buy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseResult login(User user) {
        ResponseResult result = null;
        if (user != null && StringUtils.isEmpty(user.getLoginName()) && StringUtils.isEmpty(user.getPassword())){
            result = new ResponseResult<>(404,"用户名或密码错误！");
        }
        User example = new User();
        example.setLoginName(user.getLoginName());
        User dbUser = userMapper.selectOne(example);
        if (dbUser == null){
            result = new ResponseResult<>(404,"用户名或密码错误！");
        } else {
            result = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(dbUser.getPassword()) ?new ResponseResult<>(200, "登陆成功", dbUser) : new ResponseResult<>(404,"用户名或密码错误！");
        }
        return result;
    }

    @Override
    public ResponseResult register(User user) {
        ResponseResult result = null;
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("loginName",user.getLoginName());
        userMapper.selectOneByExample(example);
        if (userMapper.selectOneByExample(example) != null) {
            result = new ResponseResult(HttpStatus.NOT_ACCEPTABLE.value(), "用户已存在");
        } else {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userMapper.insert(user);
            result = new ResponseResult(200, "注册成功！");
        }
        return result;
    }

    @Override
    public List<User> getList() {
        return userMapper.selectAll();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0){
            userMapper.insert(user);
        } else {
          userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

}
