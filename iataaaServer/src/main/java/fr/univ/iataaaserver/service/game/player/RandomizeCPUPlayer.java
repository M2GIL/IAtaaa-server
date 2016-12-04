package fr.univ.iataaaserver.service.game.player;

import fr.univ.iataaaserver.domain.game.*;
import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.service.game.util.Rules;

import java.util.List;
import java.util.Random;

/**
 * Created by z3ddycus on 04/12/16.
 */
public class RandomizeCPUPlayer implements Player {

    @Override
    public StatusService getStatus() {
        return StatusService.AVAILABLE;
    }

    @Override
    public void startGame(Token idGame, Difficulty difficulty, EnumPlayer player) throws BusyException {

    }

    @Override
    public Board<Case> PlayGame(Difficulty difficulty, Board<Case> boardGame, EnumPlayer player) throws Exception {
        List<Board<Case>> coupsPossibles = Rules.getAvailableMoves(boardGame, player);
        return coupsPossibles.get(new Random().nextInt(coupsPossibles.size()));
    }

    @Override
    public void endGame(Token idGame, EndGameCase endType) throws Exception {

    }
}
