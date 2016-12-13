package fr.univrouen.iataaaserver.services.player;

import fr.univrouen.iataaaserver.entities.*;
import fr.univrouen.iataaaserver.services.exception.BusyException;

import java.io.IOException;

public interface Player {
    void setDifficulty(Difficulty difficulty);
    StatusService getStatus();
    void startGame(Token idGame, EnumPlayer player) throws BusyException;
    Board<Case> PlayGame(Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(Token idGame, EndGameCase endType) throws IOException, Exception;

}
