package fr.univ.iataaaserver.service.gamePlatform;


import fr.univ.iataaaserver.service.gamePlatform.exception.BusyException;

public class GameRunner {

    // CONSTANTES
    public final static int J1 = 0;
    public final static int J2 = 1;

    // ATTRIBUTES

    private String id;
    private IPlayer[] players = new IPlayer[2];
    private boolean isFinished;
    private Game board;
    private EndGameAnalyser analyser;

    // CONSTRUCTOR

    public GameRunner(IPlayer p1, IPlayer p2) {
        players[J1] = p1;
        players[J2] = p2;
        board = new Game();
        isFinished = false;
        analyser = new EndGameAnalyser(board);
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
