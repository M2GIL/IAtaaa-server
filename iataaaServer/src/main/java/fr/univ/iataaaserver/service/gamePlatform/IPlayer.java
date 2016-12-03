package fr.univ.iataaaserver.service.gamePlatform;

import fr.univ.iataaaserver.service.gamePlatform.Exception.BusyException;
import fr.univ.iataaaserver.service.gamePlatform.util.*;

public interface IPlayer {

    StatutService getStatut();
    void startGame(Token idGame, Difficulty difficulty) throws BusyException;
    void PlayGame(Token idToken, Difficulty difficulty, Board boardGame, EnumPlayer player) throws Exception;
    void endGame(Token idToken, String idGame, EndGameCase endType) throws Exception;



}
