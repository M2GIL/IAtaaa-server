package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.DIFFICULTE;
import model.JOUEUR;

public class EntreeRetourIaGamePlayId {
    private String token;
    private DIFFICULTE difficulty;
    private JOUEUR player;
    private char[] board;
    
    @JsonCreator
    public EntreeRetourIaGamePlayId (@JsonProperty("token")String t, @JsonProperty("difficulty")DIFFICULTE d, @JsonProperty("player")JOUEUR p, @JsonProperty("board")char[] b) {
        token = t;
        difficulty = d;
        player = p;
        board = b;
    }
    
    public String getToken() {
        return token;
    }
    public DIFFICULTE getDifficulty() {
        return difficulty;
    }
    public JOUEUR getPlayer() {
        return player;
    }

    public char[] getBoard() {
        return board;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDifficulty(DIFFICULTE difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayer(JOUEUR player) {
        this.player = player;
    }

    public void setBoard(char[] board) {
        this.board = board;
    }
}
