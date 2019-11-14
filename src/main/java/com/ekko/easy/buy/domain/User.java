package com.ekko.easy.buy.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Table(name = "easybuy_user")
public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 登录名
     */
    @Column(name = "loginName")
    @Length(min = 2, max = 10, message = "登录名不能小于两个字符或者大于十个字符")
    private String loginName;

    /**
     * 用户名
     */
    @Column(name = "userName")
    @NotBlank(message = "真实姓名不能为空")
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    @Column(name = "`password`")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 性别(1:男 0：女)
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 身份证号
     */
    @Pattern(regexp = "^\\w{18}$")
    @Column(name = "identityCode")
    private String identityCode;

    /**
     * 邮箱
     */
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")
    @Column(name = "email")
    private String email;

    /**
     * 手机
     */
    @Pattern(regexp = "^\\d{5,11}$")
    @Column(name = "mobile")
    private String mobile;

    /**
     * 类型（1：后台 0:前台）
     */
    @Column(name = "`type`")
    private Integer type;
}