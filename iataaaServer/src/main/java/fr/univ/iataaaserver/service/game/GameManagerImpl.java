package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.service.game.player.WebServicePlayer;
import fr.univ.iataaaserver.service.game.player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager of games.
 */
public class GameManagerImpl implements GameManager {
    
    // ATTRIBUTES

    Map<String, GameRunnerImpl> games = new HashMap<>();
    List<WebServicePlayer> cpus = new ArrayList<>();
    List<Player> humans = new ArrayList<>();

    // CONSTRUCTOR

    public GameManagerImpl() {

    }

    // REQUESTS

    @Override
    public Map<String, GameRunnerImpl> getGames() {
        return games;
    }

    @Override
    public List<WebServicePlayer> getCpus() {
        return cpus;
    }

    @Override
    public List<Player> getHumans() {
        return humans;
    }

    @Override
    public GameRunnerImpl getGame(String id) {
        return games.get(id);
    }

    @Override
    public Player getHuman(String id) {
        Player result = null;
        for (Player human : humans) {
            // TODO: 03/12/16
        }
        return result;
    }

    @Override
    public WebServicePlayer getCPU(String id) {
        WebServicePlayer result = null;
        for (WebServicePlayer cpu : cpus) {
            // TODO: 03/12/16
        }
        return result;
    }


    // METHODS

}
