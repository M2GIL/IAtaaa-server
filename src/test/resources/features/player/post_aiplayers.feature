Feature: Add aiPlayers

  Scenario: I make call to POST /aiPlayers.
    When I set a "POST" request to "/api/aiPlayers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
       {
        "name":"ia1",
        "difficulty":"HARD",
        "url":"127.0.0.1:8080"
      }
    """
    And I send the request
    Then the response status code is 201
    And the "Content-Type" attribute of the response header is "application/json;charset=UTF-8"
    And the response body matches :
      | difficulty | HARD           |
      | name       | ia1            |
      | token      | toto           |
      | url        | 127.0.0.1:8080 |
    And the aiPlayers data database is:
      | difficulty | name | token | url            |
      | HARD       | ia1  | toto  | 127.0.0.1:8080 |


  Scenario: I make call to POST without name in json.
    When I set a "POST" request to "/api/aiPlayers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
      {
        "difficulty":"HARD",
        "url":"127.0.0.1:8080"
      }
    """
    And I send the request
    Then the response status code is 400
    And the aiPlayers data database is:
      | id | difficulty | name | token | url |


  Scenario: I make call to POST without difficulty in json.
    When I set a "POST" request to "/api/aiPlayers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
      {
        "name":"ia1",
        "url":"127.0.0.1:8080"
      }
    """
    And I send the request
    Then the response status code is 400
    And the aiPlayers data database is:
      | id | difficulty | name | token | url |

  Scenario: I make call to POST without url in json.
    When I set a "POST" request to "/api/aiPlayers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
      {
        "name":"ia1",
        "difficulty":"HARD"
      }
    """
    And I send the request
    Then the response status code is 400
    And the aiPlayers data database is:
      | id | difficulty | name | token | url |

