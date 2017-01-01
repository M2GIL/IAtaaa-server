package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.game.Game;

import java.io.IOException;
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
        StatusService result=res.getForObject(""+url+":"+port+"/ai/games/start", StatusService.class);
        if(result.equals("BUSY")){
         throw new BusyException();
        }
     
    }

    @Override
    public Board<Case> PlayGame(Token game_id,Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {
        RestTemplate res=new RestTemplate();
        Game result=res.getForObject(""+url+":"+port+"/ai/games/play/"+game_id, Game.class);
        result.move(boardGame);
        return result.getPieces();
        }
     

    @Override
    public void endGame(EndGameCase endType) throws Exception {
        
        RestTemplate res=new RestTemplate();
        StatusService result=res.getForObject(""+url+":"+port+"/ai/games/end", StatusService.class);
        
        if(result.equals("BUSY")){
           
         throw new BusyException();
         
        }

    }
}
