package com.sec.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.sec.repository")
@PropertySource("classpath:db.properties")
public class DataConfig {

    @Autowired
    private Environment environment;

//    @Bean
//    public SessionFactory sessionFactory() {
//        LocalSessionFactoryBuilder builder =
//                new LocalSessionFactoryBuilder(dataSource());
//        builder.scanPackages("com.sec")
//                .addProperties(hibernateProperties());
//
//        return builder.buildSessionFactory();
//    }


    /************* Start Spring JPA config details **************/
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        //JDBC
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("SecPersistenceUnit");
        lcemfb.setPackagesToScan("com.sec.model");
        //Hibernate
        lcemfb.setJpaProperties(hibernateProperties());
        return lcemfb;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    /************* End Spring JPA config details **************/
    // JDBC
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    // Hibernate config
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // can use DIALECT in first field
        properties.put("hibernate.dialect",
                environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",
                environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",
                environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",
                environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.enable_lazy_load_no_trans",
                environment.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
    }
}