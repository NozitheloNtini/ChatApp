/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainchatapp;
import java.util.Scanner;


/**
 *
 * @author Student
 */
public class MainChatApp {

    public static void main(String[] args) {
      Scanner obj = new Scanner(System.in);
      boolean isLoggedIn = false;
      LoginClass validation = new LoginClass("", "", "", "", ""); //used to validate methods 
       
    //creating instance of LoginClass
    
        System.out.println("----Welcome to QuickChat Registration.----");
        
        
        System.out.println("What is your first name?");
        String firstName = obj.nextLine();
        
        System.out.println("What is your last name?");
        String lastName = obj.nextLine();
        
        //validate username
        System.out.println("What is your username?");
        String username = obj.nextLine();
        
        if (!validation.checkUserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.");
        return; //exits program if username is wrong
        
        } System.out.println("Username successfully captured.");
        
        //validate password
        System.out.println("What is your password?");
        String password = obj.nextLine();
        
        if (!validation.checkPasswordComplexity(password)){
            System.out.println("Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number and a special character.");
        return;
        } System.out.println("Password successfully captured.");
        
        //validate phone number
        System.out.println("What is your cell phone number? (e.g, +27123456789");
        String phoneNumber = obj.nextLine();
        
        if (!validation.checkPhoneNumber(phoneNumber)){
            System.out.println("Cell phone number incorrectly formatted or does not contain international code."); 
        return;
        }System.out.println("Cell phone number successfully added.");
        
        
        LoginClass login = new LoginClass(username, password, phoneNumber, firstName, lastName);
        System.out.println(login.registerUser());

     //calling registration method
    
    login.loginUser(username, password);//calling login verification module
    
    if (isLoggedIn) {
        System.out.println("---Welcome to QuickChat!---");
        //this is where menu of options will be added later
    }
    
    
    }
}
