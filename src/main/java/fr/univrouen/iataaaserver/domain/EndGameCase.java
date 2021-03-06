package fr.univrouen.iataaaserver.domain;

import fr.univrouen.iataaaserver.dto.EnumPlayer;

public enum EndGameCase {

    PLAYER_1_VICTORY,
    PLAYER_2_VICTORY,
    DRAW,
    ERROR,
    CONTINUE;
    
    public static EndGameCase getVictory(EnumPlayer player) {
        if (player == null) {
            return ERROR;
        }
        if (player == EnumPlayer.J1) {
            return PLAYER_1_VICTORY;
        }
        return PLAYER_2_VICTORY;
    }
}
