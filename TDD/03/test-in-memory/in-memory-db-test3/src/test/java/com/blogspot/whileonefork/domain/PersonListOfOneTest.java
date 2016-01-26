package com.blogspot.whileonefork.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogspot.whileonefork.db.InMemoryDbBase;
import com.blogspot.whileonefork.db.InMemoryDbPerTestBase;

/**
 * Save an item and list Person's with no criteria, twice. Should both see one
 * item because DB is scrubbed each time.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonListOfOneTest extends InMemoryDbPerTestBase {
    
    private int startingSize;

    @Test
    public void list1() {
        startingSize = getDaoHelp().list(Person.class, null, 8).size();
        getDaoHelp().write(new Person());
        assertEquals(1, getDaoHelp().list(Person.class, null, 8).size() - startingSize);
    }

    @Test
    public void list2() {
      int startingSize2 = getDaoHelp().list(Person.class, null, 8).size();
      assertEquals(startingSize, startingSize2);
      getDaoHelp().write(new Person());
      assertEquals(1, getDaoHelp().list(Person.class, null, 8).size() - startingSize2);
    }
}
