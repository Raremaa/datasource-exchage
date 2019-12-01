package com.masaiqi.exchage.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sq.ma
 * @date 2019/11/26 上午9:58
 */
@Configuration
public class DatabaseConfig {

    /**
     * dev环境配置
     */
    @Value("${datasource.dev.driverClass}")
    private String devDriverClass;

    @Value("${datasource.dev.url}")
    private String devUrl;

    @Value("${datasource.dev.username}")
    private String devUsername;

    @Value("${datasource.dev.password}")
    private String devPassword;

    /**
     * 生产环境配置
     */
    @Value("${datasource.prod.driverClass}")
    private String prodDriverClass;

    @Value("${datasource.prod.url}")
    private String prodUrl;

    @Value("${datasource.prod.username}")
    private String prodUsername;

    @Value("${datasource.prod.password}")
    private String prodPassword;

    @Bean(name = "devDataSource")
    public DruidDataSource getDevDataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(devUrl);
        datasource.setUsername(devUsername);
        datasource.setPassword(devPassword);
        datasource.setDriverClassName(devDriverClass);
        datasource.setFilters("stat,wall");
        return datasource;
    }

    @Bean(name = "prodDataSource")
    public DruidDataSource getProdDataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(prodUrl);
        datasource.setUsername(prodUsername);
        datasource.setPassword(prodPassword);
        datasource.setDriverClassName(prodDriverClass);
        datasource.setFilters("stat,wall");
        return datasource;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(MultipleDataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/masaiqi/exchage/mapper/**.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(MultipleDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置动态数据源
     * <p>
     * 数据源类型见 {@link DataSourceType}
     *
     * @param devDataSource dev数据源
     * @param prodDataSource prod数据源
     * @return {@link com.masaiqi.exchage.config.MultipleDataSource}
     * @author sq.ma
     * @date 2019/11/26 下午10:07
     */
    @Bean
    public MultipleDataSource dynamicDataSource(@Qualifier("devDataSource") DruidDataSource devDataSource, @Qualifier("prodDataSource")DruidDataSource prodDataSource) {
        //配置切换的动态数据源
        Map<Object, Object> datasourceMap = new HashMap<>(2);
        datasourceMap.put(DataSourceType.DB_DEV, devDataSource);
        datasourceMap.put(DataSourceType.DB_PROD, prodDataSource);
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        multipleDataSource.setTargetDataSources(datasourceMap);
        //配置默认数据源
        multipleDataSource.setDefaultTargetDataSource(devDataSource);
        return multipleDataSource;
    }

}
