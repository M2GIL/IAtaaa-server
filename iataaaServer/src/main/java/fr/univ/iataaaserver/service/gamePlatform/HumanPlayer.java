package fr.univ.iataaaserver.service.gamePlatform;


import fr.univ.iataaaserver.service.gamePlatform.exception.BusyException;
import fr.univ.iataaaserver.service.gamePlatform.util.*;

public class HumanPlayer implements IAWebServicePlayer {

    private String ip;
    private int port;
    private String name;

    public HumanPlayer(String ip, int port, String name) {
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
    public void PlayGame(Token idToken, Difficulty difficulty, Board boardGame, EnumPlayer player) throws Exception {

    }

    @Override
    public void endGame(Token idToken, String idGame, EndGameCase endType) throws Exception {

    }
}
