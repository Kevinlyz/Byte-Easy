package com.mbyte.easy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @Author 王震
 * @Description //TODO 指定事务管理器
 * @Date 11:42 2019/1/26
 * @Param 
 * @return 
 **/
@Configuration
@ComponentScan
public class TransactionConfig  implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;
   
    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
