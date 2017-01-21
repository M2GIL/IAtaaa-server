package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.game.PlayerType;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.player.WebServicePlayer;


public class WebSocketGameBean {
    private final static int J1 = 0;
    private final static int J2 = 1;

    public WebSocketGameBean (GameRunner gameRunner) {
        this.endGameCode = gameRunner.getStatus();
        this.board = gameRunner.getGame().toString();
        this.endGameCode = gameRunner.getStatus();
        this.whiteToPlay = gameRunner.getGame().getCurrentPlayer() == EnumPlayer.J1;
        this.gameId = gameRunner.getId().getId();

        PlayerBean p1 = new PlayerBean();
        p1.setName(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setDifficulty(gameRunner.getPlayer(EnumPlayer.J1).getDifficulty());
        p1.setToken(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setType(PlayerType.IA);
        p1.setUrl(((WebServicePlayer) (gameRunner.getPlayer(EnumPlayer.J1))).getUrl());
        this.players[J1] = p1;

        PlayerBean p2 = new PlayerBean();
        p2.setName(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setDifficulty(gameRunner.getPlayer(EnumPlayer.J2).getDifficulty());
        p2.setToken(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setType(PlayerType.IA);
        p2.setUrl(((WebServicePlayer) (gameRunner.getPlayer(EnumPlayer.J2))).getUrl());
        this.players[J2] = p2;
    }
    
    @JsonProperty
    private String gameId;
    @JsonProperty
    private PlayerBean[] players = new PlayerBean[2];
    @JsonProperty
    private String board;
    @JsonProperty
    private boolean whiteToPlay;
    @JsonProperty
    private EndGameCase endGameCode;

    public static int getJ1() {
        return J1;
    }

    public static int getJ2() {
        return J2;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public PlayerBean[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerBean[] players) {
        this.players = players;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public boolean isWhiteToPlay() {
        return whiteToPlay;
    }

    public void setWhiteToPlay(boolean whiteToPlay) {
        this.whiteToPlay = whiteToPlay;
    }

    public EndGameCase getEndGameCode() {
        return endGameCode;
    }

    public void setEndGameCode(EndGameCase endGameCode) {
        this.endGameCode = endGameCode;
    }
}
