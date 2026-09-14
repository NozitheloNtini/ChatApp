/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginClassTest {
  LoginClass obj = new LoginClass();
  
@Test
public void testCheckUserNameValid() {
    String username = "kyl_1";
    boolean expected = true;
    boolean results = obj.checkUserName(username);

 assertTrue(results);
}
 
@Test
public void testCheckUserNameInvalidNoUnderscore() {
    String username = "kyle!!!!!!!";
    boolean expected = false;
    boolean results = obj.checkUserName(username);
    
  assertFalse(results);
}

}
