package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Manager of games.
 */
public class GameManagerImpl implements GameManager {
    
    private final Map<String, GameRunner> games;

    public GameManagerImpl() {
        games = new HashMap<>();
    }
    
      @Override
    public boolean createGame(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Set<String> getGameNames() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Board getBoard(String gameID) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    

    /*
    @Override
    public Map<String, GameRunner> getGames() {
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
    public GameRunner getGame(String id) {
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

  

    */

}
