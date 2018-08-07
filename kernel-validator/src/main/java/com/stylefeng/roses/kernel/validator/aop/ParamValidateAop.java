package com.stylefeng.roses.kernel.validator.aop;

import com.stylefeng.roses.kernel.model.constants.AopSortConstants;
import com.stylefeng.roses.kernel.validator.util.ValidationUtil;
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
            ValidationUtil.validateParameters(methodParams);
            return point.proceed();
        }
    }
}
