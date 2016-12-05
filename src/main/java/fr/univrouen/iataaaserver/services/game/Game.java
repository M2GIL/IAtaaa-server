/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.util.observable.Observable;
import fr.univrouen.iataaaserver.services.exception.ForbiddenMoveException;

/**
 *
 * @author anto
 */
public interface Game extends Observable {

    /**
     * The size of the board.
     */
    int PIECE_SIZE = 50;
    int CASE_NB_OF_LINE = 5;
    int LINE_NB = 10;
    String EVENT_BOARD_CHANGED = "BoardChanged"; // Attends un Piece[] en argument

    Board<Case> getPieces();
    EnumPlayer getCurrentPlayer();
    void move(Board<Case> pieces) throws ForbiddenMoveException;
}
