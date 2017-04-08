package fr.univrouen.iataaaserver.api.game.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Case {
    EMPTY('0', " "),
    BLACK_PIECE('1', "o"),
    BLACK_QUEEN('2', "O"),
    WHITE_PIECE('3', "x"),
    WHITE_QUEEN('4', "X");
    

    // ATTRIBUTES

    private final char value;
    @JsonIgnore
    private final String abstraction;

    // CONSTRUCTOR

    Case(char value, String abstraction) {
        this.value = value;
        this.abstraction = abstraction;
    }

    // REQUEST

    @JsonValue
    public char getValue() {
            return value;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return abstraction;
    }
}
