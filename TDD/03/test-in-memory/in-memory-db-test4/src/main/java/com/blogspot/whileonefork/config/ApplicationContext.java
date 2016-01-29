package com.blogspot.whileonefork.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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

    private static final String PROPERTY_NAME_DB_DRIVER_TAG                           = "jdbc.driverClassName";
    private static final String PROPERTY_NAME_DB_URL_TAG                              = "jdbc.url";
    private static final String PROPERTY_NAME_DB_USERNAME_TAG                         = "jdbc.username";
    private static final String PROPERTY_NAME_DB_PASSWORD_TAG                         = "jdbc.pwd";

    private static final String PROPERTY_NAME_JPA_SHOW_SQL_TAG                        = "jpa.showSql";
    private static final String PROPERTY_NAME_JPA_GENERATE_DDL_TAG                    = "jpa.generateDdl";
    private static final String PROPERTY_NAME_JPA_DATABASE_TAG                        = "jpa.platform";

    private static final String PROPERTY_NAME_QUERY_SUBSTITUTION = "hibernate.query.substitutions";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL                      = "hibernate.format_sql";

    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL_TAG = PROPERTY_NAME_HIBERNATE_FORMAT_SQL;
    
    @Value("classpath:create-db.sql")
    private Resource dbSchemaScript;

    @Value("classpath:populate-db.sql")
    private Resource dbDataScript;

    @Value("classpath:drop-data.sql")
    private Resource dbCleanerScript;

    @javax.annotation.Resource
    private Environment         env;

    @Bean
    public DaoHelper daoHelper() {
        return new DaoHelper();
    }
         
    @Bean
    public DataSource dataSource() {
            return createDataSource();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {

        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource());
        initializer.setDatabasePopulator(databasePopulator());
        initializer.setDatabaseCleaner(databaseCleaner());
        initializer.setEnabled(isInitializerEnabled());
        return initializer;
    }
    
    public boolean isInitializerEnabled() {
        return true;
    }
    
    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbSchemaScript);
        populator.addScript(dbDataScript);
        return populator;
    }
    
    private DatabasePopulator databaseCleaner() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbCleanerScript);
        return populator;
    }

    private DataSource createDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DB_DRIVER_TAG));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DB_URL_TAG));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DB_USERNAME_TAG));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD_TAG));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        factory.setJpaVendorAdapter(jpaVendorAdapter());
//        factory.setPackagesToScan("com.sample.model");
        factory.setDataSource(dataSource());     

        factory.setJpaProperties(jpaProperties());
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

        return factory;
    }
    
    private JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(env.getRequiredProperty(PROPERTY_NAME_JPA_DATABASE_TAG, Database.class));
        adapter.setGenerateDdl(env.getProperty(PROPERTY_NAME_JPA_GENERATE_DDL_TAG, Boolean.class, false));
        adapter.setShowSql(env.getRequiredProperty(PROPERTY_NAME_JPA_SHOW_SQL_TAG, Boolean.class));
        return adapter;
        
    }

    private Properties jpaProperties() {
        final Properties props = new Properties();
        /*
        props.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT_TAG));
        props.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, "none");
        props.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL_TAG));
         */
        props.put(PROPERTY_NAME_QUERY_SUBSTITUTION, "true 'Y', false 'N'");
        props.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, 
                env.getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL_TAG, Boolean.class, true));
        return props;
    }

}
