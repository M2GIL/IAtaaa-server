package fr.univrouen.iataaaserver.api.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.game.util.EnumPlayer;

public class StartGameRequest {
    
    @JsonProperty
    private String token;
    @JsonProperty
    private Difficulty difficulty;
    @JsonProperty
    private EnumPlayer player;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public EnumPlayer getPlayer() {
        return player;
    }

    public void setPlayer(EnumPlayer player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "StartGameRequestBean{" + "token=" + token + ", difficulty=" + difficulty + ", player=" + player + '}';
    }

    
    
    
}
