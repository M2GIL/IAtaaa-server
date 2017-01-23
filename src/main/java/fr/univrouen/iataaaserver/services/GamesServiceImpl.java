package fr.univrouen.iataaaserver.services;


import fr.univrouen.iataaaserver.controller.SynchronizeWebsocketGame;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.Token;
import fr.univrouen.iataaaserver.domain.request.*;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.Player;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
import fr.univrouen.iataaaserver.services.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Manager of games.
 */
@Service
public class GamesServiceImpl implements GamesService {

    @Autowired
    private SynchronizeWebsocketGame synchronizeWebSocketGame;

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

            String urlP1 = p1.getUrl(); 
            Difficulty difficultyP1 = p1.getDifficulty();
            String tokenP1 = p1.getToken();

            String urlP2 = p2.getUrl(); 
            Difficulty difficultyP2 = p2.getDifficulty();
            String tokenP2 = p2.getToken();

            Token tokenGame = new Token(gameID);
            Player player1 = new WebServicePlayer(tokenP1, urlP1, difficultyP1);
            Player player2 = new WebServicePlayer(tokenP2, urlP2, difficultyP2);

            GameRunner gr = gr = new GameRunnerImpl(tokenGame, player1, player2);
            synchronizeWebSocketGame.registerGame(gr);

            games.put(gameID, gr);
            try {
                gr.startGame();
            } catch (Exception ex) {
                status = StatusResponse.BUSY_IA;
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
    public List<String> getPlayersNames() {
        Collection<PlayerBean> pls = players.values();
        List<String> playerNames = new ArrayList<>();
        for (PlayerBean p : pls) {
            playerNames.add(p.getName());
        }
        return playerNames;
    }

    @Override
    public List<PlayerBean> getPlayers() {
        return new LinkedList<>(players.values());
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
        String url = player.getUrl();
        String name = player.getName();
        
        if (player.getDifficulty() == null 
            || player.getUrl() == null 
            || name == null) {
            return StatusResponse.INVALIDE_ARGUMENT;
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

    public PlayerBean getPlayer(String name) {
        return this.getPlayerBean(name);
    }
}
