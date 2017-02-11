package fr.univrouen.iataaaserver.dto;

public class EndGameRequest {
    private String token;
    private EnumPlayer winner;
    private CodeEndGame code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EnumPlayer getWinner() {
        return winner;
    }

    public void setWinner(EnumPlayer winner) {
        this.winner = winner;
    }

    public CodeEndGame getCode() {
        return code;
    }

    public void setCode(CodeEndGame code) {
        this.code = code;
    }
    
    
}
