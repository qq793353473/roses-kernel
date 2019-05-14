package cn.stylefeng.roses.kernel.model.api.model;

import cn.stylefeng.roses.kernel.model.request.AbstractBaseRequest;
import lombok.Data;

/**
 * token的请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class TokenReq extends AbstractBaseRequest {

    /**
     * token
     */
    private String token;

}
