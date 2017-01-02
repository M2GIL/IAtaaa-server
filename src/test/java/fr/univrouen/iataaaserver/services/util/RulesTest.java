package fr.univrouen.iataaaserver.services.util;

import fr.univrouen.iataaaserver.entities.Board;
import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;


public class RulesTest {

    @Test
    public void getAvailableMovesStart() {
        List<Board<Case>> expectedBoard = new ArrayList<>();

        Case[] cases = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(45, Case.WHITE_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(cases, 45, 40);
        expectedBoard.add(new Board<Case>(expected));

        Board<Case> board = new Board(cases);
        List<Board<Case>> res = Rules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(res).containsOnlyElementsOf(expectedBoard);
    }

}
