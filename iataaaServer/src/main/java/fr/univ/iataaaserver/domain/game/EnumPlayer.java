package fr.univ.iataaaserver.domain.game;

/**
 * Created by z3ddycus on 03/12/16.
 */
public enum EnumPlayer {
    PLAYER_1, PLAYER_2;

    public static EnumPlayer getNextPlayer(EnumPlayer player) {
        if (player == null) throw new AssertionError();
        if (player == PLAYER_1) return PLAYER_2;
        return PLAYER_1;
    }
}
