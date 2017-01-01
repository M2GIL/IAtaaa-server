package fr.univrouen.iataaaserver.entities;


public enum Difficulty {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private String toJson;

    Difficulty(String toJson) {
        this.toJson = toJson;
    }

    String toJson() {
        return toJson;
    }
}
