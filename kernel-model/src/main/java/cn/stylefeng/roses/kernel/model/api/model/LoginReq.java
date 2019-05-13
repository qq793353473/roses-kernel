package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.api.base.AbstractBaseRequest;
import lombok.Data;

/**
 * 登录的请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class LoginReq extends AbstractBaseRequest {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

}
