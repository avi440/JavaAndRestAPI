Feature: API Testing with REST Assured Configuration file

  Scenario: Verify the status code of the GET request with Configuration
    Given user hit the Base URL
    When user send a Get request with endpoint
    Then user verify status code was 200
    And user verify the response values form list users from Json file

  Scenario: Verify the status code of the POST calling request
    Given user hit the Base URL
    When user send a POST request with endpoint from Json file
    Then user verify status code was 201
    #Coding is right but Api is not working . use same process in real time it'll work
    And user verify the response values for Post calling from Json file