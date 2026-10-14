/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainchatapp;

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
    
    //creating a static field because they shared across all Message objects
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
    
}
