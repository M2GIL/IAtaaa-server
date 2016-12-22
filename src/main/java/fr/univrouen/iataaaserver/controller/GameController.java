package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.PlayerType;
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
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/games")
public class GameController {

    @Autowired
    private GamesService gamesService;
    
    // A supprimer
    private GameController() {
        PlayerBean p1 = new PlayerBean();
        p1.setIp("127.0.0.1");
        p1.setPort(8080);
        p1.setName("ia1");
        p1.setToken("toto");
        p1.setDifficulty(Difficulty.MEDIUM);
        p1.setType(PlayerType.IA);
        
        PlayerBean p2 = new PlayerBean();
        p2.setIp("127.0.0.1");
        p2.setPort(8080);
        p2.setName("ia2");
        p2.setToken("tata");
        p2.setDifficulty(Difficulty.MEDIUM);
        p2.setType(PlayerType.IA);
        
        gamesService.subscribePlayer(p1);
        gamesService.subscribePlayer(p2);
        
        GameBean g = new GameBean();
        g.setGameID("gameOne");
        String[] players = new String[]{"ia1", "ia2"};
        gamesService.createGame(g);
    }

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
    

    @RequestMapping(value = { "/player/subscribe" }, method = RequestMethod.POST)
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