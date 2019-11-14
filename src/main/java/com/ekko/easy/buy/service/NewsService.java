package com.ekko.easy.buy.service;

import com.ekko.easy.buy.domain.News;

import java.util.List;

public interface NewsService{
    List<News> getAllList();

    News getById(Integer id);
}
