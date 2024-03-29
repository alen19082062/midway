package com.gg.midway.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect  // 成为切面类
@Component
public class WebLogAcpect {
    private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);

    static {
        System.out.println("WebLogAcpect's initilize static statements ...");
    }

    /**
     * 定义切入点，切入点为com.gg.midway.aop.web下的所有函数
     */
    @Pointcut("execution(public * com.gg.midway.aop.web..*.*(..))")
    public void webLog() {
        System.out.println("webLog() ======================  ");
        System.out.println("webLog() init... 切入点 ： execution(public * com.gg.spring.aop.web..*.*(..))");
    }

    /**
     * 前置通知：在连接点之前执行的通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("doBefore()  =================== ");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        // System.out.println("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("doAfterReturning() ...");
        logger.info("RESPONSE : " + ret);
    }
}
