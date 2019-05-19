package cn.stylefeng.roses.kernel.model.api.model;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.kernel.model.request.AbstractBaseRequest;
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

    @Override
    public String checkParam() {
        if (StrUtil.isEmpty(account)) {
            return "请求账号为空！";
        }
        if (StrUtil.isEmpty(password)) {
            return "请求密码为空！";
        }
        return null;
    }
}
