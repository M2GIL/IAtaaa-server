package fr.univrouen.iataaaserver.player;

import fr.univrouen.iataaaserver.domain.Board;
import fr.univrouen.iataaaserver.domain.Case;
import fr.univrouen.iataaaserver.domain.EndGameCase;
import fr.univrouen.iataaaserver.dto.*;
import fr.univrouen.iataaaserver.util.exception.BusyException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.UnknownServiceException;

import static fr.univrouen.iataaaserver.dto.StatusService.BUSY;

public class WebServicePlayer implements Player {
    private final String token;
    private final String name;
    private final String url;
    private Difficulty difficulty;
    private String gameId;

    public WebServicePlayer(String name, String token, String url, Difficulty difficulty) {
        this.token = "toto";
        this.name = name;
        this.difficulty = difficulty;
        this.url = url;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public StatusService getStatus() throws IOException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/status")
                    .queryParam("token", token);

            HttpEntity<StatusRequest> requestEntity = new HttpEntity<StatusRequest>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<StatusResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                    HttpMethod.GET, requestEntity, StatusResponse.class);
            StatusResponse statusBean = result.getBody();
            if (statusBean.getStatus() == null) {
                throw new IllegalArgumentException();
            }
            return statusBean.getStatus();
        } catch (Exception e) {
            throw new IOException();
        }
    }

    @Override
    public void startGame(EnumPlayer player) throws BusyException, UnknownServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/ai/games/start")
                .queryParam("token", token)
                .queryParam("difficulty", difficulty.toString())
                .queryParam("player", player.toString());

        HttpEntity<StatusRequest> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<StartGameResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET, requestEntity, StartGameResponse.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            throw new UnknownServiceException();
        }
        StartGameResponse startGameBean = result.getBody();
        if (startGameBean.getStatus().equals(BUSY)) {
            throw new BusyException();
        }
        gameId = startGameBean.getGame_id();
    }

    @Override
    public Board<Case> playGame(Board<Case> boardGame, EnumPlayer player) throws UnknownServiceException {
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

        ResponseEntity<MoveDTO> response = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET, requestEntity, MoveDTO.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new UnknownServiceException();
        }
        MoveDTO playGameBean = response.getBody();
        return new Board<>(playGameBean.getBoard());

    }

    @Override
    public void endGame(EndGameCase endType) throws UnknownServiceException, BusyException {
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

        ResponseEntity<EndGameResponse> result = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET, requestEntity, EndGameResponse.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            throw new UnknownServiceException();
        }
        EndGameResponse endGameBean = result.getBody();
        if (endGameBean.getStatus().equals(BUSY)) {
            throw new BusyException();
        }
    }
}
