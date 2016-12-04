package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.STATUS;

public class RetourIaStatus {

    private String token;
    private STATUS status;
    
    @JsonCreator
    public RetourIaStatus(@JsonProperty("token")String t, @JsonProperty("status")STATUS s) {
        token = t;
        status = s;
    }

    public String getToken() {
        return token;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
