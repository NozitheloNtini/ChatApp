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
       
       
    //creating instance of LoginClass
    
        System.out.println("----Welcome to QuickChat Registration.----");
        
        
        System.out.println("What is your first name?");
        String firstName = obj.nextLine();
        
        System.out.println("What is your last name?");
        String lastName = obj.nextLine();
        
        System.out.println("What is your first username?");
        String username = obj.nextLine();
        
        System.out.println("What is your password?");
        String password = obj.nextLine();
        
        System.out.println("What is your cell phone number?");
        String phoneNumber = obj.nextLine();
        
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
