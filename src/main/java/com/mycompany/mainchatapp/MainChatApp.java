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
     
      /*instantiate the manager once per run. Loading thestored messages here
        means the "Stored Messages" menu has data available the moment the user reaches 
        the main menu, before they have sent anything in this session.*/
      
      MessageManagement manager = new MessageManagement();
      manager.loadStoredMessagesFromJson("messages.json");
     
    //Registration
    
        System.out.println("----Welcome to QuickChat Registration.----");
        
        
        System.out.println("What is your first name?");
        String firstName = obj.nextLine();
        
        System.out.println("What is your last name?");
        String lastName = obj.nextLine();
        
       
        //validate username (loops until correct)
        System.out.println("Choose a username (must contain '_', max 5 characters):");
        String username = obj.nextLine();

        while (!validation.checkUserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.");
            System.out.println("Choose a username (must contain '_', max 5 characters):");
            username = obj.nextLine();
        
        } System.out.println("Username successfully captured.");
          System.out.println();

          
        //validate password (loops until correct)
        System.out.println("Choose a password (min 8 chars, 1 capital, 1 number, 1 special char):");
        String password = obj.nextLine();

        while (!validation.checkPasswordComplexity(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number and a special character.");
            System.out.println("Choose a password (min 8 chars, 1 capital, 1 number, 1 special char):");
            password = obj.nextLine();
        
        } System.out.println("Password successfully captured.");
          System.out.println();

        //validate phone number (loops until correct)
        System.out.println("Enter cell number (with international code, e.g. +27838968976):");
        String phoneNumber = obj.nextLine();

        while (!validation.checkPhoneNumber(phoneNumber)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            System.out.println("Enter cell number (with international code, e.g. +27838968976):");
            phoneNumber = obj.nextLine();
        }System.out.println("Cell phone number successfully added."); 

     //calling registration method
        LoginClass login = new LoginClass(username, password, phoneNumber, firstName, lastName);
        System.out.println(login.registerUser());
   
     ///user login menu (loops until login succeeds or attempts run out)
        System.out.println("");
        System.out.println("---Login to continue---");

        int attempts = 0;
        while (!isLoggedIn && attempts < 3) {
            System.out.println("Enter your username:");
            String loginUsername = obj.nextLine();

            System.out.println("Enter your password:");
            String loginPassword = obj.nextLine();

            isLoggedIn = login.loginUser(loginUsername, loginPassword);

            if (!isLoggedIn) {
                attempts++;
                System.out.println("Login failed. Please try again.\n");
            }
        }

        //stop the app if login failed after max attempts
        if (!isLoggedIn) {
            System.out.println("Too many failed attempts. Exiting.");
            return;
        }

        System.out.println();
        System.out.println(login.returnLoginStatus(isLoggedIn)); //prints welcome message with their name and surname
        System.out.println();

        System.out.println("---Welcome to QuickChat!---"); //print only if logged in successfully
        
        //asking how many messages the user wants to enter
        System.out.println("How many messages do you want to send?");
        int numMessages = obj.nextInt();
         obj.nextLine();
         
         boolean isAppWorking = true;
         while (isAppWorking) {
             System.out.println("---QuickChat Menu---");
             System.out.println("1. Send messages");
             System.out.println("2. Show recently sent messages (Coming Soon.)");
             System.out.println("3. Quit");
             System.out.println("4. Stored Messages");
             System.out.println("");
             System.out.println("Choose an option:");
             
             //paise is inside a try so a non number input doesnt crash the whole app.
             //a failed parse just reprints the menu
             int option;
             try {
                 option = Integer.parseInt(obj.nextLine());
             } catch (NumberFormatException e) {
                 System.out.println("Invalid option. Please choose 1, 2, 3 or 4.");
                 continue;
             }
             
             
             if (option ==1) {
                 //the for loop will run numMessages times (if user chooses 3 for numMessages, it'll run 3 times)
                 for(int i=0; i< numMessages; i++){
                     System.out.println("\n --- Message " + (i+1) + " of " + numMessages) ; // tells the user which message they are on currently out of the number of messages they chose
                     
                     //asks for the recipient
                     System.out.println("Enter recipient cell number (e.g. +27718693002): ");
                     String recipient = obj.nextLine();
                     
                     //get message
                     System.out.println("Enter your message (max 250 characters): ");
                     String text = obj.nextLine();
                     
                     //checking message length first before creating message obj so it doesnt wste time building a message that will be ignored
                     String lengthCheck = Message.checkMessageLength(text);
                     if (!lengthCheck.equals("Message ready to send.")) { //if mesage is too long, it prints an error
                         System.out.println(lengthCheck);
                         continue; //skips to the next message for loop handles
                     
                     } //bulding the messsage
                            Message msg = new Message(recipient, text);
                            
                            if (!msg.checkRecipientCell().equals("Cell phone number successfully captured.")) { //checks recipient number format
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. "
                                        + "+ Please correct the number and try again."); //prints this if number is invalid
                                continue;
                            }
                                
                                //if everything is valid, it'll show the message details
                                System.out.println("Cell phone number successfully captured.");
                                
                                //asking for choice before showing details
                                System.out.println("Choose an option:");
                                System.out.println("1. Send message");
                                System.out.println("2. Disregard message");
                                System.out.println("3. Store message");
                                System.out.println("4. Stored Messages");
                                System.out.println("Your choice: ");
                                int sendChoice = Integer.parseInt(obj.nextLine());
                                
                                //details after the choice
                                System.out.println("\n--- Message Details ---");
                                System.out.println(msg.printMessages());
                                
                                if (sendChoice == 2) {
                                    msg.sentMessage(sendChoice, obj);   // calls only, doesnt print
                                } else {
                                    System.out.println(msg.sentMessage(sendChoice, obj));   // prints for options 1 and 3
                                }
                                /*registerAction() decides which slot based on sendChoice (1,2 or 3)
                                */
                                manager.registerAction(
                                        msg.getMessageText(),
                                        msg.getRecipient(),
                                        msg.getMessageHash(),
                                        msg.getMessageID(),
                                        sendChoice
                                );
  
                               }
        
                                  System.out.println("\n Total messages sent: " + Message.returnTotalSent() ); //after all message are entered, it;ll show the total created

                     } else if (option==2){
                     System.out.println("Coming Soon.");
                     
                   } else if (option ==3) {
                       System.out.println("Goodbye!");
                       isAppWorking = false; //sets the flag to false so the loop exits
                       
                    } else if (option == 4) {
                       handleStoredMessagesMenu(obj, manager);   
                       
                   }else {
                       System.out.println("Invalid option. Please choose 1, 2, 3 or 4.");
         }    
                 }   
            
                    
    }
    /*menu for option 4 (Stored Messages).
     
    Each letter maps to one of the six Part 3 features plus g
     to return to the main menu. The menu loops until g so the
     user can inspect several things without re navigating.
     */
    private static void handleStoredMessagesMenu(Scanner obj, MessageManagement manager) {
       boolean inMenu = true;
    while (inMenu) {
        System.out.println("\n--- Stored Messages Menu ---");
        System.out.println("a. Display sender and recipient of stored messages");
        System.out.println("b. Display the longest stored message");
        System.out.println("c. Search by message ID");
        System.out.println("d. Search all messages for a recipient");
        System.out.println("e. Delete a message by hash");
        System.out.println("f. Display full report");
        System.out.println("g. Back to main menu");
        System.out.print("Choose: ");
        
        String choice = obj.nextLine().trim().toLowerCase();

         if (choice.equals("a")) {
            System.out.println(manager.displayStoredSenderRecipient());
        } else if (choice.equals("b")) {
            System.out.println(manager.displayLongestStoredMessage());
        } else if (choice.equals("c")) {
            System.out.print("Enter message ID: ");
            System.out.println(manager.searchByMessageID(obj.nextLine().trim()));
        } else if (choice.equals("d")) {
            System.out.print("Enter recipient: ");
            System.out.println(manager.searchByRecipient(obj.nextLine().trim()));
        } else if (choice.equals("e")) {
            System.out.print("Enter message hash: ");
            System.out.println(manager.deleteByHash(obj.nextLine().trim()));
        } else if (choice.equals("f")) {
            System.out.println(manager.displayReport());
        } else if (choice.equals("g")) {
            inMenu = false;
        } else {
            System.out.println("Invalid choice. Please pick a-g.");
        }
        
    }
    }
}

