# QuickChat — Part 1: Registration and Login


---

## Description

QuickChat is a console based chat application built in Java. Part 1
implements the **registration and login feature**, allowing a user to:

- Register with a username, password, and South African cell phone number
- Have each field validated against specific formatting rules
- Log in using the credentials captured during registration

All input and output is handled through the console 

---

## Features

### 1. Registration

The user is prompted for a **first name**, **last name**, **username**,
**password**, and **cell phone number**. Each field is validated before
registration proceeds:

| Field | Rule | Success Message |
|-------|------|-----------------|
| Username | Contains an underscore `_` and is no more than 5 characters long | `Username successfully captured.` |
| Password | At least 8 characters, contains a capital letter, a number, and a special character | `Password successfully captured.` |
| Cell phone | Contains international code `+27` followed by 9 digits | `Cell phone number successfully added.` |

If any field fails validation, the system prints the corresponding error
message and exits the registration flow.

### 2. Login

The user is prompted to re-enter their username and password. The system
compares these against the stored credentials and responds:

| Condition | Message |
|-----------|---------|
| Credentials match | `Welcome <first name>, <last name> it is great to see you again.` |
| Credentials do not match | `Username or password incorrect, please try again.` |

A successful login grants access to the QuickChat main menu (implemented
in Part 2).

---

## Class: `LoginClass`

| Method | Return Type | Description |
|--------|-------------|-------------|
| `checkUserName(String)` | `boolean` | Validates the username contains `_` and is ≤ 5 characters |
| `checkPasswordComplexity(String)` | `boolean` | Validates length ≥ 8, capital letter, number, and special character |
| `checkPhoneNumber(String)` | `boolean` | Validates the `+27` international code and 9-digit number |
| `registerUser()` | `String` | Returns the appropriate registration message based on validation results |
| `loginUser(String, String)` | `boolean` | Compares entered credentials against stored credentials |
| `returnLoginStatus(boolean)` | `String` | Returns the welcome or failure message based on login outcome |

---

## Unit Tests

The `LoginClassTest` class uses JUnit to verify all validation and login
logic against the test data specified in the PoE brief.

| Test | Test Data | Expected Result |
|------|-----------|-----------------|
| Username valid | `kyl_1` | `true` |
| Username invalid | `kyle!!!!!!!` | `false` |
| Password valid | `Ch&&sec@ke99!` | `true` |
| Password invalid | `password` | `false` |
| Phone valid | `+27838968976` | `true` |
| Phone invalid | `08966553` | `false` |
| Login valid | `kyl_1` / `Ch&&sec@ke99!` | `true` |
| Login invalid | wrong credentials | `false` |

---
## References

GeeksforGeeks. (2025). Validate Phone Numbers (with Country Code
extension) using Regular Expression. [Online]. Available at:
https://www.geeksforgeeks.org/dsa/validate-phone-numbers-with-country-code-extension-using-regular-expression/
[Accessed 13 September 2026].

LabEx. (n.d.). How to check if a string contains special characters in
Java. [Online]. Available at:
https://labex.io/tutorials/java-how-to-check-if-a-string-contains-special-characters-in-java-559981
[Accessed 13 September 2026].


# QuickChat — Part 2: Messaging and QuickChat Menu

## Description

Part 2 extends the QuickChat console application with a messaging feature and a main menu. After logging in, the user can enter a chosen number of messages, each with a recipient and message body. Every message is validated, given a unique ID and hash, and the user chooses whether to **send**, **store**, or **disregard** it. Sent and stored messages are saved to a JSON file.

All input and output is handled through the console.

---

## Features

### 1. QuickChat Menu

After login, the user is asked how many messages they want to send. A menu loop runs until the user quits.

| Option | Description |
|--------|-------------|
| 1 | Send Messages — enter the chosen number of messages |
| 2 | Show recently sent messages — displays `Feature coming soon.` |
| 3 | Quit — exits the program |

### 2. Message Entry & Validation

For each message the user enters a **recipient** and **message text**.

| Field | Rule | Success Message |
|-------|------|-----------------|
| Recipient | Matches `+27` followed by 9 digits | `Cell phone number successfully captured.` |
| Message length | ≤ 250 characters | `Message ready to send.` |

If validation fails, the user retries the same message.

### 3. Send, Store, or Disregard

| Option | Action | Saved to JSON? |
|--------|--------|----------------|
| 1. Send | Status becomes `Sent` | ✅ Yes |
| 2. Store | Status becomes `Stored` | ✅ Yes |
| 3. Disregard | Asks for confirmation (`-1` to confirm), status becomes `Disregarded` | ❌ No |

### 4. Message Details

After creation, the message is displayed:

- Message ID: 7309258146
- Message Hash: 73:1:HITONIGHT
- Recipient: +27718693002
- Message: Hi Mike, can you join us for dinner tonight?

### 5. Total Messages Sent

After all messages are entered, the system displays the total number of messages **sent** (stored and disregarded do not count).

### 6. JSON Storage

Every sent or stored message is appended to `messages.json`:

{
  "messageID": "7309258146",
  "messageNumber": 0,
  "recipient": "+27718693002",
  "message": "Hi Mike, can you join us for dinner tonight?",
  "messageHash": "73:1:HITONIGHT",
  "status": "Sent"
}

---

## Classes

### Message

| Method | Return Type | Description |
|--------|-------------|-------------|
| Message(String, String) | — | Constructor: builds a message with unique ID and hash |
| checkMessageID() | boolean | ID must be ≤ 10 characters |
| checkRecipientCell() | String | Validates recipient format |
| checkMessageLength(String) | String | Validates message is ≤ 250 characters |
| createMessageHash() | String | Builds hash in ID:number:FIRSTLAST format |
| sentMessage(int, Scanner) | String | Handles send/store/disregard choice |
| storeMessage() | void | Appends message as JSON to messages.json |
| printMessages() | String | Returns formatted message details |
| returnTotalMessages() | int | Returns total messages sent |

### MainChatApp

Handles registration, login, the QuickChat menu, message entry, and total display.

---

## How to Run

1. Open the project in NetBeans.
2. Run MainChatApp.java.
3. Register and log in.
4. Enter how many messages you want to send.
5. Choose 1 to send messages, 2 for the coming-soon feature, 3 to quit.
6. Check messages.json in the project folder after sending or storing.

---

## References

GeeksforGeeks. (2025). Validate Phone Numbers (with Country Code extension) using Regular Expression. [Online]. Available at: https://www.geeksforgeeks.org/dsa/validate-phone-numbers-with-country-code-extension-using-regular-expression/ [Accessed 13 September 2026].

LabEx. (n.d.). How to check if a string contains special characters in Java. [Online]. Available at: https://labex.io/tutorials/java-how-to-check-if-a-string-contains-special-characters-in-java-559981 [Accessed 13 September 2026].

Oracle. (2022). Java Platform, Standard Edition Java API Reference. [Online]. Available at: https://docs.oracle.com/javase/jp/18/docs/api/java.base/java/lang/String.html [Accessed 14 October 2026].

Oracle. (2024). FileWriter (Java Platform SE 8). [Online]. Available at: https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html [Accessed 15 October 2026].
