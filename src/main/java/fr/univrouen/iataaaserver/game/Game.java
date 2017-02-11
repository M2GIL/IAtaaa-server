package fr.univrouen.iataaaserver.game;

import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.dto.EnumPlayer;
import fr.univrouen.iataaaserver.domain.observable.Observable;
import fr.univrouen.iataaaserver.util.exception.ForbiddenMoveException;


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
