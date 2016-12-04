package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.service.game.player.HumanPlayer;
import fr.univ.iataaaserver.service.game.player.IAWebServicePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager of games.
 */
public class GameManagerImpl implements GameManager{

    // ATTRIBUTES

    Map<String, GameRunner> games = new HashMap<>();
    List<IAWebServicePlayer> cpus = new ArrayList<>();
    List<HumanPlayer> humans = new ArrayList<>();

    // CONSTRUCTOR

    public GameManagerImpl() {

    }

    // REQUESTS

    @Override
    public Map<String, GameRunner> getGames() {
        return games;
    }

    @Override
    public List<IAWebServicePlayer> getCpus() {
        return cpus;
    }

    @Override
    public List<HumanPlayer> getHumans() {
        return humans;
    }

    @Override
    public GameRunner getGame(String id) {
        return games.get(id);
    }

    @Override
    public HumanPlayer getHuman(String id) {
        HumanPlayer result = null;
        for (HumanPlayer human : humans) {
            // TODO: 03/12/16
        }
        return result;
    }

    @Override
    public IAWebServicePlayer getCPU(String id) {
        IAWebServicePlayer result = null;
        for (IAWebServicePlayer cpu : cpus) {
            // TODO: 03/12/16
        }
        return result;
    }


    // METHODS

}
