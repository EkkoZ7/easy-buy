package com.ekko.easy.buy.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@Table(name = "easybuy_product")
public class Product {
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
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;

    /**
     * 库存
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * 分类1
     */
    @Column(name = "categoryLevel1Id")
    private Integer categoryLevel1Id;

    /**
     * 分类2
     */
    @Column(name = "categoryLevel2Id")
    private Integer categoryLevel2Id;

    /**
     * 分类3
     */
    @Column(name = "categoryLevel3Id")
    private Integer categoryLevel3Id;

    /**
     * 文件名称
     */
    @Column(name = "fileName")
    private String filename;

    /**
     * 是否删除(1：删除 0：未删除)
     */
    @Column(name = "isDelete")
    private Integer isDelete;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String  categoryLevel1Name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String  categoryLevel2Name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private String  categoryLevel3Name;
}