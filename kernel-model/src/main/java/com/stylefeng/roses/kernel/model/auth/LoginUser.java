package com.stylefeng.roses.kernel.model.auth;

import lombok.Data;

import java.util.Set;

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
    private String infoId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 账号
     *
     * @Author 戚传文
     * @Date 2018/3/29 12:20
     */
    private String account;

    /**
     * 电话号码
     */
    private String mobilePhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 角色id
     */
    private Set<String> roleIds;

    /**
     * 角色id
     */
    private Set<String> roleCodes;

    /**
     * 组织id
     */
    private Set<String> organizationIds;

    /**
     * 包含的资源权限url
     */
    private Set<String> resourceUrls;

}
