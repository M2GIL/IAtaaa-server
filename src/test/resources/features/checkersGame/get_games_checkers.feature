Feature: Get All checkers games

  Background:
    Given there are these checkersGames data in database:
      | id | name | firstPlayerId | secondPlayerId | board                                              | currentPlayer | isEnd |
      | 0a | ia1  | ia1Id         | ia2Id          | '111111111111111111100000000003333333333333333333' | PLAYER_2      | false |

  Scenario: I make call to GET /games/checkers without query parameters.
    When I set a "GET" request to "/api/games/checkers"
    And I send the request
    Then the response status code is 200
    And the "Content-Type" attribute of the response header is "application/json;charset=UTF-8"
    And the response body matches :
      | content[0].id             | 0a                                                 |
      | content[0].name           | ia1                                                |
      | content[0].firstPlayerId  | ia1Id                                              |
      | content[0].secondPlayerId | ia2Id                                              |
      | content[0].board          | '111111111111111111100000000003333333333333333333' |
      | content[0].currentPlayer  | PLAYER_2                                           |
      | content[0].end            | false                                              |

      | last                      | true                                               |
      | totalElements             | 1                                                  |
      | totalPages                | 1                                                  |
      | first                     | true                                               |
      | numberOfElements          | 1                                                  |
      | size                      | 10                                                 |
      | number                    | 0                                                  |