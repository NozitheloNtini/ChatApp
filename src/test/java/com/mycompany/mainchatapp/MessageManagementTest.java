/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainchatapp;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author missn
 */
public class MessageManagementTest {
    
     private MessageManagement manager; // field thats shared by all tests
     
     @BeforeEach
     public void setup() { //rests Messsages's counter so has generation starts from 0
        Message.resetForTests();
        manager = new MessageManagement();
        
        //test message 1 sent
        manager.registerAction(
                "Did you get the cake?", //message text
                "+27834557896",//recipient
                "11:0:DIDCAKE",//hash
                "1111111111",//id
                1);//menu choice
        
        //test message 2 stored
        manager.registerAction(
                "Where are you? You are late! I have asked you to be on time.",
                "+27838884567",
                "22:1:WHERETIME",
                "2222222222",
                3);
        
        //test message 3 disregarded
        manager.registerAction(
                "Yohoooo, I am at your gate.",
                "+27834484567",
                "33:2:YOHOGATE",
                "3333333333",
                2);
        
        //test message 4 sent to +27838884567
        manager.registerAction(
                "It is dinner time !",
                "+27838884567",
                "44:3:ITDINNER",
                "4444444444",
                1);
        
        //test message 5 stored to +27838884567
        manager.registerAction(
                        "Ok, I am leaving without you.",
                        "+27838884567",
                        "55:4:OKYOU",
                        "5555555555",
                        3);
    }
     
     
     //CORRECTLY POPULATED ARRAYS
     
     @Test
    public void testSentArrayPopulated() {
        String[] sent = manager.getSentMessages();

        assertEquals(2, sent.length,
                "Should be 2 sent messages, from setup() test data #1 and #4");

        assertEquals("Did you get the cake?", sent[0]);
        assertEquals("It is dinner time !",   sent[1]);
    }

      @Test
    public void testDisregardedArrayPopulated() {
        String[] ignored = manager.getDisregardedMessages();

        assertEquals(1, ignored.length,
                "Should be 1 disregarded message, from setup() test data #3");

        assertEquals("Yohoooo, I am at your gate.", ignored[0]);
    }  
        
      @Test
    public void testStoredArraysAreParallel() {
        // All four stored arrays must be the same length and index i in each 
        //must describe the same physical message
        String[] msgs = manager.getStoredMessages();
        String[] rcpts = manager.getStoredRecipients();
        String[] hashes = manager.getStoredHashes();
        String[] ids = manager.getStoredIDs();
        
        assertEquals(2, msgs.length,
                "Should be 2 stored messages, from setup() test data #2 and #5");
        assertEquals(msgs.length, rcpts.length,
                "storedRecipients must be parallel to storedMessages");
        assertEquals(msgs.length, hashes.length,
                "storedHashes must be parallel to storedMessages");
        assertEquals(msgs.length, ids.length,
                "storedIDs must be parallel to storedMessages");
        
        // Spot check that index 0 refers to the same message in all four
        assertEquals("2222222222", ids[0]);
        assertEquals("22:1:WHERETIME", hashes[0]);
        assertEquals("+27838884567", rcpts[0]);
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                msgs[0]);
    }
    
    //LONGEST MESSAGE
    @Test
    public void testLongestStoredMessage() {
        String expected =
                "Longest stored message:\n" +
                "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.displayLongestStoredMessage());
    }
    
    @Test
    public void testLongestWhenEmpty() {
        MessageManagement empty = new MessageManagement();
        assertEquals("No stored messages to display.",
                empty.displayLongestStoredMessage());
    }

    //SEARCH BY RECIPIENT
    @Test
    public void testSearchByRecipientLocalFormat() {
        //0838884567 is the local form of +27838884567
        String result = manager.searchByRecipient("0838884567");

        assertTrue(result.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Should match stored message #2");
        assertTrue(result.contains("It is dinner time !"),
                "Should match sent message #4");
        assertTrue(result.contains("Ok, I am leaving without you."),
                "Should match stored message #5");
    }
    
    @Test
    public void testSearchByRecipientInternationalFormat() {
        // Same search but international form so it should return identical output
        String local = manager.searchByRecipient("0838884567");
        String intl  = manager.searchByRecipient("+27838884567");
        assertEquals(local, intl,
                "Local and international formats must return the same results");
    }

    @Test
    public void testSearchByRecipientRejectsOtherNumbers() {
        // Negative case: searching +27838884567 must NOT return
        // messages that belong to other recipients.
        String result = manager.searchByRecipient("+27838884567");
        assertFalse(result.contains("Did you get the cake?"),
                "Must not match message #1 (+27834557896)");
        assertFalse(result.contains("Yohoooo, I am at your gate."),
                "Must not match message #3 (+27834484567)");
    }
    
    @Test
    public void testNormaliseNumberAllFormats() {
        assertEquals("27838884567",
                MessageManagement.normaliseNumber("+27838884567"));
        assertEquals("27838884567",
                MessageManagement.normaliseNumber("27838884567"));
        assertEquals("27838884567",
                MessageManagement.normaliseNumber("0838884567"));
        assertEquals("27838884567",
                MessageManagement.normaliseNumber("+27 83 888 4567"));
    }

    //SEARCH BY MESSAGE ID
    @Test
    public void testSearchByMessageIDFound() {
        String result = manager.searchByMessageID("2222222222");

        assertTrue(result.contains("+27838884567"),
                "Should report the recipient of stored message #2");
        assertTrue(result.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Should report the text of stored message #2");
    }

    @Test
    public void testSearchByMessageIDNotFound() {
        assertEquals("Message ID not found.",
                manager.searchByMessageID("9999999999"));
    }
    
    //DELETE BY HASH
    @Test
    public void testDeleteByHashRemovesFromAllArrays() {
        String result = manager.deleteByHash("22:1:WHERETIME");
        assertEquals(
                "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                result);

        // All four stored arrays must shrink together
        assertEquals(1, manager.getStoredMessages().length);
        assertEquals(1, manager.getStoredRecipients().length);
        assertEquals(1, manager.getStoredHashes().length);
        assertEquals(1, manager.getStoredIDs().length);

        // The remaining entry should still line up across all four arrays        assertEquals("5555555555",   manager.getStoredIDs()[0]);
        assertEquals("55:4:OKYOU",   manager.getStoredHashes()[0]);
        assertEquals("+27838884567", manager.getStoredRecipients()[0]);
    }

    @Test
    public void testDeleteByHashNotFound() {
        assertEquals("Message hash not found.",
                manager.deleteByHash("00:0:NOPE"));
        // Nothing was removed, so the arrays are unchanged
        assertEquals(2, manager.getStoredMessages().length);
    }

    //READ JSON INTO ARRAY
    @Test
    public void testLoadFromJsonHandlesMissingFile() {
        // On a fresh run there is no messages.json yet
        // The loader should return instead of crashing
        MessageManagement fresh = new MessageManagement();
        assertDoesNotThrow(() ->
                fresh.loadStoredMessagesFromJson("does-not-exist.json"));
        assertEquals(0, fresh.getStoredMessages().length);
    }

    @Test
    public void testLoadFromJsonFiltersByStatus() throws IOException {
        // Build a temporary JSON file with three messages:
        // one stored, one sent, one disregarded.
        // Only the stored one should be loaded.
        File tmp = File.createTempFile("messages-test-", ".json");
        tmp.deleteOnExit();
        try (FileWriter w = new FileWriter(tmp)) {
            w.write("[\n");
            w.write("{\"messageID\":\"AAAA111111\",\"messageNumber\":0," +
                    "\"recipient\":\"+27838884567\",\"message\":\"stored msg\"," +
                    "\"messageHash\":\"AA:0:STOREDMSG\",\"status\":\"Stored\"},\n");
            w.write("{\"messageID\":\"BBBB222222\",\"messageNumber\":1," +
                    "\"recipient\":\"+27834557896\",\"message\":\"sent msg\"," +
                    "\"messageHash\":\"BB:1:SENTMSG\",\"status\":\"Sent\"},\n");
            w.write("{\"messageID\":\"CCCC333333\",\"messageNumber\":2," +
                    "\"recipient\":\"+27834484567\",\"message\":\"ignored msg\"," +
                    "\"messageHash\":\"CC:2:IGNOREDMSG\",\"status\":\"Disregarded\"}\n");
            w.write("]\n");
        }

        MessageManagement loaded = new MessageManagement();
        loaded.loadStoredMessagesFromJson(tmp.getAbsolutePath());
        
        // Only the stored message was loaded.
        assertEquals(1, loaded.getStoredMessages().length,
                "Only the stored message should have been loaded");

        // All four arrays were filled together.
        assertEquals(1, loaded.getStoredRecipients().length);
        assertEquals(1, loaded.getStoredHashes().length);
        assertEquals(1, loaded.getStoredIDs().length);

        // The values match the stored message in the file.
        assertEquals("stored msg",      loaded.getStoredMessages()[0]);
        assertEquals("+27838884567",    loaded.getStoredRecipients()[0]);
        assertEquals("AA:0:STOREDMSG",  loaded.getStoredHashes()[0]);
        assertEquals("AAAA111111",      loaded.getStoredIDs()[0]);
    }
    
    //FULL REPORT SHOWING ALL STORED MESSAGES
    @Test
    public void testDisplayReportContainsAllFields() {
        String report = manager.displayReport();

        // Both stored messages should be in the report, each with
        // its hash, recipient and message text.
        assertTrue(report.contains("22:1:WHERETIME"));
        assertTrue(report.contains("55:4:OKYOU"));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains(
                "Where are you? You are late! I have asked you to be on time."));
        assertTrue(report.contains("Ok, I am leaving without you."));

        // Messages that were sent or disregarded should not appear.
        assertFalse(report.contains("Did you get the cake?"));
        assertFalse(report.contains("Yohoooo, I am at your gate."));
    }

    @Test
    public void testDisplayReportEmpty() {
        MessageManagement empty = new MessageManagement();
        assertEquals("No stored messages to display.",
                empty.displayReport());
    }
}
  