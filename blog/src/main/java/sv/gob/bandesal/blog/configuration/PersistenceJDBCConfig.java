package sv.gob.bandesal.blog.configuration;

import java.util.Properties;

import javax.naming.NamingException;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@Profile("development")
@PropertySource("classpath:persistence-jdbc.properties")
@EnableJpaRepositories(basePackages = "sv.gob.bandesal.blog.repository", entityManagerFactoryRef = "bandesalEntityManagerFactory", transactionManagerRef = "bandesalTransactionManager")
public class PersistenceJDBCConfig {

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

	@Bean(name = "cegisadatasource")
    @ConfigurationProperties(prefix = "db.database")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
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
