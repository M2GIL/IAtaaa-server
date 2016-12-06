/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author anto
 */
public class GameBean {
    
    @JsonProperty  
    private String gameID;
    @JsonProperty  
    private String iaToken1; 
    @JsonProperty  
    private String iaName1; 
    @JsonProperty  
    private String iaIP1; 
    @JsonProperty  
    private int iaPort1; 
    @JsonProperty  
    private Difficulty iaDifficulty1;
    @JsonProperty  
    private String iaToken2; 
    @JsonProperty  
    private String iaName2; 
    @JsonProperty  
    private String iaIP2;
    @JsonProperty  
    private int iaPort2;
    @JsonProperty  
    private Difficulty iaDifficulty2;

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getIaToken1() {
        return iaToken1;
    }

    public void setIaToken1(String iaToken1) {
        this.iaToken1 = iaToken1;
    }

    public String getIaName1() {
        return iaName1;
    }

    public void setIaName1(String iaName1) {
        this.iaName1 = iaName1;
    }

    public String getIaIP1() {
        return iaIP1;
    }

    public void setIaIP1(String iaIP1) {
        this.iaIP1 = iaIP1;
    }

    public int getIaPort1() {
        return iaPort1;
    }

    public void setIaPort1(int iaPort1) {
        this.iaPort1 = iaPort1;
    }
    
    public Difficulty getIaDifficulty1() {
        return iaDifficulty1;
    }

    public void setIaDifficulty1(Difficulty iaDifficulty1) {
        this.iaDifficulty1 = iaDifficulty1;
    }

    public String getIaToken2() {
        return iaToken2;
    }

    public void setIaToken2(String iaToken2) {
        this.iaToken2 = iaToken2;
    }

    public String getIaName2() {
        return iaName2;
    }

    public void setIaName2(String iaName2) {
        this.iaName2 = iaName2;
    }

    public String getIaIP2() {
        return iaIP2;
    }

    public void setIaIP2(String iaIP2) {
        this.iaIP2 = iaIP2;
    }

    public int getIaPort2() {
        return iaPort2;
    }

    public void setIaPort2(int iaPort2) {
        this.iaPort2 = iaPort2;
    }

    public Difficulty getIaDifficulty2() {
        return iaDifficulty2;
    }

    public void setIaDifficulty2(Difficulty iaDifficulty2) {
        this.iaDifficulty2 = iaDifficulty2;
    }
    
}
