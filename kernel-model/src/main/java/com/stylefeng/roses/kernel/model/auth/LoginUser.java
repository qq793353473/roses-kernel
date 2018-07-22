package com.stylefeng.roses.kernel.model.auth;

import lombok.Data;

/**
 * 登录中的用户信息
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:47
 */
@Data
public class LoginUser {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户姓名
     */
    private String phoneNumber;

    /**
     * email
     */
    private String email;

    /**
     * 状态: 1-启用  0-禁用
     */
    private Integer status;

}
