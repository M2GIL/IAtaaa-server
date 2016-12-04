/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.domain.game;

/**
 *
 * @author anto
 */
public class Player {
    
    private String ip;
    private int port;
    private String name;
    private PlayerEnum playerNumber;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerEnum getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(PlayerEnum playerNumber) {
        this.playerNumber = playerNumber;
    }
    
}
