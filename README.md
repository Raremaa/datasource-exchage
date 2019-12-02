# 本项目是一个动态数据源切换的例子

## 拥有以下特性：

- 每种数据源要能够实现单例
- 能够正常执行事务提交回滚
- 数据源切换的线程安全问题
- 符合SpringBoot整体风格，`约定优于配置（convention over configuration）`

## 采用`@DataSourceChoose`注解进行数据源选择
- 这个注解用来选择使用的数据源
- 它应该被用于需要用到dataSource的地方
- 与事务注解`@Transactional`不冲突，AOP里解析注解时已经配置了解析顺序
- 仅支持`com.masaiqi.exchage.service`下的类的方法使用，否则需要在`com.masaiqi.exchage.config.DataSourceAOP`中配置切入点

## 切忌不要直接使用`DataSourceContextHolder`切换数据源
- 这个类理论上也可以切换数据源，但是与事务注解`@Transactional`冲突，默认先执行事务注解，会导致数据源切换失败

## 联系我一起讨论
- [个人博客](https://www.masaiqi.com)
- E-mail：masaiqi.com@gmail.com
