package fr.univ.iataaaserver.domain.game;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Piece {
    EMPTY(0),
    BLACK_PIECE(1),
    BLACK_QUEEN(2),
    WHITE_PIECE(3),
    WHITE_QUEEN(4);
    
    private final int value;
    
    private Piece(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    } 

    
}
