package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.UserInfo");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'UserInfo'.");
        }

    }
}