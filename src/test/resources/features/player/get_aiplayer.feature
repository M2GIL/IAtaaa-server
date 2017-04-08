Feature: Get All aiPlayers

  Background:
    Given there are these aiPlayers data in database:
      | id | difficulty | name | token | url            |
      | 0a | EASY       | ia1  | toto  | 127.0.0.1:8080 |
      | 1a | HARD       | ia2  | toto  | 127.0.0.1:8081 |

  Scenario: I make call to GET /aiPlayers without query parameters.
    When I set a "GET" request to "/api/aiPlayers"
    And I send the request
    Then the response status code is 200
    And the "Content-Type" attribute of the response header is "application/json;charset=UTF-8"
    And the response body matches :
      | content[0].id         | 0a             |
      | content[0].difficulty | EASY           |
      | content[0].name       | ia1            |
      | content[0].token      | toto           |
      | content[0].url        | 127.0.0.1:8080 |

      | content[1].id         | 1a             |
      | content[1].difficulty | HARD           |
      | content[1].name       | ia2            |
      | content[1].token      | toto           |
      | content[1].url        | 127.0.0.1:8081 |


      | last                  | true           |
      | totalElements         | 2              |
      | totalPages            | 1              |
      | first                 | true           |
      | numberOfElements      | 2              |
      | size                  | 10             |
      | number                | 0              |