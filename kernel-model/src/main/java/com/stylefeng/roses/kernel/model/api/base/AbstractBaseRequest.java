package com.stylefeng.roses.kernel.model.api.base;

/**
 * 远程服务的参数的基类
 *
 * @author fengshuonan
 * @date 2018-08-06-下午4:22
 */
public abstract class AbstractBaseRequest {

    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;

    /**
     * 校验请求参数是否为空
     *
     * @return String 如果返回null代表没有为空的参数，校验通过，如果不为空，则代表有空的字段，并且返回的内容是为空的提示信息
     * @author fengshuonan
     * @Date 2018/8/6 下午4:28
     */
    protected abstract String checkParam();
}
