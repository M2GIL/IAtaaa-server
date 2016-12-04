package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.CODEENDGAME;
import model.JOUEUR;

public class EntreeIaGamesEndId {

    private String token;
    private JOUEUR winner;
    private CODEENDGAME code;
    
    @JsonCreator
    public EntreeIaGamesEndId(@JsonProperty("token")String t, @JsonProperty("winner")JOUEUR w, @JsonProperty("code")CODEENDGAME c) {
        token = t;
        winner = w;
        code = c;
    }

    public String getToken() {
        return token;
    }

    public JOUEUR getWinner() {
        return winner;
    }

    public CODEENDGAME getCode() {
        return code;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setWinner(JOUEUR winner) {
        this.winner = winner;
    }

    public void setCode(CODEENDGAME code) {
        this.code = code;
    }

}
