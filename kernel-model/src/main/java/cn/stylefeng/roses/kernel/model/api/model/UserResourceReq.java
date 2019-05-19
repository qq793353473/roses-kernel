package cn.stylefeng.roses.kernel.model.api.model;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.kernel.model.request.AbstractBaseRequest;
import lombok.Data;

/**
 * 获取用户资源请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class UserResourceReq extends AbstractBaseRequest {

    /**
     * 账号id
     */
    private String accountId;

    @Override
    public String checkParam() {
        if (StrUtil.isEmpty(accountId)) {
            return "请求accountId为空！";
        }
        return null;
    }

}
