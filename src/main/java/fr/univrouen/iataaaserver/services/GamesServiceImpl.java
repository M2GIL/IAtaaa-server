package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.GameBean;
import fr.univrouen.iataaaserver.entities.StatusGameCreation;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
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
    public StatusGameCreation createGame(GameBean gameBean) {
        String gameID = gameBean.getGameID(); 
        String iaToken1 = gameBean.getIaToken1(); 
        String iaName1 = gameBean.getIaName1(); 
        String iaIP1 = gameBean.getIaIP1(); 
        int iaPort1 = gameBean.getIaPort1(); 
        Difficulty difficulty1 = gameBean.getIaDifficulty1();
        String iaToken2 = gameBean.getIaToken2(); 
        String iaName2 = gameBean.getIaName2(); 
        String iaIP2 = gameBean.getIaIP2();
        int iaPort2 = gameBean.getIaPort2();
        Difficulty difficulty2 = gameBean.getIaDifficulty2();

        Token tokenGame = new Token(gameID);
        Player p1 = new WebServicePlayer(iaToken1, iaIP1, iaPort1);
        Player p2 = new WebServicePlayer(iaToken2, iaIP2, iaPort2);

        GameRunner gr = new GameRunnerImpl(tokenGame, p1, difficulty1, p2, difficulty2);
        games.put(gameID, gr);
        
        return StatusGameCreation.OK;
    }

    @Override
    public Set<String> getGameNames() {
        Set<String> names = games.keySet();
        return names;
    }

    @Override
    public Board<Case> getBoard(String gameID) {
        GameRunner gr = games.get(gameID);
        return gr.getGame().getPieces();
    }

}
