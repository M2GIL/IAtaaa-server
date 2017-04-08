package fr.univrouen.iataaaserver.api.game.rules;

import fr.univrouen.iataaaserver.api.TestUtil;
import fr.univrouen.iataaaserver.api.game.util.Case;
import fr.univrouen.iataaaserver.api.game.util.EnumPlayer;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseRulesTest {
    @Test
    public void getAllTopRightCornerPositionTest() {
        int i = 2;
        List<Integer> res = new ArrayList<>();
        res.add(7);
        res.add(13);
        res.add(18);
        res.add(24);
        res.add(29);

        List<Integer> test = ReverseRules.getAllTopRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }

    @Test
    public void getAllBottomLeftCornerPositionTest() {
        int i = 14;
        List<Integer> res = new ArrayList<>();
        res.add(8);
        res.add(3);

        List<Integer> test = ReverseRules.getAllBottomLeftCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }

    @Test
    public void getAllBottomRightCornerPositionTest() {
        int i = 23;
        List<Integer> res = new ArrayList<>();
        res.add(18);
        res.add(14);
        res.add(9);

        List<Integer> test = ReverseRules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }

    @Test
    public void getAllBottomRightCornerPositionReturnEmptyTest() {
        int i = 19;
        List<Integer> res = new ArrayList<>();

        List<Integer> test = ReverseRules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }

    @Test
    public void getTopLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getTopLeftCornerPosition(src, 2);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void getTopLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getTopLeftCornerPosition(src, 2);

        assertThat(result).isEqualTo(28);
    }

    @Test
    public void getTopRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getTopRightCornerPosition(src, 2);

        assertThat(result).isEqualTo(16);
    }

    @Test
    public void getTopRightCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getTopRightCornerPosition(src, 2);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void getBottomLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getBottomLeftCornerPosition(src, 2);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void getBottomLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getBottomLeftCornerPosition(src, 2);

        assertThat(result).isEqualTo(8);
    }

    @Test
    public void getBottomRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getBottomRightCornerPosition(src, 2);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void getBottomRightCornerPositionAfterJumpTest2() {
        int src = 17;
        int result = ReverseRules.getBottomRightCornerPosition(src, 2);

        assertThat(result).isEqualTo(8);
    }

    @Test
    public void getAvailableMovesStart() {
        Case[] board = TestUtil.createBoard(
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
                TestUtil.moveCaseFrom(board, 15, 20),
                TestUtil.moveCaseFrom(board, 15, 21),
                TestUtil.moveCaseFrom(board, 16, 21),
                TestUtil.moveCaseFrom(board, 16, 22),
                TestUtil.moveCaseFrom(board, 17, 22),
                TestUtil.moveCaseFrom(board, 17, 23),
                TestUtil.moveCaseFrom(board, 18, 23),
                TestUtil.moveCaseFrom(board, 18, 24),
                TestUtil.moveCaseFrom(board, 19, 24)
        );
        assertThat(ReverseRules.getAvailableMoves(board, EnumPlayer.J1))
                .containsOnlyElementsOf(expected);
    }


    @Test
    public void getAvailableMovesCaseJump1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(5, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(11, Case.BLACK_PIECE)
        );
        Case[] expected = TestUtil.moveCaseFrom(board, 5, 16);
        expected[11] = Case.EMPTY;
        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }



    @Test
    public void getAvailableMovesCaseJump1With2Choices() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(6, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(11, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(12, Case.BLACK_PIECE)
        );
        Case[] expected1 = TestUtil.moveCaseFrom(board, 6, 15);
        expected1[11] = Case.EMPTY;
        Case[] expected2 = TestUtil.moveCaseFrom(board, 6, 17);
        expected2[12] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected1, expected2);
    }


    @Test
    public void getAvailableMovesCaseJump2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(6, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(11, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(12, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(21, Case.BLACK_PIECE)
        );
        Case[] expected = TestUtil.moveCaseFrom(board, 6, 26);
        expected[11] = Case.EMPTY;
        expected[21] = Case.EMPTY;
        assertThat(ReverseRules.getAvailableMoves(board, EnumPlayer.J1))
                .containsOnly(expected);
    }

    @Test
    public void getAvailableMovesCaseJump3() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(20, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(15, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(16, Case.BLACK_QUEEN),
                new HashMap.SimpleEntry<>(26, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(25, Case.BLACK_PIECE)
        );
        Case[] expected = board.clone();
        expected[15] = Case.EMPTY;
        expected[16] = Case.EMPTY;
        expected[26] = Case.EMPTY;
        expected[25] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);
        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesQueen1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(16, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(17, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 0, 5),
                TestUtil.moveCaseFrom(board, 0, 11),
                TestUtil.moveCaseFrom(board, 16, 21),
                TestUtil.moveCaseFrom(board, 16, 22)
        );

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnlyElementsOf(expected);
    }



    @Test
    public void getAvailableMovesQueen2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(17, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 0, 5),
                TestUtil.moveCaseFrom(board, 0, 11),
                TestUtil.moveCaseFrom(board, 0, 16),
                TestUtil.moveCaseFrom(board, 0, 22),
                TestUtil.moveCaseFrom(board, 0, 27),
                TestUtil.moveCaseFrom(board, 0, 33),
                TestUtil.moveCaseFrom(board, 0, 38),
                TestUtil.moveCaseFrom(board, 0, 44),
                TestUtil.moveCaseFrom(board, 0, 49)
        );

        assertThat(ReverseRules.getAvailableMoves(board, EnumPlayer.J1))
                .containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesQueen3() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(16, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(22, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 0, 5),
                TestUtil.moveCaseFrom(board, 0, 11)
        );

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnlyElementsOf(expected);
    }


    @Test
    public void getAvailableMovesQueenJump1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(16, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 0, 22),
                TestUtil.moveCaseFrom(board, 0, 27),
                TestUtil.moveCaseFrom(board, 0, 33),
                TestUtil.moveCaseFrom(board, 0, 38),
                TestUtil.moveCaseFrom(board, 0, 44),
                TestUtil.moveCaseFrom(board, 0, 49)
        );
        expected.stream().forEach((e) -> {
            e[16] = Case.EMPTY;
        });

        assertThat(ReverseRules.getAvailableMoves(board, EnumPlayer.J1))
                .containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesQueenJump3() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(16, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(23, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 0, 18),
                TestUtil.moveCaseFrom(board, 0, 14),
                TestUtil.moveCaseFrom(board, 0, 9)
        );
        expected.stream().map((e) -> {
            e[16] = Case.EMPTY;
            return e;
        }).forEach((e) -> {
            e[23] = Case.EMPTY;
        });

        assertThat(ReverseRules.getAvailableMoves(board, EnumPlayer.J1))
                .containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesQueenJump4() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(0, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(22, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(32, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(41, Case.BLACK_PIECE)
        );
        Case[] expected = TestUtil.moveCaseFrom(board, 0, 45);

        expected[22] = Case.EMPTY;
        expected[32] = Case.EMPTY;
        expected[41] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesQueenJump5() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(13, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(22, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(32, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(33, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(23, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 13, 17),
                TestUtil.moveCaseFrom(board, 13, 12),
                TestUtil.moveCaseFrom(board, 13, 6),
                TestUtil.moveCaseFrom(board, 13, 1)
        );
        expected.stream().map((e) -> {
            e[22] = Case.EMPTY;
            return e;
        }).map((e) -> {
            e[32] = Case.EMPTY;
            return e;
        }).map((e) -> {
            e[33] = Case.EMPTY;
            return e;
        }).forEach((e) -> {
            e[23] = Case.EMPTY;
        });

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);
        assertThat(result).containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesQueenJump6() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(13, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(22, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(32, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(33, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(23, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(35, Case.BLACK_PIECE)
        );
        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 13, 17),
                TestUtil.moveCaseFrom(board, 13, 12),
                TestUtil.moveCaseFrom(board, 13, 6),
                TestUtil.moveCaseFrom(board, 13, 1)
        );
        expected.stream().map((e) -> {
            e[22] = Case.EMPTY;
            return e;
        }).map((e) -> {
            e[32] = Case.EMPTY;
            return e;
        }).map((e) -> {
            e[33] = Case.EMPTY;
            return e;
        }).forEach((e) -> {
            e[23] = Case.EMPTY;
        });
        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnlyElementsOf(expected);
    }

    @Test
    public void reverseCases1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(31, Case.BLACK_PIECE)
        );

        Case[] reverse = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(18, Case.WHITE_PIECE)
        );

        Case[] test = ReverseRules.reverseCases(board);
        assertThat(test).isEqualTo(reverse);
    }

    @Test
    public void getAvailableMovesQueenJump6ForP2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(31, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(26, Case.WHITE_PIECE)
        );
        Case[] expected = TestUtil.moveCaseFrom(board, 31, 22);
        expected[26] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J2);

        assertThat(result).containsOnly(expected);
    }
    
    @Test
    public void getAvailableMovesForP2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(5, Case.BLACK_PIECE)
        );
        Case[] expected1 = TestUtil.createBoard(); 
        Case[] expected2 = TestUtil.createBoard();
        expected1[0] = Case.BLACK_QUEEN;
        expected2[1] = Case.BLACK_QUEEN;
        List<Case[]> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
         
        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J2);
         
        assertThat(result).containsOnlyElementsOf(expected);
    }
    
    @Test
    public void getAvailableMovesTo1JumpForP2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(11, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(5, Case.WHITE_QUEEN)
        );
        Case[] expected = TestUtil.createBoard(); 
        expected[0] = Case.BLACK_QUEEN;
        
        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J2);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesDoNotMoveForward() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(29, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(33, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(39, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(43, Case.BLACK_PIECE)
        );

        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 29, 34),
                TestUtil.moveCaseFrom(board, 33, 37),
                TestUtil.moveCaseFrom(board, 33, 38)
        );

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesHaveToJump() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(20, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(25, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(31, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(46, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 25, 36);
        expected[31] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesHaveToJumpFromTooChoice() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(27, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(25, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(31, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(32, Case.BLACK_PIECE)
        );

        Case[] expected1 = TestUtil.moveCaseFrom(board, 25, 36);
        expected1[31] = Case.EMPTY;

        Case[] expected2 = TestUtil.moveCaseFrom(board, 27, 36);
        expected2[32] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected1 , expected2);
    }

    @Test
    public void getAvailableMovesChangeToQueen() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(39, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(44, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(49, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(45, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(46, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 44, 48);
        expected[48] = Case.WHITE_QUEEN;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }


    @Test
    public void getAvailableMovesHaveToJumpNotToBeQueen() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(32, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(44, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(49, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(45, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(36, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 32, 41);
        expected[36] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }


    @Test
    public void getAvailableMovesJumpAndNotBeQueen() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(36, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(42, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(43, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 36, 38);
        expected[42] = Case.EMPTY;
        expected[43] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesMultipleChoiceQueen() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(47, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(45, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(40, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(20, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(39, Case.BLACK_PIECE)
        );

        List<Case[]> expected = Arrays.asList(
                TestUtil.moveCaseFrom(board, 47, 42),
                TestUtil.moveCaseFrom(board, 47, 36),
                TestUtil.moveCaseFrom(board, 47, 31),
                TestUtil.moveCaseFrom(board, 47, 25),
                TestUtil.moveCaseFrom(board, 47, 43),
                TestUtil.moveCaseFrom(board, 47, 38),
                TestUtil.moveCaseFrom(board, 47, 34),
                TestUtil.moveCaseFrom(board, 47, 29)
        );

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnlyElementsOf(expected);
    }

    @Test
    public void getAvailableMovesMultipleJumpChoiceSameWaySize() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(2, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(6, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(7, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(12, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(23, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(33, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(42, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(44, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(34, Case.BLACK_PIECE)

        );

        Case[] expected1 = TestUtil.moveCaseFrom(board, 6, 46);
        expected1[46] = Case.WHITE_QUEEN;
        expected1[12] = Case.EMPTY;
        expected1[23] = Case.EMPTY;
        expected1[33] = Case.EMPTY;
        expected1[42] = Case.EMPTY;

        Case[] expected2 = TestUtil.moveCaseFrom(board, 6, 48);
        expected2[48] = Case.WHITE_QUEEN;
        expected2[12] = Case.EMPTY;
        expected2[23] = Case.EMPTY;
        expected2[34] = Case.EMPTY;
        expected2[44] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected1, expected2);
    }

    @Test
    public void getAvailableMovesMultipleJumpChoiceDifferentWaySize1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(2, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(6, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(7, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(15, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(18, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(28, Case.BLACK_PIECE)

        );

        Case[] expected = TestUtil.moveCaseFrom(board, 2, 33);
        expected[7] = Case.EMPTY;
        expected[18] = Case.EMPTY;
        expected[28] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesMultipleJumpChoiceDifferentWaySize2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(12, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(6, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(7, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(16, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(17, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(25, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(28, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(38, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 12, 43);
        expected[17] = Case.EMPTY;
        expected[28] = Case.EMPTY;
        expected[38] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }

    @Test
    public void getAvailableMovesMultipleJumpChoiceDifferentWaySize3() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(2, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(40, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(7, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(17, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(18, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(27, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(26, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(37, Case.BLACK_PIECE)
        );

        Case[] expected = TestUtil.moveCaseFrom(board, 2, 42);
        expected[7] = Case.EMPTY;
        expected[17] = Case.EMPTY;
        expected[27] = Case.EMPTY;
        expected[37] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected);
    }
    @Test
    public void getAvailableMovesNotDoubleWay1() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(22, Case.WHITE_QUEEN),
                new HashMap.SimpleEntry<>(35, Case.BLACK_PIECE),
                new HashMap.SimpleEntry<>(8, Case.BLACK_PIECE)
        );

        Case[] expected1 = TestUtil.moveCaseFrom(board, 22, 40);
        expected1[35] = Case.EMPTY;

        Case[] expected2 = TestUtil.moveCaseFrom(board, 22, 4);
        expected2[8] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J1);

        assertThat(result).containsOnly(expected1, expected2);
    }

    @Test
    public void getAvailableMovesNotDoubleWay2() {
        Case[] board = TestUtil.createBoard(
                new HashMap.SimpleEntry<>(13, Case.BLACK_QUEEN),
                new HashMap.SimpleEntry<>(22, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(23, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(32, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(33, Case.WHITE_PIECE),
                new HashMap.SimpleEntry<>(35, Case.WHITE_PIECE)
        );

        Case[] expected1 = TestUtil.moveCaseFrom(board, 13, 17);
        expected1[22] = Case.EMPTY;
        expected1[23] = Case.EMPTY;
        expected1[32] = Case.EMPTY;
        expected1[33] = Case.EMPTY;

        Case[] expected2 = TestUtil.moveCaseFrom(board, 13, 12);
        expected2[22] = Case.EMPTY;
        expected2[23] = Case.EMPTY;
        expected2[32] = Case.EMPTY;
        expected2[33] = Case.EMPTY;

        Case[] expected3 = TestUtil.moveCaseFrom(board, 13, 6);
        expected3[22] = Case.EMPTY;
        expected3[23] = Case.EMPTY;
        expected3[32] = Case.EMPTY;
        expected3[33] = Case.EMPTY;

        Case[] expected4 = TestUtil.moveCaseFrom(board, 13, 1);
        expected4[22] = Case.EMPTY;
        expected4[23] = Case.EMPTY;
        expected4[32] = Case.EMPTY;
        expected4[33] = Case.EMPTY;

        List<Case[]> result = ReverseRules.getAvailableMoves(board, EnumPlayer.J2);

        assertThat(result).containsOnly(expected1, expected2, expected3, expected4);
    }

}
