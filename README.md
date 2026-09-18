# QuickChat — Part 1: Registration and Login

**Module:** PROG5121 Programming 1A
**Assessment:** PoE Part 1
**Student Name:** Nozithelo Ntini
**Student Number:** ST10519223

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
