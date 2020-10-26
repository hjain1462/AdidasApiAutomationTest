@api
Feature: Demo Pet Store api test

  Scenario: As a client, I want to get the available pets
    Given query params are:
      | status | available |
    When get all the available pets
    Then check for available pets

    When add a new pet to store with template 'AddPetToStore.json'
      | PET_NAME | Lion |
    Then status code should be 200
    And new pat is added to store

    When update status of newly added pet to sold with template 'UpdatePetStatus.json'
      | STATUS | sold |
    Then status code should be 200
    And now the status is sold of newly added pet

    When delete the newly added pet
    Then status code should be 200
    And pet is removed from the store