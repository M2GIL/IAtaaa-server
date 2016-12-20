package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fr.univrouen.iataaaserver.services.GamesService;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/")
public class GameController {

    @Autowired
    private GamesService gamesService;

    @RequestMapping(value = { "games" }, method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gamesService.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "game/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Board<Case>> findGameById(ModelMap model, @PathVariable("gameID") String gameID) {
        Board<Case> board = gamesService.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "game" }, method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> createGame(ModelMap model, @RequestBody GameBean gameBean) {
        StatusResponse st = gamesService.createGame(gameBean);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
    

    @RequestMapping(value = { "player" }, method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> createPlayer(ModelMap model, @RequestBody PlayerBean playerBean) {
        StatusResponse st = gamesService.subscribePlayer(playerBean);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "players" }, method = RequestMethod.GET)
    public ResponseEntity<List<String>> findAllPlayers(ModelMap model) {
        List<String> players = gamesService.getPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
    
}