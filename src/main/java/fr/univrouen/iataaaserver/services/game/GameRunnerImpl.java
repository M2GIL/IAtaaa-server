package fr.univrouen.iataaaserver.services.game;

import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.entities.util.observable.ObservableImpl;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.player.Player;



public class GameRunnerImpl extends ObservableImpl implements GameRunner {

    // CONSTANTS

    private final static int J1 = EnumPlayer.PLAYER_1.ordinal();
    private final static int J2 = EnumPlayer.PLAYER_2.ordinal();

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


        players[J1].startGame(EnumPlayer.PLAYER_1);
        players[J2].startGame(EnumPlayer.PLAYER_2);
        firePropertyChange(EVENT_START_GAME, null, null);
        while (getStatus() == EndGameCase.CONTINUE) {
            EnumPlayer player = game.getCurrentPlayer();
            Player current = players[player.ordinal()];
            Difficulty difficulty = difficulties[player.ordinal()];
            try {
                Board<Case> move = current.PlayGame(id,game.getPieces(), player);
                game.move(move);
                firePropertyChange(EVENT_NEW_MOVE, null, null); // TODO: 13/12/16
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
}
