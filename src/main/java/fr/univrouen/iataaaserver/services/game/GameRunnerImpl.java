package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.domain.request.EnumPlayer;
import fr.univrouen.iataaaserver.domain.request.Difficulty;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.game.Token;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.request.StatusService;
import fr.univrouen.iataaaserver.domain.util.observable.ObservableImpl;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.player.Player;



public class GameRunnerImpl extends ObservableImpl implements GameRunner {

    // CONSTANTS

    private final static int J1 = EnumPlayer.J1.ordinal();
    private final static int J2 = EnumPlayer.J2.ordinal();

    // ATTRIBUTES

    private final Token id;
    private final Player[] players = new Player[2];
    private final Difficulty[] difficulties = new Difficulty[2];
    private final GameImpl game;
    private final EndGameAnalyser analyser;
    private EndGameCase victoryAborted = null;
    // CONSTRUCTOR

    public GameRunnerImpl(Token id, Player p1, Difficulty difficulty1, Player p2, Difficulty difficulty2) {
        this.id = id;
        players[J1] = p1;
        players[J2] = p2;
        difficulties[J1] = difficulty1;
        difficulties[J2] = difficulty2;
        game = new GameImpl();
        analyser = new EndGameAnalyser(game);
        firePropertyChange(EVENT_NEW_MOVE, null, null); // TODO: 13/12/16
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
        return difficulties[player.ordinal()];
    }

    // METHODS

    @Override
    public void startGame() throws BusyException {

        if (players[J1].getStatus() != StatusService.AVAILABLE
            || players[J2].getStatus() != StatusService.AVAILABLE) {
            throw new BusyException();
        }


        players[J1].startGame(EnumPlayer.J1);
        try {
            players[J2].startGame(EnumPlayer.J2);
        } catch (BusyException e) {
            victoryAborted = EndGameCase.ERROR;
            try {
                players[J1].endGame(victoryAborted);
            } catch (Exception ignored) {}
            return;
        }
        firePropertyChange(EVENT_START_GAME, null, null);
        while (getStatus() == EndGameCase.CONTINUE) {
            EnumPlayer player = game.getCurrentPlayer();
            Player current = players[player.ordinal()];
            Difficulty difficulty = difficulties[player.ordinal()];
            try {
                Board<Case> move = current.PlayGame(game.getPieces(), player);
                game.move(move);
                firePropertyChange(EVENT_NEW_MOVE, null, null); // TODO: 13/12/16
                display(game);
           /* } catch (ForbiddenMoveException e) {
                // Specify what to do if ia dont play in rules
                victoryAborted = EndGameCase.getVictory(EnumPlayer.getNextPlayer(player));*/
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
        firePropertyChange(EVENT_END_GAME, EndGameCase.CONTINUE, getStatus()); // TODO: 13/12/16
    }

    private void display(GameImpl game) {
        Case[] board = game.getPieces().toArray();
        int k = 0;
        System.out.println("---------------------");
        for (int x = 0; x < 10; ++x) {
            System.out.print("|");
            for(int y = 0; y < 10; ++y) {
                System.out.println(board[k].toString() + "|");
                ++k;
            }
            System.out.println("|\n---------------------");
        }
    }
}
