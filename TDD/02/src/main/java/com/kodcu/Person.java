package com.kodcu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokce1 on 26/01/16.
 */
public class Person {

    private List<String> messagesHeard;


    public List<String> getMessagesHeard(){
        messagesHeard= new ArrayList<String>();
        messagesHeard.add("?");
        return messagesHeard;
    };

}
