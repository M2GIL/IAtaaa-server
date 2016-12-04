package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.STATUS;

public class RetourIaGamesStart {

    private String token;
    private STATUS status;
    private String tokenPartie;
    
    @JsonCreator
    public RetourIaGamesStart(@JsonProperty("token")String t, @JsonProperty("status")STATUS s, @JsonProperty("tokenPartie")String tp) {
        token = t;
        status = s;
        tokenPartie = tp;
    }

    public String getToken() {
        return token;
    }

    public STATUS getStatus() {
        return status;
    }

    public String getTokenPartie() {
        return tokenPartie;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void setTokenPartie(String tokenPartie) {
        this.tokenPartie = tokenPartie;
    }
}
