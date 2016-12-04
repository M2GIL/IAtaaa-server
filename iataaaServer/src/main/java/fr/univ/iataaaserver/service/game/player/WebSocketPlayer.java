package fr.univ.iataaaserver.service.game.player;


import fr.univ.iataaaserver.domain.game.*;
import fr.univ.iataaaserver.service.game.exception.BusyException;

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
    public StatusService getStatus() {
        return null;
    }

    @Override
    public void startGame(Token idGame, Difficulty difficulty) throws BusyException {

    }

    @Override
    public Board<Case> PlayGame(Difficulty difficulty, Board<Case> boardGame, EnumPlayer player) throws Exception {
        return null;
    }

    @Override
    public void endGame(Token idGame, EndGameCase endType) throws Exception {

    }
}
