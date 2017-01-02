package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Response;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.PlayerType;
import fr.univrouen.iataaaserver.entities.bean.GameBean;
import fr.univrouen.iataaaserver.entities.bean.PlayGameBean;
import fr.univrouen.iataaaserver.entities.bean.PlayerBean;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
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
import fr.univrouen.iataaaserver.services.GamesServiceImpl;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/")
public class GameController {

    //@Autowired
    private GamesService gamesService;
    
    private GameController() {
        
       GamesService gamesService = new GamesServiceImpl();
           
       PlayerBean p1 = new PlayerBean();
       p1.setUrl("http://localhost:9999/DameRESTJava");
       p1.setName("ia1");
       p1.setToken("toto");
       p1.setDifficulty(Difficulty.MEDIUM);
       p1.setType(PlayerType.IA);
       
       PlayerBean p2 = new PlayerBean();
       p2.setUrl("http://localhost:9999/DameRESTJava");
       p2.setName("ia2");
       p2.setToken("tata");
       p2.setDifficulty(Difficulty.MEDIUM);
       p2.setType(PlayerType.IA);
       
       System.out.println("gamesService : " + gamesService);
       gamesService.subscribePlayer(p1);
       gamesService.subscribePlayer(p2);
       
       GameBean g = new GameBean();
       g.setGameID("gameOne");
       String[] players = new String[]{"ia1", "ia2"};
       g.setPlayers(players);
       gamesService.createGame(g);
       
   }
    
    /******/
    //TEST
    @RequestMapping(value = { "test" }, method = RequestMethod.GET)
    public ResponseEntity<PlayGameBean> test() {
        PlayGameBean t = new PlayGameBean();
        t.setDifficulty(Difficulty.MEDIUM);
        t.setPlayer(EnumPlayer.DRAW);
        t.setToken("token");
        Case[] cases = new Case[50];
        for (int i = 0; i < 50; ++ i) {
            cases[i] = Case.EMPTY;
        }
        t.setBoard(cases);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
    
    /********/
   
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
        Response<GameBean> response = gamesService.createGame(gameBean);
        StatusResponse st = response.getStatus();
        HttpStatus httpS = HttpStatus.OK;
        if (st != StatusResponse.OK) {
            httpS = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, httpS);
    }

    @RequestMapping(value = { "player" }, method = RequestMethod.POST)
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
    public ResponseEntity<Response<List<String>>> findAllPlayers(ModelMap model) {
        List<String> players = gamesService.getPlayers();
        Response<List<String>> response = new Response<>();
        response.setContent(players);
        response.setStatus(StatusResponse.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
