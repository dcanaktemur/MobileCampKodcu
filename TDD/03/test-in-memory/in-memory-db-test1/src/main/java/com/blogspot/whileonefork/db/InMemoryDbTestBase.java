package com.blogspot.whileonefork.db;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class InMemoryDbTestBase {
    
    private static final String SHUTDOWN_TAG = "SHUTDOWN";
    private static final String NO_LOCAL_SESSION_FACTORY_MSG = 
            "No LocalSessionFactoryBean; perhaps you didn't setup @RunWith and @ContextConfiguration on your test?";
    private static final String UNABLE_TO_SETUP_SCHEMA = "Unable to setup schema; export failed";
            
    //use the prefix "&" to get the actual factory, not the proxy
    @Resource(name="&sessionFactory")
    private LocalSessionFactoryBean sf;

    @Resource
    private DataSource dataSource;

    private Configuration cfg;
    private Connection conn; 

    @Resource
    private DaoHelp daoHelper; 

    @Before
    public void setup() throws Exception {
        if (null == sf) {
            throw new IllegalStateException(NO_LOCAL_SESSION_FACTORY_MSG);
        }
        if (null == cfg) {
            cfg = sf.getConfiguration();     
        }

        conn = dataSource.getConnection();
        SchemaExport exporter = new SchemaExport(cfg, conn);
        exporter.execute(true, true, false, true);

        if ( ! exporter.getExceptions().isEmpty() ) {
            throw new IllegalStateException(UNABLE_TO_SETUP_SCHEMA);
        }  
    } 

    @After
    public void teardown() throws Exception {
        //see http://hsqldb.org/doc/guide/running-chapt.html#rgc_closing_db
        if (null != conn) {
            conn.createStatement().execute(SHUTDOWN_TAG);
            conn.close();
            conn = null;
        }
    }


    public DaoHelp getDaoHelp() {
        return daoHelper;
    }

    public void setDaoHelp(DaoHelp daoHelp) {
        this.daoHelper = daoHelp;
    } 


}

