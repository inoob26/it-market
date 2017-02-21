package com.itmarket.App;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import java.util.Properties;

/**
 *
 * @author inoob
 */
@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
@EnableJpaRepositories
public class AppConfig {

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_HIBERNATE_DIALECT = "db.hibernate.dialect";
    private static final String DB_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String DB_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    private static final String DB_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";

    @Resource
    private Environment environment;

    @Bean
    public DriverManagerDataSource itemDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty(DB_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(DB_URL));
        dataSource.setUsername(environment.getRequiredProperty(DB_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(DB_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();

        emFactory.setDataSource(itemDataSource());
        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emFactory.setPackagesToScan(environment.getRequiredProperty(DB_ENTITYMANAGER_PACKAGES_TO_SCAN));
        emFactory.setJpaProperties(getHibernateProperties());

        return emFactory;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.put(DB_HIBERNATE_DIALECT,environment.getProperty(DB_HIBERNATE_DIALECT));
        properties.put(DB_HIBERNATE_SHOW_SQL,environment.getProperty(DB_HIBERNATE_SHOW_SQL));
        properties.put(DB_HIBERNATE_HBM2DDL_AUTO,environment.getProperty(DB_HIBERNATE_HBM2DDL_AUTO));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
