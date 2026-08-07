@activity4
Feature: Login Test 
    Scenario: Testing Login without Examples
       Given the user opens the login page
       When the user enters "admin" and "password"
       And clicks the form submit button
       Then get the confirmation text and verfiy message as "Welcome Back, Admin!"