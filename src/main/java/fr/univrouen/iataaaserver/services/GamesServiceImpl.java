package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.StatusGameCreation;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Manager of games.
 */
@Service
public class GamesServiceImpl implements GamesService {
    
    private final Map<String, GameRunner> games;

    public GamesServiceImpl() {
        games = new HashMap<>();
    }
    
    @Override
    public StatusGameCreation createGame(String gameID, String iaName, String iaIP1, int iaPort1, String iaIP2, int iaPort2, Difficulty difficulty) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
