/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.domain.request.GameBean;
import fr.univrouen.iataaaserver.domain.request.PlayerBean;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author m_s info
 */
@Controller
public class SynchronizeWebsocketGame {

    @MessageMapping("/connectUser")
    @SendTo("/api-webSocket/connectUserNotif")
    public String connectUser() {
        return "NEW USER CONNECTED";
    }

    @MessageMapping("/requestPlay/{playerID}")
    @SendTo("/api-webSocket/requestPlayNotif/{playerID}")
    public PlayerBean requestToPlay(PlayerBean playerRequest, @DestinationVariable String playerID) {
        return playerRequest;
    }

    @MessageMapping("/startGame/{playerID}")
    @SendTo("/api-webSocket/startGameNotif/{playerID}")
    public GameBean startGame(GameBean gameBean, @DestinationVariable String playerID) {
        return gameBean;
    }

    @MessageMapping("/playGame/{gameID}")
    @SendTo("/api-webSocket/playGameNotif/{gameID}")
    public GameBean playGame(@DestinationVariable GameBean gameBean) {
        return gameBean;
    }

    @MessageMapping("/endGame/{gameID}")
    @SendTo("/api-webSocket/endGameNotif")
    public String endGame(@DestinationVariable GameBean gameBean) {
        return "end game";
    }

}
