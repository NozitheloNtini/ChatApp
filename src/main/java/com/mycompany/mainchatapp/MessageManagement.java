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
    
    private String[] sentMessages        = new String[0]; //using 0 as temporary placeholder for empty array slots
    private String[] sentRecipients      = new String[0];

    private String[] disregardedMessages = new String[0];

    private String[] storedMessages      = new String[0];
    private String[] storedRecipients    = new String[0];
    private String[] storedHashes        = new String[0];
    private String[] storedIDs           = new String[0];   
    
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
        storedMessages   = append(storedMessages,   messageText);
        storedRecipients = append(storedRecipients, recipient);
        storedHashes     = append(storedHashes,     hash);
        storedIDs        = append(storedIDs,        id);
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
   //getters for the arrays
   public String[] getSentMessages()        { return sentMessages;        }
    public String[] getSentRecipients()      { return sentRecipients;      }
    public String[] getDisregardedMessages() { return disregardedMessages; }
    public String[] getStoredMessages()      { return storedMessages;      }
    public String[] getStoredRecipients()    { return storedRecipients;    }
    public String[] getStoredHashes()        { return storedHashes;        }
    public String[] getStoredIDs()           { return storedIDs;           }

   }
