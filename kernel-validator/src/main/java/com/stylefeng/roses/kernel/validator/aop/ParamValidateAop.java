package com.stylefeng.roses.kernel.validator.aop;

import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.model.constants.AopSortConstants;
import com.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 参数校验的aop
 *
 * @author fngshuonan
 * @date 2018-08-07-上午9:53
 */
@Aspect
@Order(AopSortConstants.PARAM_VALIDATE_AOP_SORT)
public class ParamValidateAop {

    @Pointcut(value = "@annotation(com.stylefeng.roses.kernel.validator.stereotype.ParamValidator)")
    private void cutService() {

    }

    @Around("cutService()")
    public Object doInvoke(ProceedingJoinPoint point) throws Throwable {

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //如果请求参数为空，直接跳过aop
        if (methodParams == null || methodParams.length <= 0) {
            return point.proceed();
        } else {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
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
            return point.proceed();
        }

    }
}
