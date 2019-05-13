package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.api.base.AbstractBaseRequest;
import lombok.Data;

/**
 * 获取资源通过url请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class ResourceUrlReq extends AbstractBaseRequest {

    /**
     * 账号id
     */
    private String url;

}
