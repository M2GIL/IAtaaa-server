package fr.univ.iataaaserver.service.game.util;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.univ.iataaaserver.IataaaServerApp;
import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Case;
import fr.univ.iataaaserver.domain.game.EnumPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IataaaServerApp.class)

public class RulesTest {

    @Test
    public void getAvailableMovesStart() {
        Case[] cases = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(16, Case.BLACK_PIECE)
        );

        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(cases, 16, 20),
                TestUtil.moveCaseFrom(cases, 16, 21)
        );
        
        List<Board<Case>> expectedBoard = new ArrayList<>();
        expected.stream().forEach((b) -> {
            expectedBoard.add(new Board<Case>(b));
        });
        
        Board<Case> board = new Board(cases);

        List<Board<Case>> res = Rules.getAvalaibleMoves(board, EnumPlayer.PLAYER_2);
        assertThat(res).containsOnlyElementsOf(expectedBoard);    
    }

}
