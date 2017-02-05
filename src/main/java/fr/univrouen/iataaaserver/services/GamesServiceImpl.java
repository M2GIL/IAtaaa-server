package fr.univrouen.iataaaserver.services;

import fr.univrouen.iataaaserver.util.exception.PlayerNotFoundException;
import fr.univrouen.iataaaserver.dto.Difficulty;
import fr.univrouen.iataaaserver.dto.StatusType;
import fr.univrouen.iataaaserver.dto.GameDTO;
import fr.univrouen.iataaaserver.dto.PlayerDTO;
import fr.univrouen.iataaaserver.dto.Response;
import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.domain.Token;
import fr.univrouen.iataaaserver.game.GameRunner;
import fr.univrouen.iataaaserver.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.player.Player;
import fr.univrouen.iataaaserver.player.WebServicePlayer;
import fr.univrouen.iataaaserver.util.RandomStringGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manager of games.
 */
@Service
public class GamesServiceImpl implements GamesService {

    @Autowired
    private SynchronizeWebsocketGame synchronizeWebSocketGame;

    private final Map<String, GameRunner> games;
    private final Map<String, PlayerDTO> players;

    public GamesServiceImpl() {
        games = new HashMap<>();
        players = new HashMap<>();
    }
    
    @Override
    public Response<GameDTO> createGame(GameDTO gameBean) {
        
        Response<GameDTO> response = new Response<>();
        StatusType status = checkGameBean(gameBean);
        if (status == StatusType.OK) {
            String gameID = gameBean.getGameID();
            
            String[] playersBean = gameBean.getPlayers();
            PlayerDTO p1 = getPlayerBean(playersBean[0]);
            PlayerDTO p2 = getPlayerBean(playersBean[1]);

            String urlP1 = p1.getUrl(); 
            Difficulty difficultyP1 = p1.getDifficulty();
            String tokenP1 = p1.getToken();

            String urlP2 = p2.getUrl(); 
            Difficulty difficultyP2 = p2.getDifficulty();
            String tokenP2 = p2.getToken();

            Token tokenGame = new Token(gameID);
            Player player1 = new WebServicePlayer(tokenP1, urlP1, difficultyP1);
            Player player2 = new WebServicePlayer(tokenP2, urlP2, difficultyP2);

            GameRunner gr = new GameRunnerImpl(tokenGame, player1, player2, WAINTING_TIME);
            synchronizeWebSocketGame.registerGame(gr);

            games.put(gameID, gr);
            try {
                gr.startGame();
            } catch (Exception ex) {
                status = StatusType.BUSY_IA;
            }
            response.setContent(gameBean);
        } else {
            response.setContent(null);
        }
        response.setStatus(status);

        return response;
    }
    
    @Override
    public boolean deleteGame(String id) {
        return (games.remove(id) != null);
    }
    
    @Override
    public boolean deletePlayer(String id) {
        return (players.remove(id) != null);
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
        Collection<PlayerDTO> pls = players.values();
        List<String> playerNames = new ArrayList<>();
        pls.stream().forEach((p) -> {
            playerNames.add(p.getName());
        });
        return playerNames;
    }

    @Override
    public List<PlayerDTO> getPlayers() {
        return new LinkedList<>(players.values());
    }

    @Override
    public Response<PlayerDTO> subscribePlayer(PlayerDTO playerBean) {
        Response<PlayerDTO> response = new Response<>();
        StatusType res = checkPlayerBean(playerBean);
        String token;
        PlayerDTO p = null;
        if (res == StatusType.OK) {
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
    
    @Override
    public PlayerDTO getPlayer(String name) {
        return this.getPlayerBean(name);
    }
    
    
// PRIVATE
    
    private StatusType checkGameBean(GameDTO game) {
        String[] playersNames = game.getPlayers();
        String gameName = game.getGameID();
        if (game.getGameID() == null 
            || playersNames == null 
            || playersNames.length != 2) {
            return StatusType.INVALIDE_ARGUMENT;
        }
        Collection<PlayerDTO> playersBean = players.values();
        
        boolean containsP1 = false;
        boolean containsP2 = false;
        for (PlayerDTO p : playersBean) {
            if (p.getName().equals(playersNames[0]) ) {
                containsP1 = true;
            }
            if (p.getName().equals(playersNames[1]) ) {
                containsP2 = true;
            }
        }
        if (!containsP1 || !containsP2) {
            return StatusType.PLAYERS_NO_FOUND;
        }
        
        if (games.containsKey(gameName)) {
            return StatusType.NAME_GAME_NOT_AVAILABLE;
        }
        
        return StatusType.OK;
    }
    
    private StatusType checkPlayerBean(PlayerDTO player) {
        String url = player.getUrl();
        String name = player.getName();
        
        if (player.getDifficulty() == null 
            || player.getUrl() == null 
            || name == null) {
            return StatusType.INVALIDE_ARGUMENT;
        }

        Collection<PlayerDTO> playersBean = players.values();
        
        for (PlayerDTO p : playersBean) {
            if (p.getName().equals(name) ) {
                return StatusType.NAME_PLAYER_NOT_AVAILABLE;
            }
        }
        
        return StatusType.OK;
    }
    
    private PlayerDTO getPlayerBean(String name) {
        for (PlayerDTO p : players.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        throw new PlayerNotFoundException(name);
    }
}