package net.capitalinfotech.config;

import java.util.Properties;
import javax.annotation.Resource;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@ComponentScan("net.capitalinfotech.graph.model")
@EnableNeo4jRepositories("net.capitalinfotech.graph.repositories")
public class MultipleDataSourceConfiguration {

	@Resource
	private Environment env;
	private static final Logger LOG = LoggerFactory.getLogger(MultipleDataSourceConfiguration.class);
	private static final String HOST = "spring.data.neo4j.uri";
	private static final String NEO_USER = "spring.data.neo4j.username";
	private static final String NEO_PWD = "spring.data.neo4j.password";
	private static final String NEO_DRIVER = "org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver";
	private static final String MYSQL_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String MYSQL_DS_URL = "spring.datasource.url";
	private static final String MYSQL_USER = "spring.datasource.username";
	private static final String MYSQL_PWD = "spring.datasource.password";
	private static final String SHOW_SQL = "hibernate.show_sql";
	private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
	private static final String C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
	private static final String C3P0_ACQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";
	private static final String C3P0_TIMEOUT = "hibernate.c3p0.timeout";
	private static final String C3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";
	private static final String SCAN_RDBMS = "net.capitalinfotech.rdbms";

	@Primary
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		// Setting JDBC properties
		props.put(MYSQL_DRIVER, env.getProperty(MYSQL_DRIVER));
		props.put(MYSQL_DS_URL, env.getProperty(MYSQL_DS_URL));
		props.put(MYSQL_USER, env.getProperty(MYSQL_USER));
		props.put(MYSQL_PWD, env.getProperty(MYSQL_PWD));
		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty(SHOW_SQL));
		props.put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty(C3P0_MIN_SIZE));
		props.put(C3P0_MAX_SIZE, env.getProperty(C3P0_MAX_SIZE));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty(C3P0_ACQUIRE_INCREMENT));
		props.put(C3P0_TIMEOUT, env.getProperty(C3P0_TIMEOUT));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty(C3P0_MAX_STATEMENTS));
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan(new String[] {"net.capitalinfotech.rdbms.model", "net.capitalinfotech.rdbms.repositories"});
		//factoryBean.setPackagesToScan(new String[]{SCAN_RDBMS});
		return factoryBean;
	}
	
	@Primary
	@Bean(name = "rdbmsTransactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
		HibernateTransactionManager htmanager = new HibernateTransactionManager();
		htmanager.setSessionFactory(sessionFactory.getObject());
		return htmanager;
	}

	@Bean
	public SessionFactory getNeoSessionFactory() {
		return new SessionFactory(configuration(), "net.capitalinfotech.graph.model");
	}

	@Bean(name = "neo4jTransactionManager")
	public Neo4jTransactionManager neo4jTransactionManager() throws Exception {
		return new Neo4jTransactionManager(getNeoSessionFactory());
	}

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		return new org.neo4j.ogm.config.Configuration.Builder().uri(env.getRequiredProperty(HOST))
				.credentials(env.getRequiredProperty(NEO_USER), env.getRequiredProperty(NEO_PWD)).build();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager,
			HibernateTransactionManager rdbmsTransactionManager) {
		return new ChainedTransactionManager(rdbmsTransactionManager, neo4jTransactionManager);
	}

}
