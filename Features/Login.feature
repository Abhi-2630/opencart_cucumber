Feature: Login with Valid Credentials

@sanity
  Scenario: Successful Login with Valid Credentials
     Given User Launch browser
     And opens URL "http://localhost/opencart/upload/"
     When User navigate to MyAccount menu
     And Click on Login
     And User enters Email as "abhipatil2630@gmail.com" and Password as "Abhi@2630"
     And Click on Login button
     Then User navigates to MyAccount Page     