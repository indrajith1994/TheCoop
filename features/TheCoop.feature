Feature: The coop link with client_credentials OAUTH 2 grant type

  Scenario: Get the Access token
    Given make the token call
    When perform post token call
    Then validate call

  Scenario: chickens-feed call
    Given make the token call
    When perform chickens-feed call
    Then validate call

  Scenario: toiletseat-down call
    Given make the token call
    When perform toiletseat-down call
    Then validate call

  Scenario: barn-unlock call
    Given make the token call
    When perform barn-unlock call
    Then validate call

  Scenario: eggs-collect call
    Given make the token call
    When perform eggs-collect call
    Then validate call

  Scenario: eggs-count call
    Given make the token call
    When perform eggs-count call
    Then validate call