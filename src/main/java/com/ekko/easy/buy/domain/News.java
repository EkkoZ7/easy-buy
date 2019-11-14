package com.ekko.easy.buy.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "easybuy_news")
public class News {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private String createTime;
}