package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.util.observable.ObservableImpl;
import fr.univrouen.iataaaserver.services.exception.ForbiddenMoveException;
import fr.univrouen.iataaaserver.services.util.Rules;



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

    @Override
    public Board<Case> getPieces() {
        return pieces;
    }

    @Override
    public EnumPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    // METHODS

    @Override
    public void move(Board<Case> pieces) throws ForbiddenMoveException {

        if (!Rules.getAvailableMoves(getPieces(), currentPlayer).contains(pieces)) {
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
