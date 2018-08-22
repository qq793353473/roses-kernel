package com.stylefeng.roses.kernel.model.auth.context;


import com.stylefeng.roses.kernel.model.auth.AbstractLoginUser;

/**
 * 快速获取登录信息上下文
 *
 * @author fengshuonan
 * @date 2018-02-05 16:58
 */
public interface AbstractLoginContext {

    /**
     * 获取当前用户的token
     */
    String getCurrentUserToken();

    /**
     * 获取当前用户
     */
    <T extends AbstractLoginUser> T getLoginUser();
}
