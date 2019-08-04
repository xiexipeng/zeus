package com.xxp.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * <p> 数据源配置类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/4 下午5:08
 * @Version V1.0
 */
@Configuration
public class DatasourceConfig {

    @Bean
    @Qualifier("datasourceTx1")
    @ConfigurationProperties("spring.datasource.tx1")
    public DataSource getDataSourceTx1(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("jdbcTemplateTx1")
    public JdbcTemplate getJdbcTemplateTx1(@Qualifier("datasourceTx1") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean
    @Primary
    @Qualifier("datasourceTx2")
    @ConfigurationProperties("spring.datasource.tx2")
    public DataSource getDataSourceTx2(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("jdbcTemplateTx2")
    public JdbcTemplate getJdbcTemplate(@Qualifier("datasourceTx2") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
