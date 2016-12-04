package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import fr.univ.iataaaserver.service.game.game.Game;
import java.util.ArrayList;
import java.util.List;

public class Rules {
    
    private static final int FIRST_POSITION_OF_LAST_LINE = Game.CASE_NB_OF_LINE 
        * (Game.LINE_NB - 1);
    private static final int LAST_POSITION_OF_FIRST_LINE = Game.CASE_NB_OF_LINE 
        - 1;
    
    
    private static final List<Board<Case>> AVALAIBLE_MOVES = new ArrayList<>();
    
    private Rules() {
    }

    public static List<Board<Case>> getAvalaibleMoves(Board board, EnumPlayer p) {
       
        return AVALAIBLE_MOVES;
    }
    
}
