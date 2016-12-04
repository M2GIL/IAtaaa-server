package fr.univ.iataaaserver.service.gamePlatform;

import fr.univ.iataaaserver.service.gamePlatform.exception.BusyException;
import fr.univ.iataaaserver.service.gamePlatform.util.*;

/**
 * Created by z3ddycus on 03/12/16.
 */
public class IAWebServicePlayer implements IPlayer {
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
    public void PlayGame(Token idToken, Difficulty difficulty, Game boardGame, EnumPlayer player) throws Exception {

    }

    @Override
    public void endGame(Token idToken, String idGame, EndGameCase endType) throws Exception {

    }
}
