Feature: Login Data Driven

  @sanity
  Scenario Outline: Login Data Driven
    Given User Launch browser
    And opens URL "http://localhost/opencart/upload/"
    When User navigate to MyAccount menu
    And Click on Login
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login button
    Then User navigates to MyAccount Page

    Examples: 
      | email                   | password   |
      | abhipatil2630@gmail.com | Abhi@2630  |
      | patilabhi3026@gmail.com | Patil@3026 |
