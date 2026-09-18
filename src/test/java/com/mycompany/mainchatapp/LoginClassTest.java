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
    public void testPasswordComplexityValid() {
        System.out.println("testPasswordComplexityValid");
        
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
    public void testPasswordComplexityInvalid() {
        System.out.println("testPasswordComplexityInvalid");
        
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
    public void testCellPhoneNumberValid() {
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
    public void testCellPhoneNumberInvalid() {
        System.out.println("checkCellPhoneNumberInvalid");
        
        LoginClass instance = new LoginClass("kyl_1", "Password_1", "08966553", "Kyle", "Smith");
        
        String phoneNumber = "08966553";
        boolean expResult = false;
        
        boolean result = instance.checkPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
    }
    
    //the tests below are supposed to use either assertTrue() or assertFalse()
    
    /**
     * Valid Test of registerUser method, of class LoginClass.
    */
   
    @Test
    public void testRegisterUserValid() {
        System.out.println("checkRegisterUserValid");
        
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        
        String result = instance.registerUser();
        String expResult = "User registered successfully.";
        
         assertTrue(expResult.equals(result));

    }
    
    /**
     * Invalid Username Test of registerUser method, of class LoginClass.
    */

    @Test
    public void testRegisterUserInvalidUsername() {
        System.out.println("checkRegisterUserInvalidUsername");
        
        LoginClass instance = new LoginClass("kyle!!!!!!!", "Password_1", "+27838968976", "Kyle", "Smith");
        
        String result = instance.registerUser();
        String expResult = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        
         assertTrue(expResult.equals(result));

    }
    
    /**
     * Invalid Password Test of registerUser method, of class LoginClass.
    */
    @Test
    public void testRegisterUserInvalidPassword() {
        System.out.println("checkRegisterUserInvalidPassword");
        
        LoginClass instance = new LoginClass("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        
        String result = instance.registerUser();
        String expResult = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        
         assertTrue(expResult.equals(result));

    }
    
    /**
     * Invalid Phone Number Test of registerUser method, of class LoginClass.
    */
    @Test
    public void testRegisterUserInvalidPhoneNumber() {
        System.out.println("checkRegisterUserInvalidPhoneNumber");
        
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        
        String result = instance.registerUser();
        String expResult = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

        
         assertTrue(expResult.equals(result));

    }

    /**
     * Valid Test of loginUser method, of class LoginClass.
     */
    @Test
    public void testLoginUserValid() {
        System.out.println("loginUserValid");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        
        boolean result = instance.loginUser(username, password);

        assertTrue(result);    
    }
    
    /**
     * Invalid Username Test of loginUser method, of class LoginClass.
     */
    @Test
    public void testLoginUserInvalidUsername() {
        System.out.println("loginUserInvalid");
        String username = "kyle!!!!!!!";
        String password = "Ch&&sec@ke99!";
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        
        boolean result = instance.loginUser(username, password);

        assertFalse(result);
    }

    /**
     * Invalid Password Test of loginUser method, of class LoginClass.
     */
    @Test
    public void testLoginUserInvalidPassword() {
        System.out.println("loginUserInvalidPassword");
        String username = "kyl_1";
        String password = "password";
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        
        boolean result = instance.loginUser(username, password);

        assertFalse(result);
    }
    
    /**
     * Valid Test of returnLoginStatus method, of class LoginClass.
     */
    @Test
    public void testReturnLoginStatusValid() {
       
        System.out.println("returnLoginStatusValid");
        boolean isLoggedIn = true;
        LoginClass instance = new LoginClass("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String expResult = "Welcome Kyle, Smith it is great to see you again.";        
        String result = instance.returnLoginStatus(isLoggedIn);
        assertTrue(expResult.equals(result));
    }
    
    /**
     * Invalid Test of returnLoginStatus method, of class LoginClass.
     */
    @Test
    public void testReturnLoginStatusInvalid() {
        
      
        System.out.println("returnLoginStatusInvalid");
        boolean isLoggedIn = false;
        LoginClass instance = new LoginClass("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(isLoggedIn);
        assertEquals(expResult, result);
    }
}
    
    
    
    
    