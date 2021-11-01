@ui @guest
Feature: Book a table
  As a guest I want to reserve a table so that I don't come to the restaurant in vain.

  Background:
    Given The My Thai start page has been opened
    And the booking section has been opened

  Scenario: Booking without accepting terms not possible
    When I enter valid booking data
    And I do not accept the terms
    Then Booking a table is not possible

  Scenario Outline: Book a table for different parties - check limits and mostly chosen value
    When I enter valid booking information for a table for <number> persons
    And I accept the terms
    And I confirm the booking
    Then The table is successfully booked

    Examples:
      | number |
      |      1 |
      |      4 |
      |      8 |

  Scenario Outline: Different Error situations
    When I enter valid booking data
    And I accept the terms
    And I change <attribute> to <value>
    Then Booking a table is not possible

    Examples:
      | attribute | value     |
      | email     |           |
      | email     | test      |
      | email     | test@test |
      | name      |           |
      | persons   |           |
      | persons   |         0 |
      | persons   |         9 |
      | persons   |        -1 |
      | persons   | X         |

  Scenario: Special Error situation - known bug in application
    When I enter valid booking data
    And I accept the terms
    And I change persons to 2.3
    Then Booking a table is not possible