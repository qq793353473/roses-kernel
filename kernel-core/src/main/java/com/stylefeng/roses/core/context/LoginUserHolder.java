package com.stylefeng.roses.core.context;

import com.stylefeng.roses.kernel.model.auth.LoginUser;

/**
 * <pre>
 * 当前登录用户的临时保存容器
 *
 *  说明：
 *     当OPEN_UP_FLAG标识在ThreadLocal里为true
 * </pre>
 *
 * @author fengshuonan
 * @Date 2018/7/3 下午5:29
 */
public class LoginUserHolder {

    private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal<>();
    private static final ThreadLocal<LoginUser> LONGIN_USER_HOLDER = new ThreadLocal<>();

    /**
     * 初始化
     *
     * @author fengshuonan
     * @Date 2018/7/4 下午12:31
     */
    public static void init() {
        OPEN_UP_FLAG.set(true);
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会set失效
     *
     * @author fengshuonan
     * @Date 2018/7/4 上午11:09
     */
    public static void set(LoginUser loginUser) {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return;
        } else {
            LONGIN_USER_HOLDER.set(loginUser);
        }
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会get值为null
     *
     * @author fengshuonan
     * @Date 2018/7/4 上午11:09
     */
    public static LoginUser get() {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return null;
        } else {
            return LONGIN_USER_HOLDER.get();
        }
    }

    public static void remove() {
        OPEN_UP_FLAG.remove();
        LONGIN_USER_HOLDER.remove();
    }
}
