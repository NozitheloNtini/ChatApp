/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainchatapp;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Message {
    //variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String messageStatus; //this will flags/alerts if message is sent, stored or disregarded
    
    //creating a static field because they shared across all Message objects. it tracks how many messages are created
    private static int totalMessagesCreated = 0;
    
    //constructor to build a message and assign it a number
    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = totalMessagesCreated;
        totalMessagesCreated++;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        this.messageStatus = ""; //has no status yet because the user hasnt chosen what to do with the message yet
    }
    
    
        
    //generating a random message ID thats 10 digits long
    private String generateMessageID(){
  
        Random rndm = new Random();
        StringBuilder sb = new StringBuilder();
            for (int i=0; i <10; i++){ //loops 10 times
                sb.append(rndm.nextInt(10));
   }
          return String.format("%s", sb.toString());
    }
    //validation methods:
    
    //method to ensure message id is not more than 10 characters
    public boolean checkMessageID() {
        if (messageID == null) {
            return false;
    }
    return messageID.length() <= 10;
}
    
    //method to check cell number
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    //method to ensure message lenth is not more than 250 characters
    public static String checkMessageLength(String msg){
        if (msg.length()<= 250) {
            return "Message ready to send.";
            
        } return "Message exceeds 250 characters by " + (msg.length() - 250) +"; please reduce the size.";
    }
    
    //creating a method hash (first 2 numbers of ID:message number:FIRSTWORD+LASTWORD)
    
    /*
    String manipulation: trim, split and replaceAll adapted from Oracle
    Source:
    Oracle. (2022). Java Platform, Standard Edition Java API Reference. [online] Available at: <https://docs.oracle.com/javase/jp/18/docs/api/java.base/java/lang/String.html> [Accessed 14 October 2026].
    */
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2); //first 2 indexes (0 and 1)

        String [] words = messageText.trim().split("\\s+"); // Trim leading/trailing whitespace, then split the message into words so consecutive spaces don't produce empty entries
        String firstWord = words[0].replaceAll("[^a-zA-Z]",""); //Takes the first word and remove every character that is NOT a letter (a-z, A-Z)
        String lastWord = words [words.length-1].replaceAll("[^a-zA-Z]", ""); // Take the last word and remove every character that is NOT a letter
                                                                               
    return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

//storing messeage to JSON file
/*File writer approach referenced from:
Oracle. (2024). FileWriter (Java Platform SE 8). [Online] Available at: <https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html> [Accessed 15 October 2026].
*/
    
public void storeMessage() { //storeMessage() as a string only array
        
    String newObject =
          "{\n"
        + "  \"messageID\": \"" + messageID + "\",\n"
        + "  \"messageNumber\": " + messageNumber + ",\n"
        + "  \"recipient\": \"" + recipient + "\",\n"
        + "  \"message\": \"" + messageText + "\",\n"
        + "  \"messageHash\": \"" + messageHash + "\",\n"
        + "  \"status\": \"" + messageStatus + "\"\n"
        + "}";

    // read file into string
    String oldContent = "";
    try (java.io.BufferedReader reader =
             new java.io.BufferedReader(new java.io.FileReader("messages.json"))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        oldContent = sb.toString().trim();
    } catch (java.io.IOException e) {
        oldContent = "";
    }

    // rewrap into array
    String finalJson;
    if (oldContent.isEmpty()) {
        finalJson = "[\n" + newObject + "\n]\n";
    } else {
        String inner = oldContent.substring(1, oldContent.length() - 1).trim();
        finalJson = "[\n" + inner + ",\n" + newObject + "\n]\n";
    }

    try (java.io.FileWriter writer =
             new java.io.FileWriter("messages.json", false)) {
        writer.write(finalJson);
    } catch (java.io.IOException e) {
        System.out.println("Error storing message: " + e.getMessage());
    }
        
    }

// handles the user's choice: send, store or disregard the message
public String sentMessage(int option, Scanner confirm) {
    //option 1 send message
    if (option == 1) {
        messageStatus = "Sent";
        storeMessage();
        totalMessagesCreated = totalMessagesCreated + 1;
    return "Message successfully sent.";       
    }
    
    // option 2 disregard(confirm with -1 to avoid mistaken deletes)
    if (option ==2) {
        System.out.println("Press 0 to delete the message.");
        // -1 is intentional. It needs a deliberate input
        System.out.println("Type -1 to confirm deletion:");
        int confirmChoice = Integer.parseInt(confirm.nextLine());
        
        if (confirmChoice == -1) {
            messageStatus = "Disregarded";
            return "Message disregarded.";
        } else {
            return "Disregard cancelled.";
        }
    }
     //option 3 store message
     if (option == 3) {
        messageStatus = "Stored";
        storeMessage();
        return "Message successfully stored.";
    }
    return "Invalid Option.";
}
    

//returns one message's full details
public String printMessages() {
    return "Message ID: " + messageID + "\n"
            + "Message Hash: " + messageHash + "\n"
            + "Recipient: " + recipient + "\n"
            + "Message: " + messageText + "\n"
            ;
}

public static int returnTotalMessages() {
    return totalMessagesCreated;
}

//getters to test can build full hash
public int getMessageNumber() { return messageNumber; }
public String getMessageHash() { return messageHash; }
public String getMessageID() { return messageID; }

}
