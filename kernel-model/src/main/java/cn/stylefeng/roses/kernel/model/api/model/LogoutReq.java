package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.request.AbstractBaseRequest;
import lombok.Data;

/**
 * 退出的请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class LogoutReq extends AbstractBaseRequest {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

}
