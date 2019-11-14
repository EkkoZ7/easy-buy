package com.ekko.easy.buy.web.controller;

import com.ekko.easy.buy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news/list")
    public String newsList(Model model){
        model.addAttribute("newsList", newsService.getAllList());
        return "news_list";
    }

    @GetMapping("/news/{id}")
    public String newsDetail(Model model, @PathVariable Integer id){
        model.addAttribute("news", newsService.getById(id));
        return "news_detail";
    }
}
