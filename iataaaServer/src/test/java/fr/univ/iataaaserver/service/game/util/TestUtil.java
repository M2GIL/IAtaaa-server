package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.service.game.game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestUtil {
    private TestUtil() {
        
    }
    
    public static Case[] moveCaseFrom(Case[] board, int sourcePosition, int targetPosition) {
        Case[] result = Arrays.copyOf(board, board.length);
        result[targetPosition] = result[sourcePosition];
        result[sourcePosition] = Case.EMPTY;
        return result;
    }
    
    public static Case[] createBoard(Map.Entry<Integer, Case>... pieces) {
        Case[] board = new Case[Game.PIECE_SIZE];
        for (int i = 0; i < board.length; ++i) {
            board[i] = Case.EMPTY;
        }
        Arrays.asList(pieces).forEach(entry -> {
            board[entry.getKey()] = entry.getValue();
        });
        return board;
    }
    
    public static List<Integer> getIACases(Case[] pieces) {
        List<Integer> positions = new ArrayList<>(15);
        for (int i = 0; i < pieces.length; ++i) {
            if (pieces[i] == Case.WHITE_PIECE || pieces[i] == Case.WHITE_QUEEN) {
                positions.add(i);
            }
        }
        return positions;
    }
}
