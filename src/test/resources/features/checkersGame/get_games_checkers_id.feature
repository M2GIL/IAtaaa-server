Feature: Get checkers game

  Background:
    Given there are these checkersGames data in database:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | false |

  Scenario: I make call to GET right checkers game.
    When I set a "GET" request to "/api/games/checkers/0a"
    And I send the request
    Then the response status code is 200
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | false |
    And the response body matches :
      | id             | 0a                                                 |
      | name           | ia1                                                |
      | firstPlayerId  | ia1Id                                              |
      | secondPlayerId | ia2Id                                              |
      | board          | '111111111111111111100000000003333333333333333333' |
      | currentPlayer  | PLAYER_2                                           |
      | end            | false                                              |


  Scenario: I make call to GET non-existent checkers game.
    When I set a "GET" request to "/api/games/checkers/incorrectId"
    And I send the request
    Then the response status code is 404
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | false |