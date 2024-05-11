Feature: Login Feature
  As a user i want to login
  with credential and navigate
  to Homepage

  Scenario: Login with valid credential
    Given I am on Saucedemo website
    When I enter a valid usernma "<username>"
    And I enter a valid password "<password>"
    And Click the "Login" button
    Then I should rederected to homepage
