package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "login")
    @ResponseBody
    public ResponseResult login(User user, HttpServletRequest request){
        ResponseResult login = userService.login(user);
        if (login.getCode() == 200){
            request.getSession().setAttribute("user", login.getData());
        }
        return userService.login(user);
    }

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }

    @GetMapping("/loginTip")
    public String loginTip(){
        return "loginTip";
    }
}
