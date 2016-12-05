package fr.univrouen.iataaaserver.services.player;



import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.StatusService;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.util.Rules;
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
