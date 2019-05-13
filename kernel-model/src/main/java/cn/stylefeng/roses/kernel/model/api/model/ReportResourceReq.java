package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.api.base.AbstractBaseRequest;
import cn.stylefeng.roses.kernel.model.resource.ResourceDefinition;
import lombok.Data;

import java.util.Map;

/**
 * 汇报资源请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class ReportResourceReq extends AbstractBaseRequest {

    /**
     * app编码
     */
    private String appCode;

    /**
     * 资源集合
     */
    private Map<String, Map<String, ResourceDefinition>> resourceDefinitions;

    public ReportResourceReq(String appCode, Map<String, Map<String, ResourceDefinition>> resourceDefinitions) {
        this.appCode = appCode;
        this.resourceDefinitions = resourceDefinitions;
    }

}
