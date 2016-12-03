package fr.univ.iataaaserver.service.gamePlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager of games.
 */
public class GameManager {

    // ATTRIBUTES

    Map<String, Game> games = new HashMap<>();
    List<CPUPlayer> cpus = new ArrayList<>();
    List<HumanPlayer> humans = new ArrayList<>();

    // CONSTRUCTOR

    public GameManager() {

    }

    // REQUESTS

    public Map<String, Game> getGames() {
        return games;
    }

    public List<CPUPlayer> getCpus() {
        return cpus;
    }

    public List<HumanPlayer> getHumans() {
        return humans;
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public HumanPlayer getHuman(String id) {
        HumanPlayer result = null;
        for (HumanPlayer human : humans) {
            // TODO: 03/12/16
        }
        return result;
    }

    public CPUPlayer getCPU(String id) {
        CPUPlayer result = null;
        for (CPUPlayer cpu : cpus) {
            // TODO: 03/12/16
        }
        return result;
    }


    // METHODS

}
