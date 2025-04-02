package com.sshyu.protag.configure;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.sshyu.protag.adapter.out.persistence.mysql.*.jpa",
    entityManagerFactoryRef = "mySqlJpaEntityManagerFactory",
    transactionManagerRef = "mySqlJpaTransactionManager"
)
public class MySqlJpaDatabaseConfig {
    
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mySqlJpaDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlJpaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mySqlJpaDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("com.sshyu.protag.adapter.out.persistence.mysql.*.jpa")  // 엔티티가 위치한 패키지 경로
                .persistenceUnit("jpa")
                .build();

        // Hibernate 설정 추가
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.properties.", "create");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        factoryBean.setJpaPropertyMap(jpaProperties);
        return factoryBean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mySqlJpaTransactionManager(
            @Qualifier("mySqlJpaEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
