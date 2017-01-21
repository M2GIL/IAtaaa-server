package fr.univrouen.iataaaserver.controller;

import fr.univrouen.iataaaserver.domain.game.Token;
import fr.univrouen.iataaaserver.services.GamesService;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import fr.univrouen.iataaaserver.services.game.GameRunner;
import fr.univrouen.iataaaserver.services.game.GameRunnerImpl;
import fr.univrouen.iataaaserver.services.player.RandomizeCPUPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    GamesService gamesService;

    @Autowired
    SynchronizeWebsocketGame synchronizeWebsocketGame;

    @RequestMapping("/")
    public void launchGame() {
        GameRunner g = new GameRunnerImpl(new Token("ahahah"), new RandomizeCPUPlayer("J1"), new RandomizeCPUPlayer("J2"));
        synchronizeWebsocketGame.registerGame(g);
        try {
            g.startGame();
        } catch (BusyException e) {
            e.printStackTrace();
        }
    }
}