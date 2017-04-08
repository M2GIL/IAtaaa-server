Feature: Add checkersGame

  Scenario: I make call to POST /checkersGame.
    When I set a "POST" request to "/api/games/checkers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
       {
        "name":"ia1",
        "firstPlayerId":"ia1Id",
        "secondPlayerId":"ia2Id"
      }
    """
    And I send the request
    Then the response status code is 201
    And the "Content-Type" attribute of the response header is "application/json;charset=UTF-8"
    And the response body matches :
      | name           | ia1                                                |
      | firstPlayerId  | ia1Id                                              |
      | secondPlayerId | ia2Id                                              |
      | currentPlayer  | PLAYER_1                                           |
      | end            | false                                              |


  Scenario: I make call to POST without name in json.
    When I set a "POST" request to "/api/games/checkers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
       {
        "firstPlayerId":"ai1Id",
        "secondPlayerId":"ai2Id"
      }
    """
    And I send the request
    Then the response status code is 400
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              |

  Scenario: I make call to POST without firstPlayerId in json.
    When I set a "POST" request to "/api/games/checkers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
       {
        "name":"ia1",
        "secondPlayerId":"ai2Id"
      }
    """
    And I send the request
    Then the response status code is 400
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              |

  Scenario: I make call to POST without secondPlayerId in json.
    When I set a "POST" request to "/api/games/checkers"
    And the "Content-Type" attribute of the request header is "application/json"
    And the request body is :
    """
       {
       "name":"ia1",
       "firstPlayerId":"ai1Id"
      }
    """
    And I send the request
    Then the response status code is 400
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              |


