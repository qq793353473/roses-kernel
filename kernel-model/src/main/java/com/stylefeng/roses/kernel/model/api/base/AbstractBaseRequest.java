package com.stylefeng.roses.kernel.model.api.base;

import com.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程服务的参数的基类
 *
 * @author fengshuonan
 * @date 2018-08-06-下午4:22
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam {

    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;

}
