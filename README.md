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


# QuickChat — Part 3: Stored Messages and Reporting

## Description

Part 3 extends the QuickChat console application with a **Stored
Messages** feature. After logging in, the user can access a sub-menu
that lists, searches, deletes, and reports on messages that were
previously stored. Stored messages are read back from `messages.json`
at startup, so they persist across app restarts.

All input and output is handled through the console.

---

## Features

### 1. Fourth Main Menu Option

A fourth option is added to the main menu:

| Option | Description |
|--------|-------------|
| 1 | Send Messages |
| 2 | Show recently sent messages — displays `Coming Soon.` |
| 3 | Quit |
| **4** | **Stored Messages — opens the stored-messages sub-menu** |

### 2. Stored Messages Sub-Menu

The sub-menu loops until the user types `g` to return to the main
menu, so several features can be used in one visit.

| Key | Feature | Description |
|-----|---------|-------------|
| a | Display sender and recipient | Lists every stored message with sender, recipient, and message text |
| b | Display longest message | Finds the stored message with the most characters |
| c | Search by message ID | Returns the recipient and text for a stored ID |
| d | Search by recipient | Returns all messages (sent or stored) for a given number |
| e | Delete by hash | Removes a stored message by its hash from all four parallel arrays |
| f | Display full report | Prints ID, hash, recipient and message for every stored entry |
| g | Back to main menu | Closes the sub-menu |

### 3. Parallel Arrays

The brief requires messages to be held in **parallel arrays**, so
`MessageManagement` uses `String[]` for each attribute rather than a
`List<Message>`:

| Array | Contents |
|-------|----------|
| `sentMessages` | Message text of sent messages |
| `sentRecipients` | Recipient numbers of sent messages |
| `disregardedMessages` | Message text of disregarded messages |
| `storedMessages` | Message text of stored messages |
| `storedRecipients` | Recipient numbers of stored messages |
| `storedHashes` | Message hashes of stored messages |
| `storedIDs` | Message IDs of stored messages |

Each array is index-aligned: `storedMessages[i]`, `storedRecipients[i]`,
`storedHashes[i]` and `storedIDs[i]` all describe the same physical
message. Search and delete operations rely on this alignment.

### 4. South African Number Normalisation

The brief's Test 4 searches for `0838884567`, which is the **local**
form of `+27838884567`. `normaliseNumber()` converts both forms to the
same internal key:

| Input | Output |
|-------|--------|
| `+27838884567` | `27838884567` |
| `27838884567` | `27838884567` |
| `0838884567` | `27838884567` |
| `+27 83 888 4567` | `27838884567` |

Normalisation is only tolerated at **search time**. Registration and
message sending still require the strict `+27` format, so the send
path is unchanged from Part 2.

### 5. JSON Persistence

Stored messages are read back from `messages.json` at startup, so the
stored-messages view is populated before the user sends anything in
the current session. Only entries with `"status": "Stored"` are
loaded; sent and disregarded messages remain in the file but are
excluded from the stored view.

### 6. Registration and Login Loops

The registration and login flows from Parts 1 and 2 are wrapped in
loops so an invalid input re-prompts instead of exiting the app:

- **Username, password, phone:** loop until valid
- **Login:** up to 3 attempts, then exit with a message

---

## Class: `MessageManagement`

| Method | Return Type | Description |
|--------|-------------|-------------|
| `registerAction(String, String, String, String, int)` | `void` | Records a message into the correct parallel array based on the user's choice (1 = Send, 2 = Disregard, 3 = Store) |
| `displayStoredSenderRecipient()` | `String` | Returns sender, recipient and message text for every stored message |
| `displayLongestStoredMessage()` | `String` | Returns the stored message with the most characters |
| `searchByMessageID(String)` | `String` | Returns recipient and text for a stored message ID |
| `searchByRecipient(String)` | `String` | Returns every sent or stored message for the given number |
| `normaliseNumber(String)` | `String` (static) | Reduces a South African number to its canonical digits |
| `deleteByHash(String)` | `String` | Removes a stored message from all four parallel arrays |
| `displayReport()` | `String` | Returns ID, hash, recipient and message for every stored entry |
| `loadStoredMessagesFromJson(String)` | `void` | Reads `messages.json` into the stored parallel arrays |
| `getSentMessages()` / `getStoredMessages()` / etc. | `String[]` | Read-only access to the arrays, used by the unit tests |

### Helper Methods (private)

| Method | Description |
|--------|-------------|
| `append(String[], String)` | Returns a new array with the value appended |
| `removeAt(String[], int)` | Returns a new array with the index removed |
| `extractField(String, String)` | Pulls one quoted-string field out of a JSON object block |

---

## Changes to Existing Classes

### `Message`

| Change | Reason |
|--------|--------|
| Added `getRecipient()`, `getMessageText()`, `getMessageStatus()` | Needed by `MessageManagement` to read message fields without the fields being made public |
| Added `fromJson(...)` factory | Rebuilds a `Message` from persisted JSON without regenerating its ID or hash |
| `messageID`, `messageNumber`, `messageHash`, `messageStatus` changed to package-private | So `fromJson` can overwrite them with the values from disk |

### `MainChatApp`

| Change | Reason |
|--------|--------|
| `MessageManagement` instantiated and `loadStoredMessagesFromJson` called at startup | Stored messages available before the user sends anything |
| `manager.registerAction(...)` called after `sentMessage(...)` | Populates the parallel arrays as the user acts |
| Option 4 added to the main menu | Opens the stored-messages sub-menu |
| `handleStoredMessagesMenu()` added | Sub-menu dispatch for features a–g |
| Menu parse wrapped in try/catch | Non-numeric input reprints the menu instead of crashing |
| Registration loops added | Invalid input re-prompts instead of exiting |
| Login loop added (3 attempts) | Prevents brute force, forgives typos |
| `Message.returnTotalSent()` used for total count | Correct counter for sent-only messages |

---

## Unit Tests

`MessageManagementTest` has 17 tests, each named after the rubric row it
covers.

| Test | What it verifies |
|------|------------------|
| `testSentArrayPopulated` | Sent array contains the two sent messages from setup |
| `testDisregardedArrayPopulated` | Disregarded array contains the one disregarded message |
| `testStoredArraysAreParallel` | All four stored arrays are the same length and index-aligned |
| `testLongestStoredMessage` | Longest stored message is returned correctly |
| `testLongestWhenEmpty` | Empty manager returns the empty-state message |
| `testSearchByRecipientLocalFormat` | Local format `0838884567` finds the same messages as international |
| `testSearchByRecipientInternationalFormat` | Local and international formats return identical output |
| `testSearchByRecipientRejectsOtherNumbers` | Negative case: other recipients are not matched |
| `testNormaliseNumberAllFormats` | All four phone formats normalise to the same digits |
| `testSearchByMessageIDFound` | Search by stored message ID returns recipient and text |
| `testSearchByMessageIDNotFound` | Unknown ID returns "Message ID not found." |
| `testDeleteByHashRemovesFromAllArrays` | Delete removes the entry from all four stored arrays |
| `testDeleteByHashNotFound` | Unknown hash leaves the arrays unchanged |
| `testLoadFromJsonHandlesMissingFile` | Missing file handled silently |
| `testLoadFromJsonFiltersByStatus` | Only Stored entries load; Sent and Disregarded are skipped |
| `testDisplayReportContainsAllFields` | Report contains hash, recipient and message; sent/disregarded excluded |
| `testDisplayReportEmpty` | Empty manager returns the empty-state message |

### Test Data

The `setup()` method seeds the manager with the five test messages from
the Part 3 brief:

| # | Message | Recipient | Choice | Bucket |
|---|---------|-----------|--------|--------|
| 1 | Did you get the cake? | `+27834557896` | 1 (Send) | sent |
| 2 | Where are you? You are late! I have asked you to be on time. | `+27838884567` | 3 (Store) | stored |
| 3 | Yohoooo, I am at your gate. | `+27834484567` | 2 (Disregard) | disregarded |
| 4 | It is dinner time ! | `+27838884567` | 1 (Send) | sent |
| 5 | Ok, I am leaving without you. | `+27838884567` | 3 (Store) | stored |

---

## How to Run

1. Open the project in NetBeans.
2. Run `MainChatApp.java`.
3. Register with a valid username, password, and phone number. Bad
   inputs re-prompt instead of exiting.
4. Log in (up to 3 attempts).
5. Enter how many messages you want to send.
6. At the main menu:
   - Choose 1 to send messages
   - Choose 2 to see the coming-soon message
   - Choose 3 to quit
   - Choose 4 to open the Stored Messages sub-menu
7. In the Stored Messages sub-menu, type `a` through `f` for the
   features, or `g` to go back.
8. `messages.json` in the project folder holds the persisted messages.
   Stored entries reload automatically the next time the app starts.

---

## Automated Testing

The GitHub Actions workflow at `.github/workflows/testjava.yml` runs
`mvn test` on every push to `master`. The command is `mvn test` (not a
single-class command), so all three test classes run:

- `LoginClassTest`
- `MessageTest`
- `MessageManagementTest`

The workflow also uploads the surefire reports as an artifact, so any
failing run can be inspected from the Actions summary without
re-running locally.

---

## References

GeeksforGeeks. (2024). How to read a JSON file in Java. [Online].
Available at:
https://www.geeksforgeeks.org/how-to-read-and-write-json-files-in-java/
[Accessed 18 November 2026].

GeeksforGeeks. (2025). Validate Phone Numbers (with Country Code
extension) using Regular Expression. [Online]. Available at:
https://www.geeksforgeeks.org/dsa/validate-phone-numbers-with-country-code-extension-using-regular-expression/
[Accessed 13 September 2026].

LabEx. (n.d.). How to check if a string contains special characters in
Java. [Online]. Available at:
https://labex.io/tutorials/java-how-to-check-if-a-string-contains-special-characters-in-java-559981
[Accessed 13 September 2026].

Oracle. (2022). Java Platform, Standard Edition Java API Reference.
[Online]. Available at:
https://docs.oracle.com/javase/jp/18/docs/api/java.base/java/lang/String.html
[Accessed 14 October 2026].

Oracle. (2024). FileWriter (Java Platform SE 8). [Online]. Available at:
https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
[Accessed 15 October 2026].
