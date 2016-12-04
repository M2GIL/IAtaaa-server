package fr.univ.iataaaserver.service.gamePlatform;


import fr.univ.iataaaserver.service.gamePlatform.exception.ForbiddenMoveException;
import fr.univ.iataaaserver.service.gamePlatform.util.Case;
import fr.univ.iataaaserver.service.gamePlatform.util.EnumPlayer;
import fr.univ.iataaaserver.service.gamePlatform.util.ObservableImpl;
import fr.univ.iataaaserver.service.gamePlatform.util.Rules;

public class Game extends ObservableImpl{

    // CONSTANTS

    public final static String EVENT_BOARD_CHANGED = "BoardChanged"; // Attends un Piece[] en argument

    // ATTRIBUTES

    private Board<Case> pieces;
    private EnumPlayer currentPlayer;

    // CONSTRUCTOR

    public Game() {
        this.currentPlayer = EnumPlayer.PLAYER_1;
        this.pieces = initializeGame();
        firePropertyChange(EVENT_BOARD_CHANGED, null, getPieces());
    }

    // REQUESTS

    public Board<Case> getPieces()
    {
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
