package com.xiaozhu.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    private Logger log = LoggerFactory.getLogger(MyAspect.class);
    private Long startTime;
    private Long  endTime;
    @Pointcut("execution(* com.xiaozhu.controller.*.*(..))")
    public void cutPoint(){

    }
    //JoinPoint joinPoint 参数选填
    @Before("cutPoint()")
    public void before(){
        startTime = System.currentTimeMillis();
        log.info("before aspect time is {}",startTime);
    }

    @After("cutPoint()")
    public void after(JoinPoint joinPoint){
        Signature sign = joinPoint.getSignature();
        String methodName = sign.getName();
        endTime = System.currentTimeMillis();
        log.info("afterAspect {} 方法耗时 {}",methodName,endTime-startTime);
    }
//    spring 的环绕通知和前置通知，后置通知有着很大的区别，主要有两个重要的区别：
//            1） 目标方法的调用由环绕通知决定，即你可以决定是否调用目标方法，而前置和后置通知   是不能决定的，他们只是在方法的调用前后执行通知而已，即目标方法肯定是要执行的。
//
//            2）  环绕通知可以控制返回对象，即你可以返回一个与目标对象完全不同的返回值，虽然这很危险，但是你却可以办到。而后置方法是无法办到的，因为他是在目标方法返回值后调用
//@around 必须返回object @afterrunning 才能获取到返回值
    @AfterReturning(value="cutPoint()",returning="result")
    public void testAfterReturning(JoinPoint jp,Object result) throws Exception{
        Signature st = jp.getSignature();
        String methodName = st.getName();
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(result);
        log.info("{} 方法的返回值是：{}",methodName,value);
    }

    @AfterThrowing(value="cutPoint()",throwing="e")
    public void optException(JoinPoint jp,Exception e){
        String methodName = jp.getSignature().getName();
        log.info("方法：{},跑出异常：{}",methodName,e.getMessage());
    }

    @Around("cutPoint()")
    //可以通过catch 处理异常，假如异常不再抛出，ExceptionHandle 将不再处理异常
    public Object around(ProceedingJoinPoint pjp) throws Exception{
        Object result = null;
        try{
            log.info("开始执行 around 方法");
            //在pjp.proceed的前后执行 before after 方法
            result = pjp.proceed();
            log.info("结束执行 around 方法");
        }catch(Throwable e){
            //System.out.println(e.getMessage());
            Exception ex = new Exception(e);
            throw ex;
        }
        return result;

    }


}
