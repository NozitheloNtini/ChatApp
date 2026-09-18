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
       
    //Registration
    
        System.out.println("----Welcome to QuickChat Registration.----");
        
        
        System.out.println("What is your first name?");
        String firstName = obj.nextLine();
        
        System.out.println("What is your last name?");
        String lastName = obj.nextLine();
        
        //validate username
        System.out.println("Choose a username (must contain '_', max 5 characters):");
        String username = obj.nextLine();
        
        if (!validation.checkUserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.");
        return; //exits program if username is wrong
        
        } System.out.println("Username successfully captured.");
          System.out.println();

        //validate password
        System.out.println("Choose a password (min 8 chars, 1 capital, 1 number, 1 special char):");
        String password = obj.nextLine();
        
        if (!validation.checkPasswordComplexity(password)){
            System.out.println("Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number and a special character.");
        return;
        } System.out.println("Password successfully captured.");
          System.out.println();

        //validate phone number
        System.out.println("Enter cell number (with international code, e.g. +27838968976):");
        String phoneNumber = obj.nextLine();
        
        if (!validation.checkPhoneNumber(phoneNumber)){
            System.out.println("Cell phone number incorrectly formatted or does not contain international code."); 
        return;
        }System.out.println("Cell phone number successfully added."); 

     //calling registration method
        LoginClass login = new LoginClass(username, password, phoneNumber, firstName, lastName);
        System.out.println(login.registerUser());
   
     //user login menu
        System.out.println("");
        System.out.println("---Login to continue---");
        
        System.out.println("Enter your username:");
        String loginUsername =  obj.nextLine();
        
        System.out.println("Enter your password:");
        String loginPassword =  obj.nextLine();
    
        isLoggedIn = login.loginUser(loginUsername, loginPassword);
        
        System.out.println();
        System.out.println(login.returnLoginStatus(isLoggedIn)); //prints welcome message with their name and surname
        System.out.println();
        
    
    if (!isLoggedIn) {
        return; //stop the app if login failed
    }
        System.out.println("---Welcome to QuickChat!---"); //print only if logged in successfully
        
        //this is where menu of options will be added later
            
    }
    
    }
}
