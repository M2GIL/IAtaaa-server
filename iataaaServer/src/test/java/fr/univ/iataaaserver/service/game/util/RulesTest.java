package fr.univ.iataaaserver.service.game.util;


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
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IataaaServerApp.class)

public class RulesTest {

    @Test
    public void getAllTopRightCornerPositionTest() {
        int i = 2;
        List<Integer> res = new ArrayList<>();
        res.add(7);
        res.add(13);
        res.add(18);
        res.add(24);
        res.add(29);
        
        List<Integer> test = Rules.getAllTopRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomLeftCornerPositionTest() {
        int i = 14;
        List<Integer> res = new ArrayList<>();
        res.add(8);
        res.add(3);
        
        List<Integer> test = Rules.getAllBottomLeftCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomRightCornerPositionTest() {
        int i = 23;
        List<Integer> res = new ArrayList<>();
        res.add(18);
        res.add(14);
        res.add(9);
        
        List<Integer> test = Rules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomRightCornerPositionReturnEmptyTest() {
        int i = 19;
        List<Integer> res = new ArrayList<>();
        
        List<Integer> test = Rules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getTopLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = Rules.getTopLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getTopLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = Rules.getTopLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(28);
    }
    
    @Test
    public void getTopRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = Rules.getTopRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(16);
    }
    
    @Test
    public void getTopRightCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = Rules.getTopRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = Rules.getBottomLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = Rules.getBottomLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(8);
    }
    
    @Test
    public void getBottomRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = Rules.getBottomRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomRightCornerPositionAfterJumpTest2() {
        int src = 17;
        int result = Rules.getBottomRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(8);
    } 
    
    @Test
    public void getAvailableMovesStart() {
        Case[] cases = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(15, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(16, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(17, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(18, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(19, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(30, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(31, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(32, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(33, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(34, Case.BLACK_PIECE)
        );

        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(cases, 15, 20),
                TestUtil.moveCaseFrom(cases, 15, 21),
                TestUtil.moveCaseFrom(cases, 16, 21),
                TestUtil.moveCaseFrom(cases, 16, 22),
                TestUtil.moveCaseFrom(cases, 17, 22),
                TestUtil.moveCaseFrom(cases, 17, 23),
                TestUtil.moveCaseFrom(cases, 18, 23),
                TestUtil.moveCaseFrom(cases, 18, 24),
                TestUtil.moveCaseFrom(cases, 19, 24)
        );
        List<Board<Case>> expectedBoard = new ArrayList<>();
        expected.stream().forEach((b) -> {
            expectedBoard.add(new Board<Case>(b));
        });
        
        Board<Case> board = new Board(cases);

        List<Board<Case>> res = 
            Rules.getAvalaibleMoves(board, EnumPlayer.PLAYER_1);
        
        assertThat(res).containsOnlyElementsOf(expectedBoard);    
    }

}
