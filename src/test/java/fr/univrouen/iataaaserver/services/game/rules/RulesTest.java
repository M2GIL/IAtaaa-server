package fr.univrouen.iataaaserver.services.game.rules;

import fr.univrouen.iataaaserver.services.game.rules.Rules;
import fr.univrouen.iataaaserver.domain.game.Board;
import fr.univrouen.iataaaserver.domain.game.Case;
import fr.univrouen.iataaaserver.domain.request.EnumPlayer;
import fr.univrouen.iataaaserver.services.util.TestUtil;
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
