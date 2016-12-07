package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.PlayerType;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.StatusGameCreation;
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
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @Autowired
    private GamesService gamesService;
    
    @MessageMapping("/")
    @SendTo("/games/gameName") 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gamesService.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
    
    @MessageMapping("/{gameID}")
    @SendTo("/games/gameBegin") 
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<Board<Case>> getGame(ModelMap model, @PathVariable("gameID") String gameID) {
        Board<Case> board = gamesService.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    
    @MessageMapping("/create")
    @SendTo("/games/gameStatus") 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StatusGameCreation> create(ModelMap model, @RequestBody GameBean gameBean) {
        StatusGameCreation st = gamesService.createGame(gameBean);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
}
