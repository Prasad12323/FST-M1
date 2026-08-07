@activity5
Feature: Login Test 
    Scenario Outline: Testing Login with Example
       Given the user is present on the login page
       When the user enters the "<Usernames>" and "<Passwords>"
       And clicks the login button
       Then get the confirmation text message and verify it as "<Messages>"
    Examples:
        | Usernames  | Passwords | Messages              |
        | admin1     | password  | Invalid credentials   |
        | admin2 	 | password2 | Invalid credentials   | 
        | admin  	 | password  | Welcome Back, Admin!  |      
       