/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Piece;
import fr.univ.iataaaserver.domain.game.Player;

/**
 *
 * @author anto
 */
public interface GameManager {
    boolean createGame(String id);
    Board getGame(String id);
    boolean move(String ig, String tokenPlayer, Piece[] pieces);
    boolean subsribePlayer(String id, Player p);
}
