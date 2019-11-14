package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(){
        return "register";
    }

    @PostMapping
    @ResponseBody
    public ResponseResult register(User user){
        return userService.register(user);
    }

}
