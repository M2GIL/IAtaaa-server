package fr.univrouen.iataaaserver.services;

import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.dto.Response;
import fr.univrouen.iataaaserver.dto.GameDTO;
import fr.univrouen.iataaaserver.dto.PlayerDTO;
import java.util.List;
import java.util.Set;

public interface GamesService {
    
    public final static int TOKEN_SIZE = 20;
    public static final int WAINTING_TIME = 1000;
    
    Response<GameDTO> createGame(GameDTO gameBean);
    boolean deleteGame(String id);
    boolean deletePlayer(String id);
    Set<String> getGameNames();
    Board<Case> getBoard(String gameID);
    Response<PlayerDTO> subscribePlayer(PlayerDTO player);
    List<String> getPlayersNames();
    List<PlayerDTO> getPlayers();
    PlayerDTO getPlayer(String name);
}
