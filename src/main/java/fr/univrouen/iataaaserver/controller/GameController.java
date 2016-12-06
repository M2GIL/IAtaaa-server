package fr.univrouen.iataaaserver.controller;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.services.GameManager;
import fr.univrouen.iataaaserver.services.GameManagerImpl;
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

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private final GameManager gameManager;
    
    public GameController() {
        gameManager = new GameManagerImpl();
    }
    
    @RequestMapping(value = { "/coucou" }, method = RequestMethod.GET)
    public String coucou(ModelMap model) {
        return "Coucou";
    }
    
    @RequestMapping(value = { "/{gameID}" }, method = RequestMethod.POST)
    public String create(ModelMap model, 
        @PathVariable("gameID") String gameID, @RequestBody String iaName, 
        @RequestBody String iaIP1, @RequestBody int iaPort1, 
        @RequestBody String iaIP2, @RequestBody int iaPort2,
        @RequestBody Difficulty difficulty) {
        
        return String.format("Ceci est un test");
    }
    
    @RequestMapping(value = { "/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Board> getGame(ModelMap model, @PathVariable("gameID") String gameID) {
        Board board = gameManager.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gameManager.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
