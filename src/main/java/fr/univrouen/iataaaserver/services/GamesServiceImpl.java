package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.Response;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
import fr.univrouen.iataaaserver.services.util.IPValidator;
import fr.univrouen.iataaaserver.services.util.RandomStringGenerator;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMethod;

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
            Player player1 = new WebServicePlayer(tokenP1, ipP1, portP1, difficultyP1);
            Player player2 = new WebServicePlayer(tokenP2, ipP2, portP2, difficultyP2);

            GameRunner gr = gr = new GameRunnerImpl(tokenGame, player1, difficultyP1, player2, difficultyP2);
            games.put(gameID, gr);
            try {
                gr.startGame();
            } catch (BusyException ex) {
                status = StatusResponse.ERROR;
            }
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
    

    @Override
    public List<String> getPlayers() {
        Collection<PlayerBean> pls = players.values();
        List<String> playerNames = new ArrayList<>();
        for (PlayerBean p : pls) {
            playerNames.add(p.getName());
        }
        return playerNames;
    }
    
    @Override
    public Response<PlayerBean> subscribePlayer(PlayerBean playerBean) {
        Response<PlayerBean> response = new Response<>();
        StatusResponse res = checkPlayerBean(playerBean);
        String token;
        PlayerBean p = null;
        if (res == StatusResponse.OK) {
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
        String[] playersNames = game.getPlayers();
        String gameName = game.getGameID();
        if (game.getGameID() == null 
            || playersNames == null 
            || playersNames.length != 2) {
            return StatusResponse.INVALIDE_ARGUMENT;
        }
        Collection<PlayerBean> playersBean = players.values();
        
        boolean containsP1 = false;
        boolean containsP2 = false;
        for (PlayerBean p : playersBean) {
            if (p.getName().equals(playersNames[0]) ) {
                containsP1 = true;
            }
            if (p.getName().equals(playersNames[1]) ) {
                containsP2 = true;
            }
        }
        if (!containsP1 || !containsP2) {
            return StatusResponse.PLAYERS_NO_FOUND;
        }
        
        if (games.containsKey(gameName)) {
            return StatusResponse.NAME_GAME_NOT_AVAILABLE;
        }
        
        return StatusResponse.OK;
    }
    
    private StatusResponse checkPlayerBean(PlayerBean player) {
        String ip = player.getIp();
        String name = player.getName();
        
        if (player.getDifficulty() == null 
            || player.getIp() == null 
            || name == null) {
            return StatusResponse.INVALIDE_ARGUMENT;
        }
        
        IPValidator ipValidador = new IPValidator();
        if (!ipValidador.validate(ip)) {
            return StatusResponse.BAD_IP;
        }
        
        Collection<PlayerBean> playersBean = players.values();
        
        for (PlayerBean p : playersBean) {
            if (p.getName().equals(name) ) {
                return StatusResponse.NAME_PLAYER_NOT_AVAILABLE;
            }
        }
        
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
