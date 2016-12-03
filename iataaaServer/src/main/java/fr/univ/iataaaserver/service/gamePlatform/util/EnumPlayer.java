package fr.univ.iataaaserver.service.gamePlatform.util;

/**
 * Created by z3ddycus on 03/12/16.
 */
public enum EnumPlayer {
    Player1, Player2;

    public static EnumPlayer getNextPlayer(EnumPlayer player) {
        if (player == null) throw new AssertionError();
        if (player == Player1) return Player2;
        return Player1;
    }
}
