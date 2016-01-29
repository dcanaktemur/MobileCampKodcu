package com.blogspot.whileonefork.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.blogspot.whileonefork.db.DaoHelper;

@Configuration
@EnableTransactionManagement
@PropertySource({ "db.properties", "db.inmemory-overrides.properties" })
public class ApplicationContext {

    private static final String PERSISTENCE_UNIT_NAME = "persistenceUnit";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DEFAULT_VALUE = "org.hibernate.cfg.ImprovedNamingStrategy";
    private static final String PROPERTY_NAME_HIBERNATE_CACHE_REGION_PREFIX_DEFAULT_VALUE = "hibernate.cache";
    private static final String PROPERTY_NAME_CONNECTION_RELEASE_MODE_DEFAULT_VALUE = "auto";

    private static final String PROPERTY_NAME_DB_DRIVER_TAG                           = "jdbc.driverClassName";
    private static final String PROPERTY_NAME_DB_URL_TAG                              = "jdbc.url";
    private static final String PROPERTY_NAME_DB_USERNAME_TAG                         = "jdbc.username";
    private static final String PROPERTY_NAME_DB_PASSWORD_TAG                         = "jdbc.pwd";

    private static final String PROPERTY_NAME_DB_DEFAUL_AUTO_COMMIT_TAG="jdbc.defaultAutoCommit";
    private static final String PROPERTY_NAME_DB_accessToUnderlyingConnectionAllowed_TAG="jdbc.accessToUnderlyingConnectionAllowed";
    private static final String PROPERTY_NAME_DB_initialSize_TAG="jdbc.initialSize";
    private static final String PROPERTY_NAME_DB_MAX_TOTAL_TAG="jdbc.maxActive";
    private static final String PROPERTY_NAME_DB_maxIdle_TAG="jdbc.maxIdle";
    private static final String PROPERTY_NAME_DB_minIdle_TAG="jdbc.minIdle";
    private static final String PROPERTY_NAME_DB_maxWaitMillis_TAG="jdbc.maxWait";
    private static final String PROPERTY_NAME_DB_validationQuery_TAG="jdbc.validationQuery";
    private static final String PROPERTY_NAME_DB_testOnBorrow_TAG="jdbc.testOnBorrow";
    private static final String PROPERTY_NAME_DB_testOnReturn_TAG="jdbc.testOnReturn";
    private static final String PROPERTY_NAME_DB_testWhileIdle_TAG="jdbc.testWhileIdle";
    private static final String PROPERTY_NAME_DB_timeBetweenEvictionRunsMillis_TAG="jdbc.timeBetweenEvictionRunsMillis";
    private static final String PROPERTY_NAME_DB_numTestsPerEvictionRun_TAG="jdbc.numTestsPerEvictionRun";
    private static final String PROPERTY_NAME_DB_minEvictableIdleTimeMillis_TAG="jdbc.minEvictableIdleTimeMillis";

    private static final String PROPERTY_NAME_JPA_SHOW_SQL_TAG                        = "jpa.showSql";
    private static final String PROPERTY_NAME_JPA_GENERATE_DDL_TAG                    = "jpa.generateDdl";
    private static final String PROPERTY_NAME_JPA_DATABASE_TAG                        = "jpa.platform";

    
//    private static final String PROPERTY_NAME_HIBERNATE_DIALECT                         = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL                      = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY                 = "hibernate.ejb.naming_strategy";
//    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL                        = "hibernate.show_sql";
//    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN_TAG        = "entitymanager.packages.to.scan";
    
    private static final Object PROPERTY_NAME_CONNECTION_RELEASE_MODE = "hibernate.connection.release_mode";
    private static final Object PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final Object PROPERTY_NAME_HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics";
    private static final Object PROPERTY_NAME_HIBERNATE_JDBC_USE_SCROLLABLE_RESULT_SET = "hibernate.jdbc.use_scrollable_resultset";
    private static final Object PROPERTY_NAME_HIBERNATE_JDBC_USE_STREAM_FOR_BINARY = "hibernate.jdbc.use_streams_for_binary";
    private static final Object PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final Object PROPERTY_NAME_HIBERNATE_CACHE_REGION_PREFIX = "hibernate.cache.region_prefix";
    private static final Object PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    private static final Object PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";

    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL_TAG = PROPERTY_NAME_HIBERNATE_FORMAT_SQL;
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_TAG = PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY;
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE_TAG = "hibernate.batchSize";

    @Resource
    private Environment         env;

    @Bean
    public DaoHelper daoHelper() {
        return new DaoHelper();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DB_DRIVER_TAG));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DB_URL_TAG));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DB_USERNAME_TAG));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD_TAG));
                
        dataSource.setDefaultAutoCommit(
                env.getRequiredProperty(PROPERTY_NAME_DB_DEFAUL_AUTO_COMMIT_TAG, Boolean.class));
        dataSource.setAccessToUnderlyingConnectionAllowed(
                env.getRequiredProperty(PROPERTY_NAME_DB_accessToUnderlyingConnectionAllowed_TAG, Boolean.class));
        dataSource.setInitialSize(env.getRequiredProperty(PROPERTY_NAME_DB_initialSize_TAG, Integer.class));
        dataSource.setMaxTotal(env.getRequiredProperty(PROPERTY_NAME_DB_MAX_TOTAL_TAG, Integer.class));
        dataSource.setMaxIdle(env.getRequiredProperty(PROPERTY_NAME_DB_maxIdle_TAG, Integer.class));
        dataSource.setMinIdle(env.getRequiredProperty(PROPERTY_NAME_DB_minIdle_TAG, Integer.class));
        dataSource.setMaxWaitMillis(env.getRequiredProperty(PROPERTY_NAME_DB_maxWaitMillis_TAG, Integer.class));
        dataSource.setValidationQuery(env.getRequiredProperty(PROPERTY_NAME_DB_validationQuery_TAG));
        dataSource.setTestOnBorrow(env.getRequiredProperty(PROPERTY_NAME_DB_testOnBorrow_TAG, Boolean.class));
        dataSource.setTestOnReturn(env.getRequiredProperty(PROPERTY_NAME_DB_testOnReturn_TAG, Boolean.class));
        dataSource.setTestWhileIdle(env.getRequiredProperty(PROPERTY_NAME_DB_testWhileIdle_TAG, Boolean.class));
        dataSource.setTimeBetweenEvictionRunsMillis(
                env.getRequiredProperty(PROPERTY_NAME_DB_timeBetweenEvictionRunsMillis_TAG, Integer.class));
        dataSource.setNumTestsPerEvictionRun(
                env.getRequiredProperty(PROPERTY_NAME_DB_numTestsPerEvictionRun_TAG, Integer.class));
        dataSource.setMinEvictableIdleTimeMillis(
                env.getRequiredProperty(PROPERTY_NAME_DB_minEvictableIdleTimeMillis_TAG, Integer.class));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(env.getRequiredProperty(PROPERTY_NAME_JPA_DATABASE_TAG, Database.class));
        adapter.setGenerateDdl(env.getProperty(PROPERTY_NAME_JPA_GENERATE_DDL_TAG, Boolean.class, false));
        adapter.setShowSql(env.getRequiredProperty(PROPERTY_NAME_JPA_SHOW_SQL_TAG, Boolean.class));
        return adapter;
    }
    
    private Properties jpaProperties() {
        Properties props = new Properties();
/*
        props.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT_TAG));
        props.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, "none");
        props.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL_TAG));
  */
        props.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                env.getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL_TAG, Boolean.class, true));
        props.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                env.getProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_TAG, String.class, 
                        PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY_DEFAULT_VALUE));
        
        props.put(PROPERTY_NAME_CONNECTION_RELEASE_MODE, 
                PROPERTY_NAME_CONNECTION_RELEASE_MODE_DEFAULT_VALUE);
        
        props.put(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS, true);
        props.put(PROPERTY_NAME_HIBERNATE_GENERATE_STATISTICS, true);
        props.put(PROPERTY_NAME_HIBERNATE_JDBC_USE_SCROLLABLE_RESULT_SET, true);
        props.put(PROPERTY_NAME_HIBERNATE_JDBC_USE_STREAM_FOR_BINARY, true);
        props.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE_TAG));
        props.put(PROPERTY_NAME_HIBERNATE_CACHE_REGION_PREFIX, 
                PROPERTY_NAME_HIBERNATE_CACHE_REGION_PREFIX_DEFAULT_VALUE);
        props.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE, false);
        props.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, false);
        
       return props;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        
        factory.setDataSource(dataSource());
//        entityManagerFactoryBean.setPackagesToScan(environment
//                .getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN_TAG));
//        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        
        factory.setJpaProperties(jpaProperties());
        
        return factory;
    }

}
