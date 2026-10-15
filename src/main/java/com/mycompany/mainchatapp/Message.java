/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainchatapp;
import java.util.Random;

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
    private static int totalMessages =0;
    
    //declaring arrays
    private static String[] sentMessages;
    private static String[] storedMessages;
    private static String[] disregardedMessages;
    private static String[] messageHashes;
    private static String[] messageIDs;
    
    //tracks how many of the slots are filled
    private static int sentMsgsCount = 0; // how many messages are sent for far?
    private static int storedMsgsCount = 0; //how many stored
    private static int disregardedMsgsCount = 0; // how many disregarded
    
    //setup arrays with the given size
    public static void setupArrays(int size) {
        sentMessages = new String[size];
        storedMessages = new String[size];
        disregardedMessages = new String[size];
        messageHashes = new String[size];
        messageIDs = new String[size];
    }
    
    //constructor to build a message and assign it a number
    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        totalMessages = totalMessages + 1; //increments the shared counter, then assigns
        this.messageNumber = totalMessages;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        this.messageStatus = ""; //has no status yet because the user hasnt chosen what to do with the message yet
    }
        
    //generating a random message ID
    private String generateMessageID(){
  
        Random rndm = new Random();
        String id = "";
            for (int i=0; i <10; i++){ //loops 10 times
                id = id + rndm.nextInt(10); // add a random number 0-9 at the end of the ID string
            
    } return id;
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
            
        } return "Message exceeds 250 characters by " + (msg.length() - 250) +"please reduce the size.";
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

public void storeMessage() {

        // build the JSON text as a single string
        String json = "{\n"
                + "  \"messageID\": \"" + messageID + "\",\n" // messageID field
                + "  \"messageNumber\": " + messageNumber + ",\n" // messageNumber field
                + "  \"recipient\": \"" + recipient + "\",\n" //recipient field
                + "  \"message\": \"" + messageText + "\",\n" // message text field
                + "  \"messageHash\": \"" + messageHash + "\",\n" // messageHash field
                + "  \"status\": \"" + messageStatus + "\"\n" //status field
                + "}\n";

        
        try (java.io.FileWriter writer = new java.io.FileWriter("messages.json", true)) {
            writer.write(json);
        } catch (java.io.IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

// handles the user's choice: send, store or disregard the message
public String sentMessage(int option) {
    //option 1 send message
    if (option == 1) {
        messageStatus = "Sent";
        sentMessages[sentMsgsCount]=messageText;
        messageHashes[sentMsgsCount]= messageHash;
        messageIDs[sentMsgsCount]= messageID;
        sentMsgsCount = sentMsgsCount +1; // moves the counter forward so the next send goes to the next slot
    return "Message sent";       
    }
    
    //option 2 store message and save to json
    if (option ==2) {
        messageStatus = "Stored";
        storedMessages[storedMsgsCount]=messageText;
        messageHashes[storedMsgsCount]= messageHash;
        messageIDs[storedMsgsCount]= messageID;
        storedMsgsCount = storedMsgsCount +1; 
        storeMessage();
    return "Message stored";       
    }
    
    //option 3 disregard the message
    if (option == 3) {
        messageStatus = "Disregarded";
        disregardedMessages[disregardedMsgsCount]= messageText;
        disregardedMsgsCount = disregardedMsgsCount +1;
    return "Message disregarded";       
}
     //any other option is invalid
    return "Invalid Option.";
}
}
