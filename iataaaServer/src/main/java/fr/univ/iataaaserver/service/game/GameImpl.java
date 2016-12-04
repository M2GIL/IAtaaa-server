package fr.univ.iataaaserver.service.game;


import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.service.game.exception.ForbiddenMoveException;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.domain.game.util.observable.ObservableImpl;
import fr.univ.iataaaserver.domain.game.util.Rules;

public class GameImpl extends ObservableImpl implements Game {

    // CONSTANTS

    public final static String EVENT_BOARD_CHANGED = "BoardChanged"; // Attends un Piece[] en argument

    // ATTRIBUTES

    private Board<Case> pieces;
    private EnumPlayer currentPlayer;

    // CONSTRUCTOR

    public GameImpl() {
        this.currentPlayer = EnumPlayer.PLAYER_1;
        this.pieces = initializeGame();
        firePropertyChange(EVENT_BOARD_CHANGED, null, getPieces());
    }

    // REQUESTS

    public Board<Case> getPieces() {
        return pieces;
    }

    public EnumPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    // METHODS

    public void move(Board<Case> pieces) throws ForbiddenMoveException {

        if (!Rules.getAvalaibleMoves(getPieces(), currentPlayer).contains(pieces)) {
            throw new ForbiddenMoveException();
        }
        Board<Case> old = this.pieces;
        this.pieces = pieces;
        currentPlayer = EnumPlayer.getNextPlayer(getCurrentPlayer());
        firePropertyChange(EVENT_BOARD_CHANGED, old, getPieces());
    }

    // TOOLS

    private Board<Case> initializeGame() {
        Case[] pieces = new Case[50];
        for (int i = 0 ; i < 20; ++i) pieces[i] = Case.BLACK_PIECE;
        for (int i = 20; i < 30; ++i) pieces[i] = Case.EMPTY;
        for (int i = 30; i < 50; ++i) pieces[i] = Case.WHITE_PIECE;
        return new Board<>(pieces);
    }


}
