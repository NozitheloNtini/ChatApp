/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author Student
 */
public class MessageTest {
    // Scanner for tests that call sentMessage
    Scanner sc = new Scanner(System.in);

    //runs before every test so the message counter starts at 0
    @BeforeEach
        public void reset() {
        Message.resetForTests();
    }
        
   //message length tests:
  
    // do under 250 char, exactly 250 char and over 250 char
    //short message
    @Test
    public void testMessageLengthValid() {
        String result = Message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    //message over 250 characrters
    @Test
    public void testMessageLengthTooLong() {
        // building a 260 character message by adding 'x' 260 times
        String longMessage = "";
        int count = 0;
        while (count < 260) {
            longMessage = longMessage + "x";
            count = count + 1;
        }

        String result = Message.checkMessageLength(longMessage);

        // 260 - 250 = 10 characters over the limit
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }

    
    
    //recipient tests:
    // valid RSA number with +27 and 9 digits 
    @Test 
    public void testRecipientValid() { 

        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?"); 
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell()); 

    } 
    
    //invalid number thats missing international code and a wrong length 
    @Test 
    public void testRecipientInvalid() { 

        Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?"); 

        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell()); 

    }
    
    //message hash tests:
    // full hash check for Test Case 1
    @Test
    public void testMessageHashFormat() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expected = msg.getMessageID().substring(0, 2) + ":0:HITONIGHT";
        assertEquals(expected, msg.getMessageHash());
    }

    // full hash check for Message 2
    @Test
    public void testMessageHashSecondMessage() {
        Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?");
        String expected = msg.getMessageID().substring(0, 2) + ":0:HIPAYMENT";
        assertEquals(expected, msg.getMessageHash());
    }

    // loop test: 3 messages, numbers 0, 1, 2
    @Test
    public void testMessageHashLoopIncrementsNumber() {
        String[][] cases = {
            { "Hi Mike, can you join us for dinner tonight?", "HITONIGHT"   },
            { "Hello world",                                  "HELLOWORLD"  },
            { "Testing the loop",                             "TESTINGLOOP" }
        };
        for (int i = 0; i < cases.length; i++) {
            Message m = new Message("+27718693002", cases[i][0]);
            String expected = m.getMessageID().substring(0, 2)
                            + ":" + i + ":" + cases[i][1];
            assertEquals(expected, m.getMessageHash());
        }
    }

    
    
    //messageID tests:
    
    //generated IDs are always 10 digits so this should be true 
    @Test 
    public void testCheckMessageID() { 
        Message msg = new Message("+27718693002", "Hello");
    assertEquals("Message ID generated: " + msg.getMessageID(), msg.checkMessageID());     
    } 
    
    //messageID as return string
    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message("+27718693002", "Hello");
        assertEquals("Message ID generated: " + msg.getMessageID(), msg.checkMessageID());
    }

    
    //total messages tests:
    //creating a Message should increment the shared static counter by 1 
    @Test 
    public void testReturnTotalMessagesIncrements() { 
        int before = Message.returnTotalMessages();
            new Message("+27718693002", "Test message");
            
            assertEquals(before + 1, Message.returnTotalMessages());

    }
    
    //sent messages tests:
    // option 1 should return "Message sent" 
    @Test 

    public void testSentMessageReturnsSent() { 
        Message msg = new Message("+27718693002", "Hi Mike"); 
        assertEquals("Message successfully sent.", msg.sentMessage(1, sc));
    }
    
    // option 2 should return discard
    @Test
    public void testSentMessageReturnsDisregard() {
        Message msg = new Message("+27718693002", "Hi Mike");
        Scanner fakeInput = new Scanner("-1"); //feeding test -1, this is to prevent the test from hanging
    assertEquals("Press 0 to delete the message.", msg.sentMessage(2, fakeInput));

    }

    //option 3 should be stored
    @Test
    public void testSentMessageReturnsStored() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Message successfully stored.", msg.sentMessage(3, sc));
    }

    //any other number is invalid
    @Test
    public void testSentMessageInvalidOption() {
        Message msg = new Message("+27718693002", "Hi Mike");
        assertEquals("Invalid Option.", msg.sentMessage(99, sc));
    }

  
    
}
