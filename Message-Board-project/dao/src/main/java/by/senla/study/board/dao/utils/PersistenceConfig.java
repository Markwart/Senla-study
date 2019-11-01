package by.senla.study.board.dao.utils;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = { "by.senla.study.board.dao" })
public class PersistenceConfig {

	@Bean
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { "by.senla.study.board.model.entity" });
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/message_board?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}

	public Properties getHibernateProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put(Environment.DRIVER, "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.put(Environment.SHOW_SQL, true);
		jpaProperties.put(Environment.FORMAT_SQL, false);
		jpaProperties.put(Environment.POOL_SIZE, "1");
		jpaProperties.put(Environment.HBM2DDL_AUTO, "update");
		jpaProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		jpaProperties.put(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.internal.NoCacheProvider");

		jpaProperties.put("hibernate.search.default.directory_provider", "filesystem");
		jpaProperties.put("hibernate.search.default.indexBase", "./data/lucene/indexes");
		return jpaProperties;
	}
}
