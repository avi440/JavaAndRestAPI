Feature: API Testing with REST Assured without Configuration file

  Scenario: Verify the status code of the GET request
    Given the user API endpoint is "https://reqres.in/api/users?page=2"
    When user send a GET request to the endpoint
    Then user verify status code should be 200
    And user verify the page size 2

  Scenario: Verify the status code of the POST call request
    Given the user API endpoint is "https://reqres.in/api/users"
    When user send a POST request to the endpoint
      | name | Avinash  |
      | job  | somthing |
    Then user verify status code should be 201

  Scenario: Verify the status code of the PUT call request
    Given the user API endpoint is "https://reqres.in/api/users"
    When user send a PUT request to the endpoint
      | job  | nothing |
    Then user verify status code should be 200
    
     Scenario: Verify the status code of the Delete call request 
    Given the user API endpoint is "https://reqres.in/api/users"
    When user send a DELETE request to the endpoint
    Then user verify status code should be 204
   
    
