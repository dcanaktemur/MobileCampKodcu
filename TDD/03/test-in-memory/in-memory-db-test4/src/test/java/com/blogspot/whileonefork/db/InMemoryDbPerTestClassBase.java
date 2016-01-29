package com.blogspot.whileonefork.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * The database is reset only after all the tests. 
 */
public class InMemoryDbPerTestClassBase extends InMemoryDbBase {
    
    @BeforeClass
    public void setup() throws Exception {
        super.setup();
    }

    @AfterClass
    public void teardown() throws Exception {
        super.teardown();
    }

}
