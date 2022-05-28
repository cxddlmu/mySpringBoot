package com.cxd.permissionManager.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuixiaodong on 2018/5/6.
 */
@Configuration
public class DataSourceConfiguration {
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setName("masterDataSource");
        return dataSource;
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setName("slaveDataSource");
        return dataSource;
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
//        dynamicDataSource.setDefaultTargetDataSource(dataSourceOne());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(2);
        dsMap.put("masterDataSource", masterDataSource());
        dsMap.put("slaveDataSource", slaveDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        return sqlSessionFactoryBean.getObject();
//    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
