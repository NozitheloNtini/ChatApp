/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainchatapp;

/**
 *
 * @author missn
 */
public class MessageManagement {
    //parallel arrays for the 3 message slots
    
    private String[] sentMessages = new String[0]; //using 0 as temporary placeholder for empty array slots
    private String[] sentRecipients = new String[0];

    private String[] disregardedMessages = new String[0];

    private String[] storedMessages = new String[0];
    private String[] storedRecipients = new String[0];
    private String[] storedHashes = new String[0];
    private String[] storedIDs = new String[0];   
    
  //array helpers
    //Java arrays are fixed length so we grow them by allocating a new array with one extra slot and copying the old contents across
    private static String[] append(String[] arr, String value) {
        String[] out = new String[arr.length + 1];
        System.arraycopy(arr, 0, out, 0, arr.length);
        out[arr.length] = value;
        return out;
    }
    
   /*Makes a new list just like the old one, but with one item taken out.
 Used by deleteByHash() so all the stored lists stay matched up
 when something is deleted
 */
    
    private static String[] removeAt(String[] arr, int index) {
        String[] out = new String[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i == index) continue;  // skip the removed element
            out[j++] = arr[i];
        }
        return out;
    }
    
  // recording actions
  /*Records a message into the correct bucket based on the user's choice.
Called once per message after Message.sentMessage() returns.
     
     Choice values mirror the Part 2 action menu:
     1 = Send goes into the sent array
     2 = Disregard goes into the disregarded array
     3 = Store goes into all four stored arrays
     
     Anything else (including a failed "Disregard" confirmation) is
     silently ignored, because the caller already knows the action did
     not happen, so there is nothing to record.  
    */
   public void registerAction(String messageText, String recipient,
                           String hash, String id, int choice) {
    if (choice == 1) {
        sentMessages   = append(sentMessages,   messageText);
        sentRecipients = append(sentRecipients, recipient);
    } else if (choice == 2) {
        disregardedMessages = append(disregardedMessages, messageText);
    } else if (choice == 3) {
        // All four arrays grow by exactly one, in the same order so index i in each always refers to the same message
        storedMessages = append(storedMessages,   messageText);
        storedRecipients = append(storedRecipients, recipient);
        storedHashes = append(storedHashes,     hash);
        storedIDs = append(storedIDs,        id);
    }
    // if choice is anything else, nothing happens
}
   
   //stored message
   
   //returns the stored message with the most characters
   public String displayLongestStoredMessage() {
        if (storedMessages.length == 0) {
            return "No stored messages to display.";
        }
    // Track the index of the longest message rather than the string so it copies the longest once at the end
    int longestIndex = 0;
        for (int i = 1; i < storedMessages.length; i++) {
            if (storedMessages[i].length() > storedMessages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return "Longest stored message:\n" + storedMessages[longestIndex];
   }
   
   //prints the sender and recipient of everystored message
   public String displayStoredSenderRecipient() {
        if (storedMessages.length == 0) {
            return "No stored messages to display.";
        }
        
   StringBuilder sb = new StringBuilder();
   sb.append("--- Stored Messages (Sender and Recipient) ---\n");     
   
   for (int i = 0; i < storedMessages.length; i++) {
       sb.append("Sender: Me  to  Recipient: ")
              .append(storedRecipients[i])
              .append("\n")
              .append("Message: ")
              .append(storedMessages[i])
              .append("\n\n");   // blank line between entries
        }
        return sb.toString();
   }
   
 /* Returns every message (sent or stored) addressed to the given recipient, 
   one per line, prefixed with [Sent] or [Stored]
   
   Both the search input and every stored recipient are passed
    through normaliseNumber() first, so "+27838884567",
    "27838884567" and "0838884567" all match the same person
   */
    public String searchByRecipient(String recipient) {
        String wanted = normaliseNumber(recipient);
        
        StringBuilder sb = new StringBuilder();
        
        // Sent messages: iterate sentMessages and sentRecipients together on the same index
        for (int i = 0; i < sentMessages.length; i++) {
                 if (normaliseNumber(sentRecipients[i]).equals(wanted)) {
                     sb.append("[Sent]   ").append(sentMessages[i]).append("\n");
                 }
             }
        
        //stored messages: iterate storedMessages and storedRecipients together on the same index
        for (int i = 0; i < storedMessages.length; i++) {
            if (normaliseNumber(storedRecipients[i]).equals(wanted)) {
                sb.append("[Stored] ").append(storedMessages[i]).append("\n");
            }
        }

        if (sb.length() == 0) {
            return "No messages for " + recipient;
        }
        // trim() drops the trailing newline from the last append()
        return sb.toString().trim();
    }
        /*
    The same physical number can be written three ways:
        +27838884567   (international with plus)
         27838884567   (international without plus)
         0838884567    (local replacing +27 with a leading 0)
      All three normalise to "27838884567"
    */
        public static String normaliseNumber(String n) {
        if (n == null) return "";
        String digits = n.replaceAll("[^0-9]", "");
        if (digits.startsWith("27")) return digits;
        if (digits.startsWith("0"))  return "27" + digits.substring(1);
        return digits;
    }     
    //deletes the stored message whose hash matches the given number    
    /*The four stored arrays are parallel, so removing an entry
     from one without removing the same index from the other three
     would shift every later entry and silently pair a message
     with the wrong recipient. removeAt() returns
     a fresh array minus one index, so we call it four times with
     the same index and reassign all four fields together
     
     Returns a confirmation string that includes the deleted text
     so the user can see what was removed. Returns a notfound
      message when the hash does not exist */
        
   public String deleteByHash(String hash) {
        for (int i = 0; i < storedHashes.length; i++) {
            if (storedHashes[i].equals(hash)) {
            //Grabs the text before removing it, so the confirmation can quote what was deleted
              String removedText = storedMessages[i];

              /*Removes index i from every parallel array. Do all four
                before touching any field, so a half deleted state
                cannot happen if one line throws*/
              
             String[] newMessages   = removeAt(storedMessages,   i);
                String[] newRecipients = removeAt(storedRecipients, i);
                String[] newHashes     = removeAt(storedHashes,     i);
                String[] newIDs        = removeAt(storedIDs,        i);

                // Commit the four new arrays together.
                storedMessages   = newMessages;
                storedRecipients = newRecipients;
                storedHashes     = newHashes;
                storedIDs        = newIDs;

                return "Message: \"" + removedText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    } 
            
    //Prints a full report of every stored message: hash, recipient and message text, in that order
   //Returns "No stored messages to display." when the array is empty, matching the other display methods.
   public String displayReport() {
        if (storedMessages.length == 0) {
            return "No stored messages to display.";
        }

            StringBuilder sb = new StringBuilder();
                    sb.append("-----STORED MESSAGE REPORT-----\n");
                    for (int i = 0; i < storedMessages.length; i++) {
 //all four arrays are read on the same index i, so the hash, recipient and message always belong together
                    
              sb.append("Message ID   : ").append(storedIDs[i]).append("\n")
              .append("Message Hash : ").append(storedHashes[i]).append("\n")
              .append("Recipient    : ").append(storedRecipients[i]).append("\n")
              .append("Message      : ").append(storedMessages[i]).append("\n")
              .append("-------------------------------------------\n");
        }
        return sb.toString();
                    }

  //loading stored messages from json
  /*Reads messages.json and repopulates the four stored parallel
arrays with every entry whose status is "Stored".
     
  The file contains all messages the app has ever written
   (Sent, Stored, and Disregarded), so the status field is the
    filter that decides what belongs in the stored messages view.
     
    Called once from MainChatApp at startup, before the user can
    send anything. Appending to the arrays is safe because they
    start empty. 
     
Parsing approach adapted from: 
   GeeksforGeeks. (2024). How to read a JSON file in Java. [online]. Available at: <https://www.geeksforgeeks.org/how-to-read-and-write-json-files-in-java/> [Accessed 18 November 2026].
   */
   public void loadStoredMessagesFromJson(String path) {
       //reads the whole file into one string
       StringBuilder raw = new StringBuilder();
        try (java.io.BufferedReader br =
                 new java.io.BufferedReader(new java.io.FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                raw.append(line).append("\n");
            }
        } catch (java.io.IOException e) {
            return;
        }
        
    String[] objects = raw.toString().split("\\}");
         for (String obj:objects) {
            String id = extractField(obj, "messageID");
            String recipient = extractField(obj, "recipient");
            String message = extractField(obj, "message");
            String hash = extractField(obj, "messageHash");
            String status = extractField(obj, "status");
            
            //skip anything that has trailing brackets or blank lines
            if (id == null || message == null) continue;
            
            //only entries that are stored can go into the stored messages view
            if (status == null || !status.equalsIgnoreCase("Stored")) continue;
            
            //appends the four fields together
            storedMessages   = append(storedMessages,   message);
            storedRecipients = append(storedRecipients, recipient == null ? "" : recipient);
            storedHashes     = append(storedHashes,     hash == null ? "" : hash);
            storedIDs        = append(storedIDs,        id);
         }
   }
   
         
    /*Returns the quoted string value of a JSON field from a raw object
  block or null if the field is absent.
   
  Looks for the pattern key and reads until the next quote.
  This is deliberately minimal: it handles only the flat
  string value objects written by Message.storeMessage(), which
  is the only shape this app produces.
    
  Approach adapted from the same GeeksforGeeks article cited on
  loadStoredMessagesFromJson(): field extraction via indexOf is
  the technique the article demonstrates.
     */
    private static String extractField(String obj, String key) {
        String needle = "\"" + key + "\":";
        int keyStart = obj.indexOf(needle);
        if (keyStart < 0) return null;
        
        int valueStart = obj.indexOf('"', keyStart + needle.length());
        if (valueStart < 0) return null;
        
        int valueEnd = obj.indexOf('"', valueStart + 1);
        if (valueEnd < 0) return null;
        
        return obj.substring(valueStart + 1, valueEnd);
    }
   
//getters for the arrays
   public String[] getSentMessages(){ return sentMessages;}
    public String[] getSentRecipients(){ return sentRecipients;}
    public String[] getDisregardedMessages(){ return disregardedMessages;}
    public String[] getStoredMessages(){ return storedMessages;}
    public String[] getStoredRecipients(){ return storedRecipients;}
    public String[] getStoredHashes(){ return storedHashes;}
    public String[] getStoredIDs(){ return storedIDs;}

 }
