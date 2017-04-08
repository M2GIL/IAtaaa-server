package fr.univrouen.iataaaserver.api.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.univrouen.iataaaserver.api.game.util.StatusService;

public class StartGameResponse {

    @JsonProperty
    private String token;
    @JsonProperty
    private StatusService status;
    @JsonProperty
    private String game_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StatusService getStatus() {
        return status;
    }

    public void setStatus(StatusService status) {
        this.status = status;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return "StartGameResponseBean{" + "token=" + token + ", status=" + status + ", game_id=" + game_id + '}';
    }

    

}
