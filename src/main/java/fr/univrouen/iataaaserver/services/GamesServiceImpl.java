package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.Response;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
import fr.univrouen.iataaaserver.services.util.RandomStringGenerator;
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
    private final Map<String, PlayerBean> players;

    public GamesServiceImpl() {
        games = new HashMap<>();
        players = new HashMap<>();
    }
    
    @Override
    public Response<GameBean> createGame(GameBean gameBean) {
        
        Response<GameBean> response = new Response<>();
        StatusResponse status = checkGameBean(gameBean);
        if (status == StatusResponse.OK) {
            String gameID = gameBean.getGameID();
            
            String[] playersBean = gameBean.getPlayers();
            PlayerBean p1 = getPlayerBean(playersBean[0]);
            PlayerBean p2 = getPlayerBean(playersBean[1]);

            String ipP1 = p1.getIp(); 
            int portP1 = p1.getPort(); 
            Difficulty difficultyP1 = p1.getDifficulty();
            String tokenP1 = p1.getToken();

            String ipP2 = p2.getIp(); 
            int portP2 = p2.getPort(); 
            Difficulty difficultyP2 = p2.getDifficulty();
            String tokenP2 = p2.getToken();

            Token tokenGame = new Token(gameID);
            Player player1 = new WebServicePlayer(tokenP1, ipP1, portP1);
            Player player2 = new WebServicePlayer(tokenP2, ipP2, portP2);

            GameRunner gr = new GameRunnerImpl(tokenGame, player1, difficultyP1, player2, difficultyP2);
            games.put(gameID, gr);
            response.setContent(gameBean);
        } else {
            response.setContent(null);
        }
        response.setStatus(status);

        return response;
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
    
    public Response<PlayerBean> subscribePlayer(PlayerBean playerBean) {
        StatusResponse res = checkPlayerBean(playerBean);
        Response<PlayerBean> response = new Response<>();
        
        if (res == StatusResponse.OK) {
            String token;
            do {
                token = RandomStringGenerator.getRandomString(TOKEN_SIZE);
            } while (players.containsKey(token));

            playerBean.setToken(token);
            players.put(token, playerBean);
            response.setContent(playerBean);
        } else {
            response.setContent(playerBean);
        }
        response.setStatus(res);
        
        return response;
    }
    
    
// PRIVATE
    
    private StatusResponse checkGameBean(GameBean game) {
        return StatusResponse.OK;
    }
    
    private StatusResponse checkPlayerBean(PlayerBean game) {
        return StatusResponse.OK;
    }
    
    private PlayerBean getPlayerBean(String name) {
        for (PlayerBean p : players.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
