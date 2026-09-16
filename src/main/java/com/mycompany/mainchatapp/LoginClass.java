/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainchatapp;

/**
 *
 * @author Student
 */
public class LoginClass {
//variables to store user details
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;    

  
 
//creating a constructor with parameters
public LoginClass(String username, String password, String phoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

/*method to check username:
    username must contain an underscore and must not be more than 5 characters long
*/
    public boolean checkUserName(String username){
        if(username == null){ //rejects null username
            return false;
        }
      return username.contains("_")  && username.length() <=5; //must contain an underscore and be at most 5 characters long
    }

/*method to check password complexity rules with regex:
    password should be at least 8 char long, have a capital letter, a number and a special character
----------
Validates a special character
Regex adapted from:
How to check if a string contains special characters in Java

LabEx. (n.d.). How to check if a string contains special characters in Java. LabEx. [Online]. 
Available at: <https://labex.io/tutorials/java-how-to-check-if-a-string-contains-special-characters-in-java-559981> [Accessed 13 September 2026].
*/    
    
    public boolean checkPasswordComplexity (String password) {
//rejects null password
        if (password == null) {
            return false;
        }

//rejects password shorter than 8 characters
       if (password.length() < 8 ) {
            return false;
        }
       
       //use regex to ensure capital letter, number and special character
       
       boolean hasCapital = password.matches(".*[A-Z].*"); // password has at least one capital letter
       boolean hasNumber = password.matches(".*[0-9].*"); // password contains at least one number
       boolean hasSpecialChar = password.matches (".*[^a-zA-Z0-9\\s].*"); // password contains at least one special character (not a letter, digit or whitespace)
       
       //combining all checks
       return hasCapital && hasNumber && hasSpecialChar;
    }

/*method to check phone number with regex:
   it should have the international country code and number
 ----------------------
  Regex pattern adapted from a GeeksforGeeks tutorial on international
  phone number validation. The original pattern was simplified to accept only the South African international code (+27) followed by 9 or 10 digits
 
 Source:
 GeeksforGeeks. (2025). Validate Phone Numbers (with Country Code extension) using Regular Expression. [Online]. Available at:
  <https://www.geeksforgeeks.org/dsa/validate-phone-numbers-with-country-code-extension-using-regular-expression/>[Accessed 13 September 2026].
 
 */

    public boolean checkPhoneNumber(String phoneNumber) {
        
       return phoneNumber != null && phoneNumber.matches("^\\+27\\d{9}$") ; //this ensures it has RSA's +27 code and 9 numbers that follow
    }
            
            
/*method to register user:
    must return a message if:
    -The username is incorrectly formatted
    -The password does not meet the complexity requirements
    -The two above conditions have been met, and the user has been registered successfully. 
*/
    public String registerUser() {
      boolean isUsernameValid = checkUserName(username);
      boolean isPasswordValid = checkPasswordComplexity(password);
      boolean isCellPhoneValid = checkPhoneNumber(phoneNumber);
      
      if (isUsernameValid == false) {
          return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";     
      }
      
      if (isPasswordValid == false) {
          return "Password is not correctly formatted. Please ensure that the password contains at least eight characters, a capital letter, a number and a special character";
      }
      
      if (isCellPhoneValid == false) {
          return "Cell phone number incorrectly formatted or does not contain international code";   
      }
      
      return "User registered successfully";
    }
    
/*method to verify login:
   verifies that the login details entered match the login details stored when the user registers
*/
   public boolean  loginUser(String username, String password) {
       if (username == null) {
        return false;
    }
       if (password == null) {
        return false;
    }
       
       return username.equals(this.username) && password.equals(this.password);   
   }
   
/* method to display message for successful/failed login:
*/
   public String returnLoginStatus(boolean isLoggedIn) {
       if(isLoggedIn) {
return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";       } else {
            return "Username or password incorrect, please try again";   
               }    
       }
   }

