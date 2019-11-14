package com.ekko.easy.buy.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "easybuy_user_address")
public class UserAddress {
    /**
     * 主键id
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

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 是否是默认地址（1:是 0否）
     */
    @Column(name = "isDefault")
    private Integer isDefault;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}