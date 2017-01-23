package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.request.Response;
import fr.univrouen.iataaaserver.domain.request.GameBean;
import fr.univrouen.iataaaserver.domain.request.PlayerBean;
import fr.univrouen.iataaaserver.domain.request.StatusResponse;
import fr.univrouen.iataaaserver.services.player.Player;
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
        response.setStatus(StatusResponse.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "game/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Response<Board<Case>>> findGameById(ModelMap model, @PathVariable("gameID") String gameID) {
        Board<Case> board = gamesService.getBoard(gameID);
        Response<Board<Case>> response = new Response<>();
        response.setContent(board);
        response.setStatus(StatusResponse.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = { "game" }, method = RequestMethod.POST)
    public ResponseEntity<Response<GameBean>> createGame(ModelMap model, @RequestBody GameBean gameBean) {
        return new ResponseEntity<>(
                gamesService.createGame(gameBean),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = { "players" }, method = RequestMethod.POST)
    public ResponseEntity<Response<String>> createPlayer(ModelMap model, @RequestBody PlayerBean playerBean) {
        Response<PlayerBean> responsePlayer = gamesService.subscribePlayer(playerBean);
        PlayerBean p = responsePlayer.getContent();
        String token = null;
        HttpStatus httpS = HttpStatus.BAD_REQUEST;
        if (p != null) {
            token = p.getToken();
            httpS = HttpStatus.OK;
        }
        
        if (responsePlayer.getStatus() != StatusResponse.OK) {
            httpS = HttpStatus.BAD_REQUEST;
        }
        
        Response<String> response = new Response<>();
        response.setContent(token);
        response.setStatus(responsePlayer.getStatus());
        return new ResponseEntity<>(response, httpS);
    }
    
    @RequestMapping(value = { "players" }, method = RequestMethod.GET)
    public ResponseEntity<Response<List<PlayerBean>>> findAllPlayers(ModelMap model) {
        List<PlayerBean> players = gamesService.getPlayers();
        Response<List<PlayerBean>> response = new Response<>();
        response.setContent(players);
        response.setStatus(StatusResponse.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value= {"players/{name}"}, method = RequestMethod.GET)
    public ResponseEntity<Response<PlayerBean>> findPlayer(@PathVariable String name) {
        Response<PlayerBean> response = new Response<>();
        response.setContent(gamesService.getPlayer(name));
        response.setStatus(StatusResponse.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
