package fr.univ.iataaaserver.domain.game;

public enum Piece {
    WHITE_PIECE(1),
    BLACK_PIECE(-1),
    WHITE_QUEEN(3),
    BLACK_QUEEN(-3),
    EMPTY(0);
    
    private final int value;
    
    private Piece(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    } 
    
}
