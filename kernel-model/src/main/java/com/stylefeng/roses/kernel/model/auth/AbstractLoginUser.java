package com.stylefeng.roses.kernel.model.auth;

import java.util.Set;

/**
 * 登录中的用户信息
 * <p>
 * 为何类型是泛型，因为具体的项目不知道类型是什么
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:47
 */
public interface AbstractLoginUser {

    /**
     * 获取用户唯一id
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:38
     */
    <T> T getUserUniqueId();

    /**
     * 获取用户唯一id
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:38
     */
    <T> T getAppId();

    /**
     * 获取角色id的集合
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    <T> Set<T> getRoleIds();

    /**
     * 角色编码集合
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    <T> Set<T> getRoleCodes();

    /**
     * 包含的资源权限url
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    <T> Set<T> getResourceUrls();

}
