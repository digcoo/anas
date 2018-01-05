package com.slife.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * Created by cq on 18-1-5.
 */
@Configuration
@MapperScan("com.slife.dao")
@EnableTransactionManagement
public class DataBaseConfig {

    @Value("${app.db.url}")
    private String url;

    @Value("${app.db.username}")
    private String username;

    @Value("${app.db.password}")
    private String password;

    @Value(value = "classpath:mybatis/sqlmap/*.xml")
    private Resource[] mapperLocations;

    @Value(value = "classpath:mybatis/mybatis-config.xml")
    private Resource configLocation;

    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(3);
        dataSource.setInitialPoolSize(3);
        dataSource.setMaxIdleTime(60);
        dataSource.setCheckoutTimeout(3000);
        dataSource.setAcquireIncrement(3);
        dataSource.setAcquireRetryAttempts(0);
        dataSource.setAcquireRetryDelay(1000);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxStatements(100);
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setMapperLocations(mapperLocations);
        ssfb.setConfigLocation(configLocation);
        ssfb.setTypeAliasesPackage("com.cq.ligal.domain.data");
        ssfb.setDataSource(dataSource());
        return ssfb;
    }
    @Bean(name = "transactionManager",autowire = Autowire.BY_NAME)
    @Primary
    public DataSourceTransactionManager transactionManager() throws SQLException{
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean(name = "transactionTemplate")
    @Primary
    public TransactionTemplate transactionTemplate() throws SQLException{
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager());
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return template;
    }
}
