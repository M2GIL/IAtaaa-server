package fr.univrouen.iataaaserver.api.game.webSocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.api.game.GameRunner;
import fr.univrouen.iataaaserver.api.game.player.WebServicePlayer;
import fr.univrouen.iataaaserver.api.game.util.Board;
import fr.univrouen.iataaaserver.api.game.util.Case;
import fr.univrouen.iataaaserver.api.game.util.EndGameCase;
import fr.univrouen.iataaaserver.api.game.util.EnumPlayer;

public class WebSocketGameBean {
    private final static int J1 = 0;
    private final static int J2 = 1;
    
    @JsonProperty
    private String id;

    @JsonProperty
    private PlayerDTO[] players = new PlayerDTO[2];

    @JsonProperty
    private Board<Case> board;

    @JsonProperty
    private boolean whiteTurn;

    @JsonProperty
    private EndGameCase status;

    public WebSocketGameBean (GameRunner gameRunner) {
        this.id = gameRunner.getId().getId();
        this.board = gameRunner.getGame().getPieces();
        this.whiteTurn = gameRunner.getGame().getCurrentPlayer() == EnumPlayer.J1;
        this.status = gameRunner.getStatus();

        PlayerDTO p1 = new PlayerDTO();
        p1.setName(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setDifficulty(gameRunner.getPlayer(EnumPlayer.J1).getDifficulty());
        p1.setToken(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setType(PlayerType.IA);
        if (gameRunner.getPlayer(EnumPlayer.J1) instanceof WebServicePlayer) {
            p1.setUrl(((WebServicePlayer) gameRunner.getPlayer(EnumPlayer.J1)).getUrl());
        } else {
            p1.setUrl("urlJ1");
        }
        this.players[J1] = p1;

        PlayerDTO p2 = new PlayerDTO();
        p2.setName(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setDifficulty(gameRunner.getPlayer(EnumPlayer.J2).getDifficulty());
        p2.setToken(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setType(PlayerType.IA);
        if (gameRunner.getPlayer(EnumPlayer.J2) instanceof WebServicePlayer) {
            p2.setUrl(((WebServicePlayer) gameRunner.getPlayer(EnumPlayer.J2)).getUrl());
        } else {
            p2.setUrl("urlJ2");
        }
        this.players[J2] = p2;
    }

    public static int getJ1() {
        return J1;
    }

    public static int getJ2() {
        return J2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerDTO[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerDTO[] players) {
        this.players = players;
    }

    public Board<Case> getBoard() {
        return board;
    }

    public void setBoard(Board<Case> board) {
        this.board = board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public EndGameCase getStatus() {
        return status;
    }

    public void setStatus(EndGameCase status) {
        this.status = status;
    }
}
