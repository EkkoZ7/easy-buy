package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.domain.User;
import com.ekko.easy.buy.dto.ResponseResult;
import com.ekko.easy.buy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/user/list")
    public String userList(Model model){
        model.addAttribute("userList", userService.getList());
        return "user_list";
    }

    @GetMapping("/admin/users/{id}")
    public String userForm(@PathVariable Integer id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "user_form";
    }

    @GetMapping("/admin/users")
    public String userForm(Model model){
        User user = new User();
        user.setId(0);
        model.addAttribute("user",user);
        return "user_form";
    }

    @PostMapping("/admin/users")
    public String save(User user){
        userService.save(user);
        return "redirect:/admin/user/list";
    }

    @DeleteMapping("/admin/users/{id}")
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id){
        userService.deleteById(id);
        return new ResponseResult(HttpStatus.OK.value());
    }

    @GetMapping("/user")
    public String userInfo(){
        return "user_detail";
    }

}
