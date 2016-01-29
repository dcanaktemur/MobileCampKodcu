package com.blogspot.whileonefork.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogspot.whileonefork.db.InMemoryDbBase;

/**
 * Save an item and list Person's with no criteria, twice. Should both see one
 * item because DB is scrubbed each time.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonListOfOneTest extends InMemoryDbBase {
    
    private static final int DB_INIT_SIZE = 1;
    
    private int startingSize;

    @Before
    public void resetDatabase(){
        super.resetDatabase();
    }

    @Test
    public void testDbInitSize() {
        startingSize = getDaoHelp().list(Person.class, null, 8).size();
        assertThat(startingSize, is(DB_INIT_SIZE));
        getDaoHelp().write(new Person());
        assertThat(getDaoHelp().list(Person.class, null, 8).size() - startingSize, is(1));
    }

    @Test
    public void testIfDbInitSizeStillTheSame() {
      int startingSize = getDaoHelp().list(Person.class, null, 8).size();
      assertThat(startingSize, is(DB_INIT_SIZE));
      getDaoHelp().write(new Person());
      assertThat(getDaoHelp().list(Person.class, null, 8).size() - startingSize, is(1));
    }
}
