package com.xxp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/07/02 17:55:29
 * @description: 数据源配置
 * @Version V1.0
 **/
@Configuration
@MapperScan(value = "com.xxp.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Bean("DataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean("transactionManager")
    @Autowired
    public DataSourceTransactionManager getTransactionManager(@Qualifier("DataSource") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }

    @Bean("sqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(@Qualifier("DataSource") DataSource ds) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = getSqlSessionFactoryBean(ds, "classpath*:sqlmap/*.xml");
        return sqlSessionFactoryBean.getObject();
    }

    public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource ds, String sqlMapLocation) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        //扫描mapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(sqlMapLocation));
        return sqlSessionFactoryBean;
    }
}
