package fr.univ.iataaaserver.service.game.game;


import fr.univ.iataaaserver.service.game.player.Player;
import fr.univ.iataaaserver.service.game.exception.BusyException;

public class GameRunnerImpl implements GameRunner {

    // CONSTANTES
    public final static int J1 = 0;
    public final static int J2 = 1;

    // ATTRIBUTES

    private String id;
    private final Player[] players = new Player[2];
    private boolean isFinished;
    private final GameImpl board;
    private final EndGameAnalyser analyser;

    // CONSTRUCTOR

    public GameRunnerImpl(Player p1, Player p2) {
        players[J1] = p1;
        players[J2] = p2;
        board = new GameImpl();
        isFinished = false;
        analyser = new EndGameAnalyser(board);
    }

    // REQUESTS

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    // METHODS

    @Override
    public void startGame() throws BusyException {

        // TOUTE LA PARTIE!!!!!!!!!!!!!!!!!!!!!!!!

        isFinished = true;
    }
}
