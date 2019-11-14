package com.ekko.easy.buy.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@Table(name = "easybuy_order")
public class Order {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户主键
     */
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "loginName")
    private String loginName;

    /**
     * 用户地址
     */
    @Column(name = "userAddress")
    private String userAddress;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 总消费
     */
    @Column(name = "cost")
    private Double cost;

    /**
     * 订单号
     */
    @Column(name = "serialNumber")
    private String serialNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<OrderDetail> orderDetailList;
}