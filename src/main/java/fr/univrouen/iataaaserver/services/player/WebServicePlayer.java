package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;


/**
 * Created by z3ddycus on 03/12/16.
 */
public class WebServicePlayer implements Player {

    private final String id;
    private final String url;
    private final int port;
    private Difficulty difficulty;
    public WebServicePlayer(String token, String url, int port, Difficulty difficulty) {
        this.id = token;
        this.difficulty = difficulty;
        this.url = url;
        this.port = port;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public StatusService getStatus() {
        return null;
    }

    @Override
    public void startGame(Token idGame, EnumPlayer player) throws BusyException {

    }

    @Override
    public Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {
        return null;
    }
    @Override
    public void endGame(Token idGame, EndGameCase endType) throws Exception {

    }
}
