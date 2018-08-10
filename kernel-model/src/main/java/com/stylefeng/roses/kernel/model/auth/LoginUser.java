package com.stylefeng.roses.kernel.model.auth;

import java.util.Set;

/**
 * 登录中的用户信息
 * <p>
 * 为何类型是Object，因为具体的项目不知道类型是什么
 *
 * @author fengshuonan
 * @date 2017-11-09-下午5:47
 */
public interface LoginUser {

    /**
     * 获取用户唯一id
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:38
     */
    Object getUserUniqueId();

    /**
     * 获取用户唯一id
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:38
     */
    Object getAppId();

    /**
     * 获取角色id的集合
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    Set<Object> getRoleIds();

    /**
     * 角色编码集合
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    Set<Object> getRoleCodes();

    /**
     * 包含的资源权限url
     *
     * @author fengshuonan
     * @Date 2018/8/10 下午5:40
     */
    Set<Object> getResourceUrls();

}
