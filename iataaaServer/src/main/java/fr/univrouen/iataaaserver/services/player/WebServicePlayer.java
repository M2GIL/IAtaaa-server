package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.StatusService;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.exception.BusyException;


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
    public StatusService getStatus() {
        return null;
    }

    @Override
    public void startGame(Token idGame, Difficulty difficulty, EnumPlayer player) throws BusyException {

    }

    @Override
    public Board<Case> PlayGame(Difficulty difficulty, Board<Case> boardGame, EnumPlayer player) throws Exception {
        return null;
    }

    @Override
    public void endGame(Token idGame, EndGameCase endType) throws Exception {

    }
}
