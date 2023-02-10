package sv.gob.bandesal.blog.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

//@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence-jndi.properties")
@EnableJpaRepositories(basePackages = "sv.gob.bandesal.blog.repository", entityManagerFactoryRef = "bandesalEntityManagerFactory", transactionManagerRef = "bandesalTransactionManager")
public class PersistenceJNDIConfig {

	@Autowired
	private Environment env;

	@Bean(name = "bandesalEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.setPackagesToScan(env.getProperty("db.packages.to.scan"));
		em.setDataSource(dataSource());
		em.setJpaProperties(hibProperties());
		return em;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
		properties.put("hibernate.format_sql", env.getProperty("db.hibernate.format_sql"));
		properties.put("hibernate.show_sql", env.getProperty("db.hibernate.show_sql"));
		properties.put("hibernate.enable_lazy_load_no_trans", env.getProperty("db.hibernate.lazy.load"));
		return properties;
	}

	@Bean
	DataSource dataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup(env.getProperty("db.jndiName"));
	}

	@Bean
	PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	JdbcTemplate jdbcTemplate() throws NamingException {
		return new JdbcTemplate(dataSource());
	}

}
