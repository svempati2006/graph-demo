package net.capitalinfotech.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@ComponentScan("net.capitalinfotech.graph.model")
@EnableNeo4jRepositories("net.capitalinfotech.graph.repositories")
@EnableJpaRepositories(basePackages = "net.capitalinfotech.rdbms.repositories")
public class MultipleDataSourceConfiguration {

	@Resource
	private Environment env;
	private static final Logger LOGGER = LoggerFactory.getLogger(MultipleDataSourceConfiguration.class);
	private static final String HOST = "spring.data.neo4j.uri";
	private static final String NEO_USER = "spring.data.neo4j.username";
	private static final String NEO_PWD = "spring.data.neo4j.password";
	private static final String NEO_DRIVER = "org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver";
	private static final String MYSQL_DRIVER = "spring.datasource.driver-class-name";
	private static final String MYSQL_DS_URL = "spring.datasource.url";
	private static final String MYSQL_USER = "spring.datasource.username";
	private static final String MYSQL_PWD = "spring.datasource.password";
	private static final String SHOW_SQL = "hibernate.show_sql";
	private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HB_NMGL_STGY = "spring.jpa.hibernate.naming-strategy";
	private static final String HB_DIALECT = "hibernate.dialect";
	private static final String HB_BYTCD = "hibernate.bytecode.provider";
	private static final String HB_CHAR = "hibernate.connection.charSet";
	private static final String HB_INSERTS = "hibernate.order_inserts";
	private static final String HB_BATCH_SIZE = "hibernate.jdbc.batch_size";
	private static final String SCAN_RDBMS = "net.capitalinfotech.rdbms";
	private static final String SCAN_GRAPH_MODEL = "net.capitalinfotech.graph.model";
	/**
	private static final String C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
	private static final String C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
	private static final String C3P0_ACQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";
	private static final String C3P0_TIMEOUT = "hibernate.c3p0.timeout";
	private static final String C3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";

	 **/
	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName(env.getProperty(MYSQL_DRIVER))
				.url(env.getProperty(MYSQL_DS_URL)).username(env.getProperty(MYSQL_USER))
				.password(env.getProperty(MYSQL_PWD)).build();
	}

	@Primary
	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(SCAN_RDBMS);
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		Map<String, String> jpaProperties = new HashMap<>();
		jpaProperties.put(HB_CHAR, env.getProperty(HB_CHAR));
		jpaProperties.put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
		jpaProperties.put(HB_NMGL_STGY, env.getProperty(HB_NMGL_STGY));
		jpaProperties.put(HB_BYTCD, env.getProperty(HB_BYTCD));
		jpaProperties.put(HB_DIALECT, env.getProperty(HB_DIALECT));
		jpaProperties.put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
		jpaProperties.put(HB_INSERTS, env.getProperty(HB_INSERTS));
		jpaProperties.put(HB_BATCH_SIZE, env.getProperty(HB_BATCH_SIZE));

		entityManagerFactory.setJpaPropertyMap(jpaProperties);
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		LOGGER.debug("+++++++++++++++++++++++++ Init EntityManagerFactory completed +++++++++++++++");
		return entityManagerFactory;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Autowired
	@Primary
	@Bean(name = "rdbmsTransactionManager")
	public JpaTransactionManager mysqlTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory)
			throws Exception {
		LOGGER.debug("+++++++++++++++++++++++++ Init JpaTransactionManager completed +++++++++++++++");
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Bean
	public SessionFactory sessionFactory() {
		LOGGER.debug("+++++++++++++++++++++++++ Init Graph SessionFactory completed +++++++++++++++");
		return new SessionFactory(neoConfiguration(), SCAN_GRAPH_MODEL);
	}

	@Bean(name = "neo4jTransactionManager")
	public Neo4jTransactionManager neo4jTransactionManager() throws Exception {
		LOGGER.debug("+++++++++++++++++++++++++ Init Neo4jTransactionManager completed +++++++++++++++");
		return new Neo4jTransactionManager(sessionFactory());
	}

	@Bean
	public org.neo4j.ogm.config.Configuration neoConfiguration() {
		return new org.neo4j.ogm.config.Configuration.Builder().uri(env.getRequiredProperty(HOST))
				.credentials(env.getRequiredProperty(NEO_USER), env.getRequiredProperty(NEO_PWD)).build();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager,
			JpaTransactionManager rdbmsTransactionManager) {
		return new ChainedTransactionManager(rdbmsTransactionManager, neo4jTransactionManager);
	}
}
