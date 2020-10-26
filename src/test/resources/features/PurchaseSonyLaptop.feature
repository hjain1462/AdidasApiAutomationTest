@ui
Feature: Purchase sony laptop

  Scenario: As a user, I want to purchase the sony vio i5 laptop
    Given user launches the browser
    Given user enters the url
    And navigate to Laptops
    And select sony laptop

    When add laptop into cart
    Then accept the alert
    And navigate on home page
    And navigate to Laptops

    When select dell laptop
    Then add laptop into cart
    And accept the alert

    When navigate on cart page
    Then remove dell laptop from cart

    When place order for sony laptop
    Then purchase sony laptop
    And get oder id and amount
    And validate both the amount should be same
    And click ok button
    And close the browser



