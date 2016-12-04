/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.GameBean;
import fr.univ.iataaaserver.domain.game.Piece;
import fr.univ.iataaaserver.domain.game.Player;
import fr.univ.iataaaserver.domain.game.PlayerEnum;

/**
 *
 * @author anto
 */
public class GameManagerImpl implements GameManager {
    
   private final GameMap games;
   
   public GameManagerImpl() {
       games = new GameMapImpl();
   }

    @Override
    public boolean createGame(String id) {
        boolean contains = games.constains(id);
        if (!contains) {
            GameBean game = new GameBean();
            game.setId(id);
            game.setGame(new GameServiceImpl());
            games.addGame(game);
        }
        return !contains;
    }

    @Override
    public Board getGame(String id) {
        Board board = null;
        if (games.getGame(id) != null && games.getGame(id).getGame() != null) {
            board = games.getGame(id).getGame().getBoard();
        }
        return board;
    }

    @Override
    public boolean move(String id, String tokenPlayer, Piece[] pieces) {
        boolean res = false;
        GameService gameS = games.getGame(id).getGame();
        GameBean game = games.getGame(id);
        if (game.getPlayer1().getName().equals(tokenPlayer)) {
            gameS.move(pieces, PlayerEnum.PLAYER_1);
            res = true;
        } else if (game.getPlayer2().getName().equals(tokenPlayer)) {
            gameS.move(pieces, PlayerEnum.PLAYER_2);
            res = true;
        }
        return res;
    }

    @Override
    public boolean subsribePlayer(String id, Player p) {
        boolean res = false;
        GameBean game = games.getGame(id);
        if (game.getPlayer1() == null) {
            game.setPlayer1(p);
            res = true;
        } else if (game.getPlayer2() == null) {
            game.setPlayer2(p);
            res = true;
        }
        return res;
    }
    
}
