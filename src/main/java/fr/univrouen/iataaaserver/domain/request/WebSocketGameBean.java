package fr.univrouen.iataaaserver.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.game.EndGameCase;
import fr.univrouen.iataaaserver.domain.game.PlayerType;
import fr.univrouen.iataaaserver.services.game.GameRunner;


public class WebSocketGameBean {
    private final static int J1 = 0;
    private final static int J2 = 1;

    public WebSocketGameBean (GameRunner gameRunner) {
        this.id = gameRunner.getId().getId();
        this.board = gameRunner.getGame().getPieces();
        this.whiteTurn = gameRunner.getGame().getCurrentPlayer() == EnumPlayer.J1;
        this.status = gameRunner.getStatus();

        PlayerBean p1 = new PlayerBean();
        p1.setName(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setDifficulty(gameRunner.getPlayer(EnumPlayer.J1).getDifficulty());
        p1.setToken(gameRunner.getPlayer(EnumPlayer.J1).getName());
        p1.setType(PlayerType.IA);
        //p1.setUrl(((WebServicePlayer) (gameRunner.getPlayer(EnumPlayer.J1))).getUrl());
        p1.setUrl("urlJ1"); // test
        this.players[J1] = p1;

        PlayerBean p2 = new PlayerBean();
        p2.setName(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setDifficulty(gameRunner.getPlayer(EnumPlayer.J2).getDifficulty());
        p2.setToken(gameRunner.getPlayer(EnumPlayer.J2).getName());
        p2.setType(PlayerType.IA);
        //p2.setUrl(((WebServicePlayer) (gameRunner.getPlayer(EnumPlayer.J2))).getUrl());
        p2.setUrl("url J2"); // test
        this.players[J2] = p2;
    }
    
    @JsonProperty
    private String id;

    @JsonProperty
    private PlayerBean[] players = new PlayerBean[2];

    @JsonProperty
    private Board<Case> board;

    @JsonProperty
    private boolean whiteTurn;

    @JsonProperty
    private EndGameCase status;

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

    public PlayerBean[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerBean[] players) {
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
