package fr.univrouen.iataaaserver.controller;


import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.services.GameManager;
import fr.univrouen.iataaaserver.services.GameManagerImpl;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameManager gameManager;
    
    public GameController() {
        gameManager = new GameManagerImpl();
    }
    
    @RequestMapping(value = { "/coucou" }, method = RequestMethod.GET)
    public String coucou(ModelMap model) {
        return "Coucou";
    }
    
    @RequestMapping(value = { "/games/create/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Boolean> create(ModelMap model, @PathVariable("gameID") String gameID) {
        boolean isCreated = gameManager.createGame(gameID);
        return new ResponseEntity<>(isCreated, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/games/{gameID}" }, method = RequestMethod.GET)
    public ResponseEntity<Board> getGame(ModelMap model, @PathVariable("gameID") String gameID) {
        Board board = gameManager.getBoard(gameID);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/games" }, method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getGameNames(ModelMap model) {
        Set<String> names = gameManager.getGameNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/game/subscribe/{iaName}" }, method = RequestMethod.POST)
    public String subscribe(ModelMap model, 
        @PathVariable("iaName") String iaName, @RequestBody String ip, 
        @RequestBody int port, @RequestBody Difficulty difficulty) {
        
        return String.format("Ceci est un test");
    }
    
    
    
    /*
    @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
    public String index(ModelMap model) {
       return String.format("Ceci est un test");
    }
    
    
    @RequestMapping(value = { "/create/" + "{id}"}, method = RequestMethod.GET)
    public String createGame(ModelMap model, @PathVariable("id") String id) {
       gameManager.createGame(id);
       return String.format(id);
    }
    
    @RequestMapping(value = { "/" + "{id}"}, method = RequestMethod.GET)
    public ResponseEntity<Board> getGame(ModelMap model, @PathVariable("id") String id) {
       Board board = gameManager.getGame(id);
       return new ResponseEntity<>(board, HttpStatus.OK);
    }
    
    
    // Test pour recevoir du json en post. Ca fonctionne.
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<Board> setBoard(@RequestBody Board b) {
        board = b;
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
    */
    
}
