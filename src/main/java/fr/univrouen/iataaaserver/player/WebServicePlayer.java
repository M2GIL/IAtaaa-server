package fr.univrouen.iataaaserver.player;

import fr.univrouen.iataaaserver.dto.EnumPlayer;
import fr.univrouen.iataaaserver.dto.EndGameResponse;
import fr.univrouen.iataaaserver.dto.Difficulty;
import fr.univrouen.iataaaserver.dto.EndGameRequest;
import fr.univrouen.iataaaserver.dto.StatusRequest;
import fr.univrouen.iataaaserver.dto.CodeEndGame;
import fr.univrouen.iataaaserver.dto.StartGameResponse;
import fr.univrouen.iataaaserver.dto.MoveDTO;
import fr.univrouen.iataaaserver.dto.StatusService;
import fr.univrouen.iataaaserver.dto.StatusResponse;
import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.domain.EndGameCase;
import fr.univrouen.iataaaserver.util.exception.BusyException;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;

public class WebServicePlayer implements Player {
    private final String token;
    private final String url;
    private Difficulty difficulty;
    private String gameId;

    public WebServicePlayer(String token, String url, Difficulty difficulty) {
        this.token = "toto";
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
        
        HttpEntity<StatusRequest> requestEntity = new HttpEntity<StatusRequest>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StatusResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, StatusResponse.class);
            StatusResponse statusBean = result.getBody();
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

        HttpEntity<StatusRequest> requestEntity = new HttpEntity<StatusRequest>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<StartGameResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, StartGameResponse.class);
            StartGameResponse startGameBean = result.getBody();
            if (startGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
            gameId = startGameBean.getGame_id();
            
        } catch (RestClientException e ) {   
            throw new BusyException();
        } 
    }

    @Override
    public Board<Case> playGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception {
        String boardString = "";
        for (Case c : boardGame.getCases()) {
            boardString += c.getValue();
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/games/play/" + gameId)
                .queryParam("token", token)
                .queryParam("difficulty", difficulty.toString())
                .queryParam("player", player.toString())
                .queryParam("board", boardString);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MoveDTO> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<MoveDTO> response = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, MoveDTO.class);
            MoveDTO playGameBean = response.getBody();
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
        
        HttpEntity<EndGameRequest> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<EndGameResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, EndGameResponse.class);
            EndGameResponse endGameBean = result.getBody();
            if (endGameBean.getStatus().equals("BUSY")) {
                throw new BusyException();
            }
        } catch (HttpStatusCodeException e) {
            throw new Exception();
        }
    }
}
