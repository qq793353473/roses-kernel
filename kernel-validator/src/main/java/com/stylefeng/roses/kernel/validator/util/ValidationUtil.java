package com.stylefeng.roses.kernel.validator.util;

import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.stylefeng.roses.kernel.model.validator.BaseValidatingParam;

/**
 * 校验参数中的参数是否符合规则
 *
 * @author fengshuonan
 * @date 2018-08-07-上午10:34
 */
public class ValidationUtil {

    public static void validateParameters(Object[] methodParams) {
        for (Object methodParam : methodParams) {
            if (methodParam instanceof BaseValidatingParam) {
                BaseValidatingParam baseValidatingParam = (BaseValidatingParam) methodParam;
                String checkResult = baseValidatingParam.checkParam();

                //如果校验结果不为空，则代表参数校验有空的或者不符合规则的，并且checkResult为参数错误的提示信息
                if (ToolUtil.isNotEmpty(checkResult)) {
                    throw new RequestEmptyException(checkResult);
                }
            }
        }
    }
}
