package com.k8port.microservicecoursemanagement.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static java.util.Objects.requireNonNull;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private final Environment env;

    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean icemfb = new LocalContainerEntityManagerFactoryBean();
        icemfb.setJpaVendorAdapter(getJPAVendorAdapter());
        icemfb.setDataSource(dataSource());
        icemfb.setPersistenceUnitName("entityManagerFactory");
        icemfb.setPackagesToScan("com.k8port.microservicecoursemanagement.model");
        icemfb.setJpaProperties(hibernateProperties());
        return icemfb;
    }

    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(requireNonNull(entityManagerFactory().getObject()));
    }

    @Bean
    public JpaVendorAdapter getJPAVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        basicDataSource.setUrl(env.getProperty("spring.datasource.url"));
        basicDataSource.setUsername(env.getProperty("spring.datasource.username"));
        basicDataSource.setPassword(env.getProperty("spring.datasource.password"));
        return basicDataSource;
    }

    final Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", requireNonNull(env.getProperty("hibernate.dialect")));
        properties.put("hibernate.format_sql", requireNonNull(env.getProperty("hibernate.format_sql"))) ;
        properties.put("hibernate.max_fetch_depth", requireNonNull(env.getProperty("hibernate.max_fetch_depth")));
        properties.put("hibernate.cache.use_second_level_cache", requireNonNull(env.getProperty("hibernate.cache.use_second_level_cache")));
        properties.put("hibernate.cache.use_minimal_puts", requireNonNull(env.getProperty("hibernate.cache.use_minimal_puts")));
        properties.put("hibernate.connection_release_mode", requireNonNull(env.getProperty("hibernate.connection_release_mode")));
        properties.put("hibernate.cache.use_query_cache", requireNonNull(env.getProperty("hibernate.cache.use_query_cache")));
        return properties;
    }

}
