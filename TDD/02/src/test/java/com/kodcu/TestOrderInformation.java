package com.kodcu;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestOrderInformation {


    @Test
    public void testMessagesHeard() {

        //TODO 1 -  Person nesnesini oluştur.
        Person person = new Person();

        List<String> messages = person.getMessagesHeard();
        //TODO 2 - listeden donen String nesnelerini teyit et
        assertEquals("?", messages);

    }
}
