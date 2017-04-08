package fr.univrouen.iataaaserver.api.game;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.game.exception.BusyException;
import fr.univrouen.iataaaserver.api.game.player.Player;
import fr.univrouen.iataaaserver.api.game.util.*;
import fr.univrouen.iataaaserver.api.game.util.observable.ObservableImpl;

import java.io.IOException;
import java.net.UnknownServiceException;

public class GameRunnerImpl extends ObservableImpl implements GameRunner {

    // CONSTANTS

    private final static int J1 = EnumPlayer.J1.ordinal();
    private final static int J2 = EnumPlayer.J2.ordinal();
    private final static int WAITING_TIME = 1000;

    // ATTRIBUTES

    @Override
    public Token getId() {
        return id;
    }

    private final Token id;
    private final Player[] players = new Player[2];
    private final GameImpl game;
    private final EndGameAnalyser analyser;
    private EndGameCase victoryAborted = null;
    private final int waitingTime;

    // CONSTRUCTOR

    public GameRunnerImpl(Token id, Player p1, Player p2, int waitingTime) {
        this.id = id;
        players[J1] = p1;
        players[J2] = p2;
        this.waitingTime = waitingTime;
        game = new GameImpl();
        analyser = new EndGameAnalyser(game);
        firePropertyChange(EVENT_NEW_MOVE, null, this);
    }

    public GameRunnerImpl(Token id, Player p1, Player p2) {
        this(id, p1, p2, WAITING_TIME);
    }

    // REQUESTS

    @Override
    public EndGameCase getStatus() {
        return (victoryAborted == null ? analyser.getStatusGame() : victoryAborted);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Player getPlayer(EnumPlayer player) {
        return players[player.ordinal()];
    }

    @Override
    public Difficulty getDifficulty(EnumPlayer player) {
        return players[player.ordinal()].getDifficulty();
    }

    // METHODS
    
    @Override
    public void getPlayerStatus() throws BusyException, IOException {
            if (players[J1].getStatus() != StatusService.AVAILABLE
                    || players[J2].getStatus() != StatusService.AVAILABLE) {
                throw new BusyException();
            }
    }

    @Override
    public void startGame() throws BusyException, UnknownServiceException {
        players[J1].startGame(EnumPlayer.J1);
        try {
            players[J2].startGame(EnumPlayer.J2);
        } catch (Exception e) {
            victoryAborted = EndGameCase.ERROR;
            try {
                players[J1].endGame(victoryAborted);
            } catch (Exception ignored) {}
            return;
        }
        firePropertyChange(EVENT_START_GAME, null, this);
        while (getStatus() == EndGameCase.CONTINUE) {
            EnumPlayer player = game.getCurrentPlayer();
            Player currentPlayer = players[player.ordinal()];
            try {
                Board<Case> move = currentPlayer.playGame(game.getPieces(), player);
                game.move(move);
                firePropertyChange(EVENT_NEW_MOVE, null, this);
                Thread.sleep(waitingTime);
            } catch (Exception e) {
                victoryAborted = EndGameCase.getVictory(EnumPlayer.getNextPlayer(player));
            }
        }

        try {
            players[J1].endGame(getStatus());
        } catch (Exception ignored) {}
        try {
            players[J2].endGame(getStatus());
        } catch (Exception ignored) {}
        firePropertyChange(EVENT_END_GAME, null, this); // TODO: 13/12/16
    }

    public void abort() {
        victoryAborted = EndGameCase.ERROR;
    }
}
