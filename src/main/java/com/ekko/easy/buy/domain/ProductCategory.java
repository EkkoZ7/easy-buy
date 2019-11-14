package com.ekko.easy.buy.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@Table(name = "easybuy_product_category")
public class ProductCategory {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 父级目录id
     */
    @Column(name = "parentId")
    private Integer parentId;

    /**
     * 级别(1:一级 2：二级 3：三级)
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 图标
     */
    @Column(name = "iconClass")
    private String iconClass;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String parentName;
}