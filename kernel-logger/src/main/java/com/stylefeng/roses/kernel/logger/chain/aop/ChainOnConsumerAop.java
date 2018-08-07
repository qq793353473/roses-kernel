package com.stylefeng.roses.kernel.logger.chain.aop;

import com.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.roses.kernel.logger.chain.context.ParentSpanIdHolder;
import com.stylefeng.roses.kernel.logger.chain.context.SpanIdHolder;
import com.stylefeng.roses.kernel.logger.chain.context.TraceIdHolder;
import com.stylefeng.roses.kernel.logger.chain.enums.RpcPhaseEnum;
import com.stylefeng.roses.kernel.logger.util.TraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import static com.stylefeng.roses.kernel.model.constants.AopSortConstants.CHAIN_ON_CONSUMMER_SORT;

/**
 * 基于调用链的服务治理系统的设计（feign远程调用层的aop处理）
 *
 * @author fengshuonan
 * @date 2018年05月15日14:58:44
 */
@Aspect
@Order(CHAIN_ON_CONSUMMER_SORT)
public class ChainOnConsumerAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.stylefeng.roses.*..consumer.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        MethodSignature methodSignature = null;
        Signature signature = point.getSignature();
        if (signature != null && signature instanceof MethodSignature) {
            methodSignature = (MethodSignature) signature;
        }

        long begin = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("开始记录consumer aop耗时！");
        }

        //获取当前节点的spanId
        String currentSpanId = SpanIdHolder.get();

        //获取当前节点的parentSpanId
        String parentSpanId = ParentSpanIdHolder.get();

        //获取traceId
        String traceId = TraceIdHolder.get();

        if (logger.isDebugEnabled()) {
            logger.debug("consumer aop 获取参数！" + (System.currentTimeMillis() - begin));
        }

        try {

            //报告:开始调用远程服务
            TraceUtil.trace(methodSignature, RpcPhaseEnum.P2, traceId, currentSpanId, parentSpanId);

            if (logger.isDebugEnabled()) {
                logger.debug("consumer aop 开始调用远程服务前！" + (System.currentTimeMillis() - begin));
            }

            Object result = point.proceed();

            if (logger.isDebugEnabled()) {
                logger.debug("consumer aop 调用完远程服务！" + (System.currentTimeMillis() - begin));
            }

            if (logger.isDebugEnabled()) {
                logger.debug("consumer aop 发送调用成功！" + (System.currentTimeMillis() - begin));
            }

            return result;

        } catch (Throwable exception) {

            String exceptionMsg = ToolUtil.getExceptionMsg(exception);

            //报告:调用远程服务失败
            TraceUtil.trace(methodSignature, RpcPhaseEnum.EP2, traceId, currentSpanId, parentSpanId, exceptionMsg);

            if (logger.isDebugEnabled()) {
                logger.debug("consumer aop 记录完错误日志！" + (System.currentTimeMillis() - begin));
            }

            throw exception;

        }

    }
}
