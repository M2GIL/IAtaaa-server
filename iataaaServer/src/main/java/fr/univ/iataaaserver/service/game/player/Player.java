package fr.univ.iataaaserver.service.game.player;

import fr.univ.iataaaserver.domain.game.*;
import fr.univ.iataaaserver.service.game.exception.BusyException;

import java.io.IOException;

public interface Player {

    StatusService getStatus();
    void startGame(Token idGame, Difficulty difficulty, EnumPlayer player) throws BusyException;
    Board<Case> PlayGame(Difficulty difficulty, Board<Case> boardGame, EnumPlayer player) throws IOException, Exception;
    void endGame(Token idGame, EndGameCase endType) throws IOException, Exception;

}
