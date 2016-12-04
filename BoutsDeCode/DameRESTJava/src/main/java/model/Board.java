package model;

public class Board {
    private char[] board;
    /*
     Représentation :
        0  1  2  3  4
        5  6  7  8  9
        10 11 12 13 14
        15 16 17 18 19
        20 21 22 23 24
        25 26 27 28 29
        30 31 32 33 34
        35 36 37 38 39
        40 41 42 43 44
        45 46 47 48 49
    */
    public Board() {
        board = new char[50];
        for (int i = 0; i < 50; ++i) {
            if (i <= 19) {
                board[i] = CASE.BLACK_PAWN.getCharRepresentation();
            }
            else if (i > 19 && i < 35) {
                board[i] = CASE.EMPTY.getCharRepresentation();
            }
            else {
                board[i] = CASE.WHITE_PAWN.getCharRepresentation();
            }
        }
    }
    
    public Board(char[] tabB) {
        board = tabB;
    }
    
    public char[] getBoard() {
        return board;
    }
    
}
