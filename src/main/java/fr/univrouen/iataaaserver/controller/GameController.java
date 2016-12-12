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
import org.springframework.web.bind.annotation.RestController;
import fr.univrouen.iataaaserver.services.GamesService;

@RestController
@RequestMapping("api/games")
public class GameController {

    @Autowired
    private GamesService gamesService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gamesService.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Board<Case>> getGame(ModelMap model, @PathVariable("gameID") String gameID) {
        Board<Case> board = gamesService.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/subscribe" }, method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> subscribe(ModelMap model, @RequestBody PlayerBean playerBean) {
        StatusResponse st = gamesService.subscribePlayer(playerBean);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> create(ModelMap model, @RequestBody GameBean gameBean) {
        StatusResponse st = gamesService.createGame(gameBean);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
}
