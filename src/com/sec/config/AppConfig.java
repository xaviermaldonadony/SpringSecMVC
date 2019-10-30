package com.sec.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;


public class AppConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory(){
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		Properties properties = new Properties();
		//JDBC porperties
		properties.put(DRIVER,env.getProperty("jdbc.driver"));
		properties.put(URL, env.getProperty("jdbc.url"));
		properties.put(USER, env.getProperty("jdbc.username"));
		properties.put(PASS, env.getProperty("jdbc.password"));
		
		//HIbernate Properties
		properties.put( DIALECT, env.getProperty("hibernate.dialect"));
		properties.put( SHOW_SQL, env.getProperty("hibernate.show_sql"));
		properties.put( FORMAT_SQL, env.getProperty("hibernate.format_sql"));
		properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put(ENABLE_LAZY_LOAD_NO_TRANS, env.getProperty("hibernate.enabled"));

		factoryBean.setHibernateProperties(properties);
		factoryBean.setPackagesToScan("com.sec");
		
		return factoryBean;
		
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();			
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
		
}
