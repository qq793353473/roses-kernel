package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.api.base.AbstractBaseRequest;
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

}
