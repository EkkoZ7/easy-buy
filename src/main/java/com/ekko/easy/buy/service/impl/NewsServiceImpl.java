package com.ekko.easy.buy.service.impl;

import com.ekko.easy.buy.domain.News;
import com.ekko.easy.buy.mapper.NewsMapper;
import com.ekko.easy.buy.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Resource
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllList() {
        return newsMapper.selectAll();
    }

    @Override
    public News getById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
