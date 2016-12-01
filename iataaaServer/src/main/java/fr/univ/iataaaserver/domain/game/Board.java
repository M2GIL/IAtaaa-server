/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univ.iataaaserver.domain.game.observable.ObservableImpl;

/**
 *
 * @author anto
 */
public class Board extends ObservableImpl {
    
    public static final String CHANGED_PIECES_PROPERTY_NAME = "piece_move";
    
    @JsonProperty
    private Piece[] pieces;
    
    @JsonProperty
    private PlayerEnum currentPlayer;

    public Piece[] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[] pieces) {
        Piece[] old = pieces;
        this.pieces = pieces;
        firePropertyChange(CHANGED_PIECES_PROPERTY_NAME, old, pieces);
    }

    public PlayerEnum getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerEnum currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
}
