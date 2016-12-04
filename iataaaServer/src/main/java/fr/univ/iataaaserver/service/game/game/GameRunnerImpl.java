package fr.univ.iataaaserver.service.game.game;


import fr.univ.iataaaserver.domain.game.*;
import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.service.game.player.Player;

public class GameRunnerImpl implements GameRunner {

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
    }

    // REQUESTS

    @Override
    public EndGameCase getStatus() {
        return victoryAborted == null ? analyser.getStatusGame() : victoryAborted;
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

        players[J1].startGame(id, difficulties[J1], EnumPlayer.PLAYER_1);
        players[J1].startGame(id, difficulties[J2], EnumPlayer.PLAYER_2);

        while (getStatus() == EndGameCase.CONTINUE) {
            EnumPlayer player = game.getCurrentPlayer();
            Player current = players[player.ordinal()];
            Difficulty difficulty = difficulties[player.ordinal()];
            try {
                Board<Case> move = current.PlayGame(difficulty, game.getPieces(), player);
                game.move(move);
           /* } catch (ForbiddenMoveException e) {
                // Specify what to do if ia dont play in rules
                victoryAborted = EndGameCase.getVictory(EnumPlayer.getNextPlayer(player));*/
            } catch (Exception e) {
                victoryAborted = EndGameCase.getVictory(EnumPlayer.getNextPlayer(player));
            }
        }

        try {
            players[J1].endGame(id, getStatus());
        } catch (Exception ignored) {}
        try {
            players[J2].endGame(id, getStatus());
        } catch (Exception ignored) {}
    }
}
