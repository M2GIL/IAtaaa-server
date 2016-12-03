package fr.univ.iataaaserver.service.gamePlatform.util;


public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String toJson;

    Difficulty(String toJson) {
        this.toJson = toJson;
    }

    String toJson() {
        return toJson;
    }
}
