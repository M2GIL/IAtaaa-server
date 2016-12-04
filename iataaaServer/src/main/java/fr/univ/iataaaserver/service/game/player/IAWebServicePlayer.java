package fr.univ.iataaaserver.service.game.player;

import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.util.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.domain.game.StatusService;
import fr.univ.iataaaserver.domain.game.Token;
import fr.univ.iataaaserver.service.game.GameImpl;

/**
 * Created by z3ddycus on 03/12/16.
 */
public class IAWebServicePlayer implements Player {
    void connect() {
        // TODO: 03/12/16
    }

    @Override
    public StatusService getStatut() {
        return null;
    }

    @Override
    public void startGame(Token idGame, Difficulty difficulty) throws BusyException {

    }

    @Override
    public void PlayGame(Token idToken, Difficulty difficulty, GameImpl boardGame, EnumPlayer player) throws Exception {

    }

    @Override
    public void endGame(Token idToken, String idGame, EndGameCase endType) throws Exception {

    }
}
