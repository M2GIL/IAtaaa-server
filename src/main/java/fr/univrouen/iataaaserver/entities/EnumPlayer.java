package fr.univrouen.iataaaserver.entities;


public enum EnumPlayer {
    J1, J2, DRAW;

    public static EnumPlayer getNextPlayer(EnumPlayer player) {
        if (player == null) throw new AssertionError();
        if (player == J1) return J2;
        return J1;
    }
}
