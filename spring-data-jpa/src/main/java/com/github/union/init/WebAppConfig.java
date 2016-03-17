package com.github.union.init;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.github.union")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.github.union.repository")
public class WebAppConfig {
    private static final String PROPEERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPEERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPEERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPEERTY_NAME_DATABASE_PASSWORD = "db.password";

    private static final String PROPEERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPEERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPEERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty(PROPEERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPEERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPEERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPEERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPEERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPEERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPEERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPEERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPEERTY_NAME_HIBERNATE_SHOW_SQL));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}