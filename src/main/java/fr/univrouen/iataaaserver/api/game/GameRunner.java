package fr.univrouen.iataaaserver.api.game;

import fr.univrouen.iataaaserver.api.domain.util.Difficulty;
import fr.univrouen.iataaaserver.api.game.exception.BusyException;
import fr.univrouen.iataaaserver.api.game.player.Player;
import fr.univrouen.iataaaserver.api.game.util.EndGameCase;
import fr.univrouen.iataaaserver.api.game.util.EnumPlayer;
import fr.univrouen.iataaaserver.api.game.util.Token;
import fr.univrouen.iataaaserver.api.game.util.observable.Observable;

import java.io.IOException;
import java.net.UnknownServiceException;

public interface GameRunner extends Observable {
    
    String EVENT_NEW_MOVE = "event new move";
    String EVENT_END_GAME = "party over";
    String EVENT_START_GAME = "starting block";

    Token getId();
    EndGameCase getStatus();
    void getPlayerStatus() throws BusyException, IOException;
    Game getGame();
    Player getPlayer(EnumPlayer player);
    Difficulty getDifficulty(EnumPlayer player);
    void startGame() throws BusyException, UnknownServiceException;
    void abort();
}
