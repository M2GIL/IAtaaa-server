package fr.univ.iataaaserver.service.gamePlatform;


import fr.univ.iataaaserver.domain.game.Piece;
import fr.univ.iataaaserver.domain.game.observable.ObservableImpl;
import fr.univ.iataaaserver.service.gamePlatform.util.EnumPlayer;

public class Board extends ObservableImpl{
    private Piece[] pieces;
    private EnumPlayer currentPlayer;

    public Board() {
        this.currentPlayer = EnumPlayer.Player1;
        this.pieces = initializeGame();
    }

    public Piece[] getPieces()
    {
        return pieces;
    }

    public EnumPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void move(Piece[] pieces) {


        currentPlayer = EnumPlayer.getNextPlayer(currentPlayer);
    }

    private Piece[] initializeGame() {
        Piece[] pieces = new Piece[50];
        for (int i = 0 ; i < 20; ++i) pieces[i] = Piece.BLACK_PIECE;
        for (int i = 20; i < 30; ++i) pieces[i] = Piece.EMPTY;
        for (int i = 30; i < 50; ++i) pieces[i] = Piece.WHITE_PIECE;
        return pieces;
    }



}
