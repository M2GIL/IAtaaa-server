Feature: Remove checkers Game

  Background:
    Given there are these checkersGames data in database:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | true  |

  Scenario: I make call to DELETE right entity.
    When I set a "DELETE" request to "/api/games/checkers/0a"
    And I send the request
    Then the response status code is 204
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |

  Scenario: I make call to DELETE non-existent entity.
    When I set a "DELETE" request to "/api/games/checkers/incorrectId"
    And I send the request
    Then the response status code is 404
    And the checkersGames data database is:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | true  |