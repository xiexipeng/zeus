package com.xxp.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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
    @Qualifier("accountDatasource")
    @ConfigurationProperties("spring.datasource.account")
    public DataSource getDataSourceTx1(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("accountJdbcTemplate")
    public JdbcTemplate getaccountJdbcTemplate(@Qualifier("accountDatasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    @Qualifier("orderDatasource")
    @ConfigurationProperties("spring.datasource.order")
    public DataSource getDataSourceTx2(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("orderJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("orderDatasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    /**
     * 链式事务管理器
     * @return
     */
    @Bean
    PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager accountTM = new DataSourceTransactionManager(getDataSourceTx1());
        DataSourceTransactionManager orderTM = new DataSourceTransactionManager(getDataSourceTx2());
        ChainedTransactionManager transactionManager = new ChainedTransactionManager(accountTM,orderTM);
        return transactionManager;
    }
}
