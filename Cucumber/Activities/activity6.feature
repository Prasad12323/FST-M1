@activity6
Feature: To test input with Datatables
  Scenario: Adding items to a to-do list
   Given user is on to-do list page 
   When user adds following task:
         | task1 |
         | task2 |
         | task3 |
   Then they can see task added to list     

  
  