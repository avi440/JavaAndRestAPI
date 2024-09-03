Feature: API Testing with REST Assured Configuration file

  Scenario: Verify the status code of the GET request with Configuration
    Given user hit the Base URL
    When user send a Get request with endpoint
    Then user verify status code was 200
    And user verify the response values for Get calling
      | id         | 1                                       |
      | email      | george.bluth@reqres.in                  |
      | first_name | George                                  |
      | last_name  | Bluth                                   |
      | avatar     | https://reqres.in/img/faces/1-image.jpg |
    And verify the Schema for request in "GetSchemaRequest"

  Scenario: Verify the status code of the POST calling request
    Given user hit the Base URL
    When user send a POST request with endpoint
      | name | Avinashh  |
      | job  | somthingg |
    Then user verify status code was 201
    And user verify the response values for Post calling
    And verify the Schema for request in "PostSchemaRequest"

  Scenario: Verify the status code of the PUT call request
    Given user hit the Base URL
    When user send a PUT request with endpoint
      | job | nothing |
    Then user verify status code was 200
    And user verify the response values for Put calling
    And verify the Schema for request in "PutSchemaRequest"

  Scenario: Verify the status code of the Delete call request
    Given user hit the Base URL
    When user send a DELETE request with endpoint
    Then user verify status code was 204

  Scenario: Verify the status code of the get call request of schema validation with queryParams
    Given user hit the Base URL
    When user send a Get request with endpoint with queryParams
      | page | 2 |
    Then user verify status code was 200
    And verify the Schema for request in "GetSchemaRequestqueryParams"
