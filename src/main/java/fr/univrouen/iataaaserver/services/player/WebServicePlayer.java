package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.entities.bean.EndGameBean;
import fr.univrouen.iataaaserver.entities.bean.PlayGameBean;
import fr.univrouen.iataaaserver.entities.bean.StartGameBean;
import fr.univrouen.iataaaserver.entities.bean.StatusBean;
import fr.univrouen.iataaaserver.entities.status.StatusService;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        StatusBean statusBean = new StatusBean();
        HttpEntity<StatusBean> requestEntity = new HttpEntity<>(statusBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
           ResponseEntity<StatusBean> result = restTemplate.exchange("" + url + ":" + port + "/ai/status", HttpMethod.POST, requestEntity, StatusBean.class);
            statusBean = result.getBody();
            statusBean.setToken(token);
            return statusBean.getStatus();

        }
        catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                e.getMessage();
                
            }
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                e.getMessage();
            }
            
        }
          return statusBean.getStatus();
    }

    @Override
    public void startGame(EnumPlayer player) throws BusyException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        StartGameBean startGameBean = new StartGameBean();
        HttpEntity<StartGameBean> requestEntity = new HttpEntity<>(startGameBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StartGameBean> result = restTemplate.exchange("" + url + ":" + port + "/ai/games/start", HttpMethod.POST, requestEntity, StartGameBean.class);

        try {
            startGameBean = result.getBody();
            startGameBean.setToken(token);
            if (startGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                e.getMessage();
            }
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                e.getMessage();
            }
        }

    }

    @Override
    public Board<Case> PlayGame(Token game_id, Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        PlayGameBean playGameBean = new PlayGameBean();
        HttpEntity<PlayGameBean> requestEntity = new HttpEntity<>(playGameBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<PlayGameBean> response = restTemplate.exchange("" + url + ":" + port + "/ai/games/play/" +token, HttpMethod.POST, requestEntity, PlayGameBean.class);
        try {
            playGameBean = response.getBody();
            playGameBean.setToken(token);
            return playGameBean.getBoard();

        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                return null;
            }
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                return null;
            }
        }
        return boardGame;
    }

    @Override
    public void endGame(EndGameCase endType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        EndGameBean endGameBean = new EndGameBean();
        HttpEntity<EndGameBean> requestEntity = new HttpEntity<>(endGameBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EndGameBean> result = restTemplate.exchange("" + url + ":" + port + "/ai/games/end/" + id, HttpMethod.POST, requestEntity, EndGameBean.class);

        try {
            endGameBean = result.getBody();
            endGameBean.setToken(token);
            if (endGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                e.getMessage();
            }
            if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                e.getMessage();
            }
        }
    }
}
