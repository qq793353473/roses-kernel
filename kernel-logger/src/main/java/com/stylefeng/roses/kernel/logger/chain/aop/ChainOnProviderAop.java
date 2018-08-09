package com.stylefeng.roses.kernel.logger.chain.aop;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.roses.core.context.LoginUserHolder;
import com.stylefeng.roses.core.context.RequestNoContext;
import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.logger.chain.context.ParentSpanIdHolder;
import com.stylefeng.roses.kernel.logger.chain.context.SpanIdContext;
import com.stylefeng.roses.kernel.logger.chain.context.SpanIdHolder;
import com.stylefeng.roses.kernel.logger.chain.context.TraceIdHolder;
import com.stylefeng.roses.kernel.logger.chain.enums.RpcPhaseEnum;
import com.stylefeng.roses.kernel.logger.util.TraceUtil;
import com.stylefeng.roses.kernel.validator.util.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import static com.stylefeng.roses.kernel.model.constants.AopSortConstants.CHAIN_ON_PROVIDER_SORT;


/**
 * 基于调用链的服务治理系统的设计（服务提供者的aop处理）
 *
 * @author fengshuonan
 * @date 2018年05月15日14:58:44
 */
@Aspect
@Order(CHAIN_ON_PROVIDER_SORT)
public class ChainOnProviderAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截控制器层和远程提供者层
     */
    @Pointcut("execution(* *..provider.*.*(..))")
    public void cutService() {
    }

    /**
     * <pre>
     *  主要业务逻辑如下：
     *      1. 获取traceId，spanId, parentSpanId（header中）(没有就是没走网关)
     *      2. 推送接收到远程调用的日志，响应远程调用成功或失败的响应
     * </pre>
     */
    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        long begin = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("开始记录provider aop耗时！");
        }

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //生成本节点的spanId
        String currentSpanId = IdWorker.getIdStr();
        SpanIdHolder.set(currentSpanId);

        //获取当前节点的parentSpanId
        String parentSpanId = SpanIdContext.getSpanIdByRequestParam(methodParams);
        ParentSpanIdHolder.set(parentSpanId);

        //获取traceId
        String traceId = RequestNoContext.getRequestNoByRequestParam(methodParams);
        TraceIdHolder.set(traceId);

        //初始化临时LoginUser
        LoginUserHolder.init();

        if (logger.isDebugEnabled()) {
            logger.debug("provider aop 获取参数！" + (System.currentTimeMillis() - begin));
        }

        try {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            if (methodParams != null && methodParams.length > 0) {
                CheckUtil.validateParameters(methodParams);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 开始提供远程服务业务！" + (System.currentTimeMillis() - begin));
            }

            Object result = point.proceed();

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 提供远程服务完成！" + (System.currentTimeMillis() - begin));
            }

            return result;

        } catch (Throwable exception) {

            String exceptionMsg = ToolUtil.getExceptionMsg(exception);

            if (logger.isDebugEnabled()) {
                logger.debug("provider aop 记录完错误日志！" + (System.currentTimeMillis() - begin));
            }

            MethodSignature methodSignature = null;
            Signature signature = point.getSignature();
            if (signature instanceof MethodSignature) {
                methodSignature = (MethodSignature) signature;
            }

            //报告:发送给消费端失败的远程调用
            TraceUtil.trace(methodSignature, RpcPhaseEnum.EP3, traceId, currentSpanId, parentSpanId, exceptionMsg);

            throw exception;

        } finally {
            SpanIdHolder.remove();
            ParentSpanIdHolder.remove();
            TraceIdHolder.remove();
            LoginUserHolder.remove();
        }

    }

}
