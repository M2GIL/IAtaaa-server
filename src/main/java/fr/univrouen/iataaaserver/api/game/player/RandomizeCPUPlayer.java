package fr.univrouen.iataaaserver.api.game.player;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.game.exception.BusyException;
import fr.univrouen.iataaaserver.api.game.rules.Rules;
import fr.univrouen.iataaaserver.api.game.util.*;

import java.util.List;
import java.util.Random;


public class RandomizeCPUPlayer implements Player {

    private final String name;

    public RandomizeCPUPlayer(String name) {
        this.name = name;
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {

    }

    @Override
    public StatusService getStatus() {
        return StatusService.AVAILABLE;
    }

    @Override
    public void startGame(EnumPlayer player) throws BusyException {
    }

    @Override
    public Board<Case> playGame(Board<Case> boardGame, EnumPlayer player) throws Exception {
        List<Board<Case>> availableMoves = Rules.getAvailableMoves(boardGame, player);
        return availableMoves.get(new Random().nextInt(availableMoves.size()));
    }

    @Override
    public void endGame(EndGameCase endType) throws Exception {

    }
}
