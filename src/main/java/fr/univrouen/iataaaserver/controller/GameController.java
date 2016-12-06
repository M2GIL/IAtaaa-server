package fr.univrouen.iataaaserver.controller;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.StatusGameCreation;
import fr.univrouen.iataaaserver.services.GamesServiceImpl;
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
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GamesService gamesService;
    
    @RequestMapping(value = { "/{message}" }, method = RequestMethod.GET)
    public String coucou(@PathVariable("message") String message) {
        return message;
    }
    
    @RequestMapping(value = { "/{gameID}" }, method = RequestMethod.POST)
    public ResponseEntity<StatusGameCreation> create(ModelMap model, 
        @PathVariable("gameID") String gameID, @RequestBody String iaName, 
        @RequestBody String iaIP1, @RequestBody int iaPort1, 
        @RequestBody String iaIP2, @RequestBody int iaPort2,
        @RequestBody Difficulty difficulty) {
        StatusGameCreation st = gamesService.createGame(gameID, iaName, iaIP1, 
                iaPort1, iaIP2, iaPort2, difficulty);
        
        return new ResponseEntity<>(st, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Board> getGame(ModelMap model, @PathVariable("gameID") String gameID) {
        Board board = gamesService.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gamesService.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
