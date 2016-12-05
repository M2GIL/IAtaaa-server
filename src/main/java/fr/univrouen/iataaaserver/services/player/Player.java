package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.Difficulty;
import fr.univrouen.iataaaserver.entities.EndGameCase;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.entities.StatusService;
import fr.univrouen.iataaaserver.entities.Token;
import fr.univrouen.iataaaserver.services.exception.BusyException;
import java.io.IOException;

public interface Player {

    StatusService getStatus();
    void startGame(Token idGame, Difficulty difficulty, EnumPlayer player) throws BusyException;
    Board<Case> PlayGame(Difficulty difficulty, Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(Token idGame, EndGameCase endType) throws IOException, Exception;

}
