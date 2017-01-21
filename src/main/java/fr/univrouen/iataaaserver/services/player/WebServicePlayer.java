package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.request.*;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class WebServicePlayer implements Player {
    private final String token;
    private String id;
    private final String url;
    private Difficulty difficulty;
    private String gameId;

    public WebServicePlayer(String token, String url, Difficulty difficulty) {
        this.token = token;
        this.difficulty = difficulty;
        this.url = url;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public StatusService getStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/status")
                .queryParam("token", token);
        
        HttpEntity<StatusRequestBean> requestEntity = new HttpEntity<StatusRequestBean>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StatusResponseBean> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, StatusResponseBean.class);
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
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/games/start")
                .queryParam("token", token)
                .queryParam("difficulty", difficulty.toString())
                .queryParam("player", player.toString());

        HttpEntity<StatusRequestBean> requestEntity = new HttpEntity<StatusRequestBean>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StartGameResponseBean> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, StartGameResponseBean.class);
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
        String boardString = "";
        for (Case c : boardGame.getBoard()) {
            boardString += c.getValue();
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/games/play/" + gameId)
                .queryParam("token", token)
                .queryParam("difficulty", difficulty.toString())
                .queryParam("player", player.toString())
                .queryParam("board", boardString);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlayGameBean> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<PlayGameBean> response = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, PlayGameBean.class);
            PlayGameBean playGameBean = response.getBody();
            return new Board<Case>(playGameBean.getBoard());

        } catch (RestClientException e) {
           throw new Exception();
        }
    }

    @Override
    public void endGame(EndGameCase endType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
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

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/games/end/" + gameId)
                .queryParam("token", token)
                .queryParam("winner", winner.toString())
                .queryParam("code", CodeEndGame.CLASSICAL.toString());
        
        HttpEntity<EndGameRequestBean> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<EndGameResponseBean> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, EndGameResponseBean.class);
            EndGameResponseBean endGameBean = result.getBody();
            if (endGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
        } catch (HttpStatusCodeException e) {
            throw new Exception();
        }
    }
}
