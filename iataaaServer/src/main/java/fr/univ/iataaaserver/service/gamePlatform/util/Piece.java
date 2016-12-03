package fr.univ.iataaaserver.service.gamePlatform.util;


public enum Piece {
        EMPTY(0),
        WHITE_PIECE(1),
        WHITE_QUEEN(2),
        BLACK_PIECE(3),
        BLACK_QUEEN(4);

        private final int value;

        Piece(int value) {
                this.value = value;
            }
        public int getValue() {
                return value;
            }
}
