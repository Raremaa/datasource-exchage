package com.masaiqi.exchage.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切换aop
 * <p>
 * 解析数据源切换注解 {@link DataSourceChoose}
 * <p>
 * 注意@Order(1) 设置执行顺序，否则如果事务注解先处理，会导致切换数据源失效
 *
 * @author sq.ma
 * @date 2019/11/27 上午9:42
 */
@Aspect
@Component
@Order(1)
public class DataSourceAOP {

    @Pointcut("execution( * com.masaiqi.exchage.service..*.*(..))")
    public void service(){
    }

    @Before("service()")
    public void dataSourceExchange(JoinPoint joinPoint) throws NoSuchMethodException {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        Class clazz = target.getClass() ;
        Method m = clazz.getMethod(methodName, parameterTypes);
        if (m != null && m.isAnnotationPresent(DataSourceChoose.class)) {
            DataSourceChoose data = m.getAnnotation(DataSourceChoose.class);
            String dataSourceName = data.value();
            DataSourceContextHolder.setDBType(dataSourceName);
        }
    }


}
