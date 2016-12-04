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
public class WebServicePlayer implements Player {

    private final String id;
    private final String url;
    private final int port;

    public WebServicePlayer(String token, String url, int port) {
        this.id = token;
        this.url = url;
        this.port = port;
    }

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
    public void PlayGame(Difficulty difficulty, GameImpl boardGame, EnumPlayer player) throws Exception {

    }

    @Override
    public void endGame(Token idGame, EndGameCase endType) throws Exception {

    }
}
