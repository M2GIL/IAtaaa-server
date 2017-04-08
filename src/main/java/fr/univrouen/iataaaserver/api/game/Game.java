package fr.univrouen.iataaaserver.api.game;

import fr.univrouen.iataaaserver.api.game.exception.ForbiddenMoveException;
import fr.univrouen.iataaaserver.api.game.util.Board;
import fr.univrouen.iataaaserver.api.game.util.Case;
import fr.univrouen.iataaaserver.api.game.util.EnumPlayer;
import fr.univrouen.iataaaserver.api.game.util.observable.Observable;

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
