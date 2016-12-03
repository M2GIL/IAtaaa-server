package fr.univ.iataaaserver.service.gamePlatform;


import fr.univ.iataaaserver.service.gamePlatform.Exception.BusyException;

public class Game {

    // CONSTANTES
    public final static int J1 = 0;
    public final static int J2 = 1;

    // ATTRIBUTES

    private String id;
    private IPlayer[] players = new IPlayer[2];
    private boolean isFinished;
    // CONSTRUCTOR

    public Game(IPlayer p1, IPlayer p2) {
        players[J1] = p1;
        players[J2] = p2;
        isFinished = false;
    }

    // REQUESTS

    public boolean isFinished() {
        return isFinished;
    }

    // METHODS

    public void startGame() throws BusyException {

        // TOUTE LA PARTIE!!!!!!!!!!!!!!!!!!!!!!!!

        isFinished = true;
    }
}
