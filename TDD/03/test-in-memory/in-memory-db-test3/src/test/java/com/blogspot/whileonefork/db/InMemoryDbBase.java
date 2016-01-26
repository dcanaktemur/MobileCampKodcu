package com.blogspot.whileonefork.db;

import java.sql.Connection;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;

import com.blogspot.whileonefork.config.ApplicationContext;

@ContextConfiguration(classes = {ApplicationContext.class})
public class InMemoryDbBase {

    private static final String  SHUTDOWN_TAG           = "SHUTDOWN";
    private static final String  UNABLE_TO_SETUP_SCHEMA = "Unable to setup schema; export failed";
    
    private Connection conn;
    
    //use the prefix "&" to get the actual factory, not the proxy
    @Resource(name="&entityManagerFactory")
    private LocalContainerEntityManagerFactoryBean emf;

    @Resource
    private DaoHelper daoHelper;
    
    public void setup() throws Exception {
        conn = emf.getDataSource().getConnection();

        PersistenceUnitInfo persistenceUnitInfo = emf.getPersistenceUnitInfo();
        Map<String, Object> jpaPropertyMap = emf.getJpaPropertyMap();
      
        EntityManagerFactoryBuilderImpl factoryBuilder =
                new EntityManagerFactoryBuilderImpl( 
                        new PersistenceUnitInfoDescriptor(persistenceUnitInfo), 
                        jpaPropertyMap );
        
        Configuration configuration = 
                factoryBuilder.buildHibernateConfiguration(new StandardServiceRegistryBuilder().build());
        
        SchemaExport exporter = new SchemaExport(configuration, conn);
        exporter.execute(true, true, false, true);

        if (!exporter.getExceptions().isEmpty()) {
            throw new IllegalStateException(UNABLE_TO_SETUP_SCHEMA);
        }
    }

    public void teardown() throws Exception {
        // see http://hsqldb.org/doc/guide/running-chapt.html#rgc_closing_db
        if (conn != null) {
            conn.createStatement().execute(SHUTDOWN_TAG);
            conn.close();
            conn = null;
        }
    }

    public DaoHelper getDaoHelp() {
        return daoHelper;
    }

    public void setDaoHelp(DaoHelper daoHelp) {
        this.daoHelper = daoHelp;
    }

}
