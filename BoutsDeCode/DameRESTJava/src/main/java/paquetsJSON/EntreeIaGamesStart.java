package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.DIFFICULTE;
import model.JOUEUR;

public class EntreeIaGamesStart {

    private String token;
    private DIFFICULTE difficulty;
    private JOUEUR player;
    
    @JsonCreator
    public EntreeIaGamesStart(@JsonProperty("token")String t, @JsonProperty("difficulty")DIFFICULTE d, @JsonProperty("player")JOUEUR p) {
        token = t;
        difficulty = d;
        player = p;
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

    public void setToken(String token) {
        this.token = token;
    }

    public void setDifficulty(DIFFICULTE difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayer(JOUEUR player) {
        this.player = player;
    }
}
