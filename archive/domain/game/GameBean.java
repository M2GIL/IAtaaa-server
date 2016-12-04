/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.domain.game;

import fr.univ.iataaaserver.service.game.GameService;

/**
 *
 * @author anto
 */
public class GameBean {
    
    private String id;
    private GameService game;
    private Player player1;
    private Player player2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameService getGame() {
        return game;
    }

    public void setGame(GameService game) {
        this.game = game;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
}
