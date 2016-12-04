package fr.univ.iataaaserver.web.rest.game;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    /*
    private final GameManager gameManager;
    private Board board;
    
    public GameController() {
        gameManager = new GameManagerImpl();
    }

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
