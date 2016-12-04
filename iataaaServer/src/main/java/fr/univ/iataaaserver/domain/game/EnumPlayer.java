package fr.univ.iataaaserver.domain.game;


public enum EnumPlayer {
    PLAYER_1, PLAYER_2;

    public static EnumPlayer getNextPlayer(EnumPlayer player) {
        if (player == null) throw new AssertionError();
        if (player == PLAYER_1) return PLAYER_2;
        return PLAYER_1;
    }
}
