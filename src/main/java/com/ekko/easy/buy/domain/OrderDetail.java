package com.ekko.easy.buy.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "easybuy_order_detail")
public class OrderDetail {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 订单主键
     */
    @Column(name = "orderId")
    private Integer orderId;

    /**
     * 商品主键
     */
    @Column(name = "productId")
    private Integer productId;

    /**
     * 数量
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 消费
     */
    @Column(name = "cost")
    private Double cost;

    private Product product;
}