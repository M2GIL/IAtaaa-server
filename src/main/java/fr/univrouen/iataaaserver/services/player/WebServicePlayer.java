package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.domain.request.EnumPlayer;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.request.Difficulty;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.request.EndGameRequestBean;
import fr.univrouen.iataaaserver.domain.request.EndGameResponseBean;
import fr.univrouen.iataaaserver.domain.request.PlayGameBean;
import fr.univrouen.iataaaserver.domain.request.StartGameRequestBean;
import fr.univrouen.iataaaserver.domain.request.StartGameResponseBean;
import fr.univrouen.iataaaserver.domain.request.StatusRequestBean;
import fr.univrouen.iataaaserver.domain.request.StatusResponseBean;
import fr.univrouen.iataaaserver.domain.request.StatusService;
import fr.univrouen.iataaaserver.domain.request.CodeEndGame;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class WebServicePlayer implements Player {

    private final String token;
    private String id;
    private final String url;
    private Difficulty difficulty;
    private String gameId;
    
    

    public WebServicePlayer(String token, String url, Difficulty difficulty) {
        //this.token = token;
        this.token = "toto";
        this.difficulty = difficulty;
        this.url = url;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public StatusService getStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        StatusRequestBean statusRequestBean = new StatusRequestBean();
        statusRequestBean.setToken(token);
        
        HttpEntity<StatusRequestBean> requestEntity = new HttpEntity<>(statusRequestBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StatusResponseBean> result = restTemplate.exchange(url + "/ai/status", HttpMethod.POST, requestEntity, StatusResponseBean.class);
            StatusResponseBean statusBean = result.getBody();
            return statusBean.getStatus();

        }
        catch (RestClientException e) {        
            e.printStackTrace();
        }
          return StatusService.BUSY;
    }

    @Override
    public void startGame(EnumPlayer player) throws BusyException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        StartGameRequestBean startGameRequestBean = new StartGameRequestBean();
        startGameRequestBean.setDifficulty(difficulty);
        startGameRequestBean.setPlayer(player);
        startGameRequestBean.setToken(token);
        
        HttpEntity<StartGameRequestBean> requestEntity = new HttpEntity<>(startGameRequestBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StartGameResponseBean> result = restTemplate.exchange(url + "/ai/games/start/", HttpMethod.POST, requestEntity, StartGameResponseBean.class);
            StartGameResponseBean startGameBean = result.getBody();
            if (startGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
            gameId = startGameBean.getGame_id();
            
        } catch (RestClientException e ) {   
            throw new BusyException();
        } 
    }

    @Override
    public Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {
        PlayGameBean playGameBean = new PlayGameBean();
        playGameBean.setBoard(boardGame.toArray());
        playGameBean.setDifficulty(difficulty);
        playGameBean.setPlayer(player);
        playGameBean.setToken(token);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json"));
        HttpEntity<PlayGameBean> requestEntity = new HttpEntity<>(playGameBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        
        try {
            Case[] cases = playGameBean.getBoard();

            ResponseEntity<PlayGameBean> response = restTemplate.exchange(url + "/ai/games/play/" + gameId, HttpMethod.POST, requestEntity, PlayGameBean.class);
            playGameBean = response.getBody();
            return new Board<Case>(playGameBean.getBoard());

        } catch (RestClientException e) {
           throw new Exception();
        }
    }

    @Override
    public void endGame(EndGameCase endType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        EndGameRequestBean endGameRequestBean = new EndGameRequestBean();
        endGameRequestBean.setCode(CodeEndGame.CLASSICAL);
        endGameRequestBean.setToken(token);
        
        EnumPlayer winner;
        switch (endType) {
            case PLAYER_1_VICTORY :
                winner = EnumPlayer.J1;
                break;
            case PLAYER_2_VICTORY :
                winner = EnumPlayer.J2;
                break;
            case DRAW :
                winner = EnumPlayer.DRAW;
                break;
            default :
                winner = EnumPlayer.DRAW;
        }      
        endGameRequestBean.setWinner(winner);
        
        HttpEntity<EndGameRequestBean> requestEntity = new HttpEntity<>(endGameRequestBean, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<EndGameResponseBean> result = restTemplate.exchange(url + "/ai/games/end/" + id, HttpMethod.POST, requestEntity, EndGameResponseBean.class);
            EndGameResponseBean endGameBean = result.getBody();
            if (endGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
        } catch (HttpStatusCodeException e) {
            throw new Exception();
        }
    }
}
