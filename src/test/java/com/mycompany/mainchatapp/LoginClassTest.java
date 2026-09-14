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

    /**
     * Valid Test of checkUserName method, of class LoginClass.
     */
    @Test
    public void testCheckUserNameValid() {
        System.out.println("checkUserNameValid");
        
        LoginClass instance = new LoginClass("kyl_1", "Password_1", "+27123456789", "Kyle", "Smith");
        
        String username = "kyl_1";
        boolean expResult = true;
        
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Invalid Test of checkUserName method, of class LoginClass.
     */
    @Test
    public void testCheckUserNameInvalid() {
        System.out.println("checkUserNameInvalid");
        
        LoginClass instance = new LoginClass("kyle!!!!!!!", "Password_1", "+27123456789", "Kyle", "Smith");
        
        String username = "kyle!!!!!!!";
        boolean expResult = false;
        
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Valid Test of checkPasswordComplexity method, of class LoginClass.
     */
    @Test
    public void checkPasswordComplexityValid() {
        System.out.println("checkPasswordComplexityValid");
        
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27123456789", "Kyle", "Smith");
        
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Invalid Test of checkPasswordComplexity method, of class LoginClass.
     */
    @Test
    public void checkPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexityInvalid");
        
        LoginClass instance = new LoginClass("kyl_1", "password", "+27123456789", "Kyle", "Smith");
        
        String password = "password";
        boolean expResult = false;
        
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }
    
    /**
     * Valid Test of checkCellPhoneNumber method, of class LoginClass.
     */
    @Test
    public void checkCellPhoneNumberValid() {
        System.out.println("checkCellPhoneNumberValid");
        
        LoginClass instance = new LoginClass("kyl_1", "Password_1", "+27838968976", "Kyle", "Smith");
        
        String phoneNumber = "+27838968976";
        boolean expResult = true;
        
        boolean result = instance.checkPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
    }

    /**
     * Invalid Test of checkPhoneNumber method, of class LoginClass.
     */
    @Test
    public void checkCellPhoneNumberInvalid() {
        System.out.println("checkCellPhoneNumberInvalid");
        
        LoginClass instance = new LoginClass("kyl_1", "Password_1", "08966553", "Kyle", "Smith");
        
        String phoneNumber = "08966553";
        boolean expResult = false;
        
        boolean result = instance.checkPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
    }

}