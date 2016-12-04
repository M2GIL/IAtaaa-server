/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Piece;
import fr.univ.iataaaserver.domain.game.PlayerEnum;

/**
 *
 * @author anto
 */
public interface GameService {
    int PIECE_SIZE = 50;
    int CASE_NB_OF_LINE = 5;
    int LINE_NB = 10;
    
    Board getBoard();
    
    PlayerEnum getCurrentPlayer();
    
    boolean move(Piece[] pieces, PlayerEnum player);
    
    boolean isInGame();
    
    PlayerEnum getWinner();
    
    PlayerEnum getLooser();
    
}
