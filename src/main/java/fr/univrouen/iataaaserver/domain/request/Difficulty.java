package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnore;


public enum Difficulty {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    @JsonIgnore
    private String toJson;

    Difficulty(String toJson) {
        this.toJson = toJson;
    }

    String toJson() {
        return toJson;
    }
}
