package com.blogspot.whileonefork.db;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;

import com.blogspot.whileonefork.config.ApplicationContext;

@ContextConfiguration(classes = {ApplicationContext.class})
public class InMemoryDbBase {

    //use the prefix "&" to get the actual factory, not the proxy
    @Resource(name="&entityManagerFactory")
    private LocalContainerEntityManagerFactoryBean emf;

    @Resource
    private DaoHelper daoHelper;
    
    @Resource 
    DataSourceInitializer dataSourceInitializer;
    
    public void resetDatabase() {
        dataSourceInitializer.destroy();
        dataSourceInitializer.afterPropertiesSet();
    }

    public void setup() throws Exception {
    }

    public void teardown() throws Exception {
    }

    public DaoHelper getDaoHelp() {
        return daoHelper;
    }

    public void setDaoHelp(DaoHelper daoHelp) {
        this.daoHelper = daoHelp;
    }

}
