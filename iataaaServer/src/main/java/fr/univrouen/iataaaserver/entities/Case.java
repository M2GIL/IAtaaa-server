package fr.univrouen.iataaaserver.entities;


public enum Case {
    EMPTY(0, " "),
    WHITE_PIECE(1, "x"),
    WHITE_QUEEN(2, "X"),
    BLACK_PIECE(3, "o"),
    BLACK_QUEEN(4, "O");

    // ATTRIBUTES

    private final int value;
    private final String abstraction;

    // CONSTRUCTOR

    Case(int value, String abstraction) {
        this.value = value;
        this.abstraction = abstraction;
    }

    // REQUEST

    public int getValue() {
            return value;
    }

    public String toString() {
        return abstraction;
    }
}
