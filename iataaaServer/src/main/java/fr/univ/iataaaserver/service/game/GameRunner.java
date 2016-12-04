package fr.univ.iataaaserver.service.game;


import fr.univ.iataaaserver.service.game.player.Player;
import fr.univ.iataaaserver.service.game.exception.BusyException;

public class GameRunner {

    // CONSTANTES
    public final static int J1 = 0;
    public final static int J2 = 1;

    // ATTRIBUTES

    private String id;
    private Player[] players = new Player[2];
    private boolean isFinished;
    private GameImpl board;
    private EndGameAnalyser analyser;

    // CONSTRUCTOR

    public GameRunner(Player p1, Player p2) {
        players[J1] = p1;
        players[J2] = p2;
        board = new GameImpl();
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
