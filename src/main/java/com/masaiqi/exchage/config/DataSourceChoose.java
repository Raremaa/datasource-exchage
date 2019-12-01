package com.masaiqi.exchage.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换数据源注解
 *
 * @author sq.ma
 * @date 2019/11/27 上午10:02
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSourceChoose {

    /**
     * 数据源名称
     * <p>
     * @see DataSourceType
     */
    String value();
}
