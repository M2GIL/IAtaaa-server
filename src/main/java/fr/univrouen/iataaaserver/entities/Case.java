package fr.univrouen.iataaaserver.entities;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Case {
    EMPTY(0, " "),
    BLACK_PIECE(1, "o"),
    BLACK_QUEEN(2, "O"),
    WHITE_PIECE(3, "x"),
    WHITE_QUEEN(4, "X");
    

    // ATTRIBUTES

    private final int value;
    private final String abstraction;

    // CONSTRUCTOR

    Case(int value, String abstraction) {
        this.value = value;
        this.abstraction = abstraction;
    }

    // REQUEST

    @JsonValue
    public int getValue() {
            return value;
    }

    public String toString() {
        return abstraction;
    }
}
