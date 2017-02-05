package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.dto.Response;
import fr.univrouen.iataaaserver.dto.GameDTO;
import fr.univrouen.iataaaserver.dto.PlayerDTO;
import fr.univrouen.iataaaserver.dto.StatusType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fr.univrouen.iataaaserver.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class GameController {

    @Autowired
    private GamesService gamesService;
    
    @RequestMapping(value = { "games" }, method = RequestMethod.GET)
    public ResponseEntity<Response<Set<String>>> getGameNames(ModelMap model) {
        Set<String> names = gamesService.getGameNames();
        Response<Set<String>> response = new Response<>();
        response.setContent(names);
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "game/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Response<Board<Case>>> findGameById(ModelMap model, @PathVariable("gameID") String gameID) {
        Board<Case> board = gamesService.getBoard(gameID);
        Response<Board<Case>> response = new Response<>();
        response.setContent(board);
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "game/{gameId}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGame(ModelMap model, @PathVariable("gameId") String gameId) {
        boolean isDelete = gamesService.deleteGame(gameId);
        if (isDelete) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "game" }, method = RequestMethod.POST)
    public ResponseEntity<Response<GameDTO>> createGame(ModelMap model, @RequestBody GameDTO gameBean) {
        return new ResponseEntity<>(
                gamesService.createGame(gameBean),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = { "players" }, method = RequestMethod.POST)
    public ResponseEntity<Response<String>> createPlayer(ModelMap model, @RequestBody PlayerDTO playerBean) {
        Response<PlayerDTO> responsePlayer = gamesService.subscribePlayer(playerBean);
        PlayerDTO p = responsePlayer.getContent();
        String token = null;
        HttpStatus httpS = HttpStatus.BAD_REQUEST;
        if (p != null) {
            token = p.getToken();
            httpS = HttpStatus.OK;
        }
        
        if (responsePlayer.getStatus() != StatusType.OK) {
            httpS = HttpStatus.BAD_REQUEST;
        }
        
        Response<String> response = new Response<>();
        response.setContent(token);
        response.setStatus(responsePlayer.getStatus());
        return new ResponseEntity<>(response, httpS);
    }
    
    @RequestMapping(value = { "players" }, method = RequestMethod.GET)
    public ResponseEntity<Response<List<PlayerDTO>>> findAllPlayers(ModelMap model) {
        List<PlayerDTO> players = gamesService.getPlayers();
        Response<List<PlayerDTO>> response = new Response<>();
        response.setContent(players);
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value= {"players/{name}"}, method = RequestMethod.GET)
    public ResponseEntity<Response<PlayerDTO>> findPlayer(@PathVariable String name) {
        Response<PlayerDTO> response = new Response<>();
        response.setContent(gamesService.getPlayer(name));
        response.setStatus(StatusType.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "players/{playerId}" }, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlayer(ModelMap model, @PathVariable("playerId") String playerId) {
        boolean isDelete = gamesService.deletePlayer(playerId);
        if (isDelete) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
