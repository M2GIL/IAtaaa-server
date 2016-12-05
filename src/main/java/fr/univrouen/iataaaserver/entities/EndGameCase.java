package fr.univrouen.iataaaserver.entities;


public enum EndGameCase {

    PLAYER_1_VICTORY, PLAYER_2_VICTORY, DRAW, ERROR, CONTINUE;



    public static EndGameCase getVictory(EnumPlayer player) {
        if (player == null) {
            return ERROR;
        }
        if (player == EnumPlayer.PLAYER_1) {
            return PLAYER_1_VICTORY;
        }
        return PLAYER_2_VICTORY;
    }
}
