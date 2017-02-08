package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.dto.*;
import fr.univrouen.iataaaserver.game.GameRunner;
import fr.univrouen.iataaaserver.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/")
public class GameController {

    @Autowired
    private GamesService gamesService;
    
    @GetMapping(value = { "games" })
    public ResponseEntity<Response<Set<String>>> getGameNames() {
        Set<String> names = gamesService.getGameNames();
        Response<Set<String>> response = new Response<>();
        response.setContent(names);
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @GetMapping(value = { "games/{gameID}" })
    public ResponseEntity<Response<WebSocketGameBean>> findGameById(@PathVariable("gameID") String gameID) {
        Response<WebSocketGameBean> response = new Response<>();
        GameRunner game = gamesService.getGame(gameID);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.setContent(new WebSocketGameBean(game));
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = { "games/{gameId}" })
    public ResponseEntity<Void> deleteGame(@PathVariable("gameId") String gameId) {
        boolean isDelete = gamesService.deleteGame(gameId);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = { "games" })
    public ResponseEntity<Response<GameDTO>> createGame(@RequestBody GameDTO gameBean) {
        Response<GameDTO> response = gamesService.createGame(gameBean); 
        if (response.getStatus() == StatusType.OK) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CONFLICT
            );
        }
    }

    @PostMapping(value = { "players" })
    public ResponseEntity<Response<String>> createPlayer(@RequestBody PlayerDTO playerBean) {
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
    
    @GetMapping(value = { "players" })
    public ResponseEntity<Response<List<PlayerDTO>>> findAllPlayers() {
        List<PlayerDTO> players = gamesService.getPlayers();
        Response<List<PlayerDTO>> response = new Response<>();
        response.setContent(players);
        response.setStatus(StatusType.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value= {"players/{name}"})
    public ResponseEntity<Response<PlayerDTO>> findPlayer(@PathVariable String name) {
        Response<PlayerDTO> response = new Response<>();
        response.setContent(gamesService.getPlayer(name));
        response.setStatus(StatusType.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = { "players/{playerId}" })
    public ResponseEntity<Void> deletePlayer(@PathVariable("playerId") String playerId) {
        boolean isDelete = gamesService.deletePlayer(playerId);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
