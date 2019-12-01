package com.masaiqi.exchage.config;

/**
 * 数据源上下文环境，记录当前使用数据源
 * <p>
 * 数据源类型定义见{@link DataSourceType}
 *
 * @author sq.ma
 * @date 2019/11/26 下午6:06
 */
public class DataSourceContextHolder {

    /**
     * 数据源beanName存放容器
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源类型
     *
     * @param dbType {@link DataSourceType}
     * @return void
     * @author sq.ma
     * @date 2019/11/26 下午6:08
     */
    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源类型
     *
     * @param
     * @return java.lang.String {@link DataSourceType}
     * @author sq.ma
     * @date 2019/11/26 下午6:07
     */
    public static String getDBType() {
        return contextHolder.get();
    }


    /**
     * 清除数据源类型
     *
     * @param
     * @return void
     * @author sq.ma
     * @date 2019/11/26 下午6:08
     */
    public static void clearDBType() {
        contextHolder.remove();
    }
}
