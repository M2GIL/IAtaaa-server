package fr.univrouen.iataaaserver.services.player;


import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.util.Rules;

import java.util.List;
import java.util.Random;

/**
 * Created by z3ddycus on 04/12/16.
 */
public class RandomizeCPUPlayer implements Player {

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
    public Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws Exception {
        List<Board<Case>> coupsPossibles = Rules.getAvailableMoves(boardGame, player);
        return coupsPossibles.get(new Random().nextInt(coupsPossibles.size()));
    }

    @Override
    public void endGame(EndGameCase endType) throws Exception {

    }
}
