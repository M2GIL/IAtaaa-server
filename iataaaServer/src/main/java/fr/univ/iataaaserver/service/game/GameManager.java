package fr.univ.iataaaserver.service.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager of games.
 */
public class GameManager {

    // ATTRIBUTES

    Map<String, GameRunner> games = new HashMap<>();
    List<IAWebServicePlayer> cpus = new ArrayList<>();
    List<WebSocketPlayer> humans = new ArrayList<>();

    // CONSTRUCTOR

    public GameManager() {

    }

    // REQUESTS

    public Map<String, GameRunner> getGames() {
        return games;
    }

    public List<IAWebServicePlayer> getCpus() {
        return cpus;
    }

    public List<WebSocketPlayer> getHumans() {
        return humans;
    }

    public GameRunner getGame(String id) {
        return games.get(id);
    }

    public WebSocketPlayer getHuman(String id) {
        WebSocketPlayer result = null;
        for (WebSocketPlayer human : humans) {
            // TODO: 03/12/16
        }
        return result;
    }

    public IAWebServicePlayer getCPU(String id) {
        IAWebServicePlayer result = null;
        for (IAWebServicePlayer cpu : cpus) {
            // TODO: 03/12/16
        }
        return result;
    }


    // METHODS

}
