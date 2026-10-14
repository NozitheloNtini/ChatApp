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
        this.messageHash = CreateMessageHash();
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
}
