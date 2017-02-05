package fr.univrouen.iataaaserver.game;

import fr.univrouen.iataaaserver.domain.EndGameCase;
import fr.univrouen.iataaaserver.domain.Token;
import fr.univrouen.iataaaserver.dto.Difficulty;
import fr.univrouen.iataaaserver.dto.EnumPlayer;
import fr.univrouen.iataaaserver.domain.observable.Observable;
import fr.univrouen.iataaaserver.util.exception.BusyException;
import fr.univrouen.iataaaserver.player.Player;

public interface GameRunner extends Observable {
    
    String EVENT_NEW_MOVE = "event new move";
    String EVENT_END_GAME = "party over";
    String EVENT_START_GAME = "starting block";

    Token getId();
    EndGameCase getStatus();
    Game getGame();
    Player getPlayer(EnumPlayer player);
    Difficulty getDifficulty(EnumPlayer player);
    void startGame() throws BusyException;
}
