/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.domain.request;

/**
 *
 * @author anto
 */
public class EndGameRequestBean {
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
