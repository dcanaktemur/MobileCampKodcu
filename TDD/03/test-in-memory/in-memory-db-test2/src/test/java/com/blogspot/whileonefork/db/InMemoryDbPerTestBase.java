package com.blogspot.whileonefork.db;

import org.junit.After;
import org.junit.Before;

/**
 * The database is reset after each test method. 
 */
public class InMemoryDbPerTestBase extends InMemoryDbBase {
    
    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @After
    public void teardown() throws Exception {
        super.teardown();
    }

}
