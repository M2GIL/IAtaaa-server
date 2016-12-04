package fr.univ.iataaaserver.domain.game;


public enum Case {
        EMPTY(0),
        WHITE_PIECE(1),
        WHITE_QUEEN(2),
        BLACK_PIECE(3),
        BLACK_QUEEN(4);

        // ATTRIBUTES

        private final int value;

        // CONSTRUCTOR

        Case(int value) {
                this.value = value;
        }

        // REQUEST

        public int getValue() {
                return value;
            }
}
