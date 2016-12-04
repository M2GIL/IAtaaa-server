package fr.univ.iataaaserver.service.game.player;


import fr.univ.iataaaserver.service.game.exception.BusyException;
import fr.univ.iataaaserver.domain.game.Difficulty;
import fr.univ.iataaaserver.domain.game.util.EndGameCase;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.domain.game.StatusService;
import fr.univ.iataaaserver.domain.game.Token;
import fr.univ.iataaaserver.service.game.GameImpl;

public class WebSocketPlayer implements Player {

    private final String ip;
    private final int port;
    private final String name;

    public WebSocketPlayer(String ip, int port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
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
