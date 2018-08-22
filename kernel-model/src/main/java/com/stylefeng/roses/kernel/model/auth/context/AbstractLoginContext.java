package com.stylefeng.roses.kernel.model.auth.context;


import com.stylefeng.roses.kernel.model.auth.AbstractLoginUser;
import com.stylefeng.roses.kernel.model.auth.AuthService;

/**
 * 登录信息上下文
 *
 * @author fengshuonan
 * @date 2018-02-05 16:58
 */
public abstract class AbstractLoginContext {

    /**
     * 鉴权服务
     */
    private AuthService authService;

    public AbstractLoginContext(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 获取当前用户的token
     */
    protected abstract String getCurrentUserToken();

    /**
     * 获取当前用户
     */
    public abstract <T extends AbstractLoginUser> T getLoginUser();
}
