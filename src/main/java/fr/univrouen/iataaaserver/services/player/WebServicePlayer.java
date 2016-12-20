package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.entities.status.StatusResponse;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.game.Game;
import fr.univrouen.iataaaserver.services.game.GameImpl;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;

import java.io.IOException;
import java.util.Timer;
import org.springframework.web.client.RestTemplate;

/**
 * Created by z3ddycus on 03/12/16.
 */
public class WebServicePlayer implements Player {

    private final String token;
    private String id;
    private final String url;
    private final int port;
    private Difficulty difficulty;

    public WebServicePlayer(String token, String url, int port, Difficulty difficulty) {
        this.token = token;
        this.difficulty = difficulty;
        this.url = url;
        this.port = port;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public StatusService getStatus() {
    
        if(token !=null){
       return StatusService.BUSY;
        }
        else{
        
        return StatusService.AVAILABLE;
        }
    }

    @Override
    public void startGame(EnumPlayer player) throws BusyException {
  
        RestTemplate res=new RestTemplate();
        String result=res.getForObject(""+url+":"+port+"/ai/games/start", String.class);
        if(result.equals("BUSY")){
         throw new BusyException();
        }
        
        
        /*
            RestTemplate req = new ...
            String res = req.post(...);
            Status st = res.getStatus();
            if (st != Status.Available) {
                return new throw
        }
        
        */
         
    }

    @Override
    public Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {
        
            return boardGame;

        }
    
    
    

    @Override
    public void endGame(EndGameCase endType) throws Exception {
        
        RestTemplate res=new RestTemplate();
        String result=res.getForObject(""+url+":"+port+"/ai/games/end", String.class);
        if(result.equals("BUSY")){
         throw new BusyException();
        }

    }
}
