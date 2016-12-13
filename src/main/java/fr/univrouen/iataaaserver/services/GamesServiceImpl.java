package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        
        StatusGameCreation status = checkGameBean(gameBean);
        if (status == StatusGameCreation.OK) {
            String gameID = gameBean.getGameID();
            PlayerBean p1 = gameBean.getPlayers()[0];
            PlayerBean p2 = gameBean.getPlayers()[1];

            String tokenP1 = p1.getToken(); 
            String nameP1 = p1.getName(); 
            String ipP1 = p1.getIp(); 
            int portP1 = p1.getPort(); 
            Difficulty difficultyP1 = p1.getDifficulty();

            String tokenP2 = p2.getToken(); 
            String nameP2 = p2.getName(); 
            String ipP2 = p2.getIp(); 
            int portP2 = p2.getPort(); 
            Difficulty difficultyP2 = p2.getDifficulty();

            Token tokenGame = new Token(gameID);
            Player player1 = new WebServicePlayer(tokenP1, ipP1, portP1, Difficulty.EASY);
            Player player2 = new WebServicePlayer(tokenP2, ipP2, portP2, Difficulty.EASY);

            GameRunner gr = null;
            gr = new GameRunnerImpl(tokenGame, player1, difficultyP1, player2, difficultyP2);
            games.put(gameID, gr);
        }
        return status;
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
    
    private StatusGameCreation checkGameBean(GameBean game) {
        return StatusGameCreation.OK;
    }

}
