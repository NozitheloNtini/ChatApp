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
        
        //asking how many messages the user wants to enter
        System.out.println("How many messages do you want to send?");
        int numMessages = obj.nextInt();
         obj.nextLine();
         
         boolean isAppWorking = true;
         while (isAppWorking) {
             System.out.println("---QuickChat Menu---");
             System.out.println("1. Send messages");
             System.out.println("2. Show recently sent messages (coming soon)");
             System.out.println("3. Quit");
             System.out.println("");
             System.out.println("Choose an option:");
             
             int option = Integer.parseInt(obj.nextLine());
             
             if (option ==1) {
                 //the for loop will run numMessages times (if user chooses 3 for numMessages, it'll run 3 times)
                 int i=0;
                 while (i< numMessages){
                     System.out.println("\n --- Message " + (i+1) + " of " + numMessages) ; // tells the user which message they are on currently out of the number of messages they chose
                     
                     //asks for the recipient
                     System.out.println("Enter recipient cell number (e.g. +27718693002): ");
                     String recipient = obj.nextLine();
                     
                     //get message
                     System.out.println("Enter your message (max 250 characters): ");
                     String text = obj.nextLine();
                     
                     //checking message length
                     String lengthCheck = Message.checkMessageLength(text);
                     if (!lengthCheck.equals("Message ready to send.")) {
                         System.out.println(lengthCheck);
                     
                     }else{
                            Message msg = new Message(recipient, text);
                            
                            if (!msg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            } else {
                                System.out.println("Cell phone number successfully captured.");
                                
                                System.out.println("\n--- Message Details ---");
                                System.out.println(msg.printMessages());
                                
                                System.out.println("Choose an option:");
                                System.out.println("1. Send message");
                                System.out.println("2. Store message");
                                System.out.println("3. Disregard message");
                                System.out.println("Your choice: ");
                                int sendChoice = Integer.parseInt(obj.nextLine());
                                
                                System.out.println(msg.sentMessage(sendChoice));
                                
                                i= i+1; //increment only on full success
                            }
                     }
                 }   
            
                 System.out.println("\n Total messages sent: " + Message.returnTotalMessages() );
                   } else if (option==2){
                     System.out.println("Feature coming soon.");
                     
                   } else if (option ==3) {
                       System.out.println("Goodbye!");
                       isAppWorking = false;
                   }else {
                       System.out.println("Invalid option. Please choose 1, 2 or 3.");
                       
             
         }     
    }
    
    }
}

