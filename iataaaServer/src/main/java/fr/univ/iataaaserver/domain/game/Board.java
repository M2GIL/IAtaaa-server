package fr.univ.iataaaserver.domain.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z3ddycus on 04/12/16.
 * @param <T>
 */
public class Board<T> {
    private final ArrayList<T> board;

    public Board(T[] array) {
        board = new ArrayList<>(array.length);
        for (T element : array) {
            board.add(element);
        }
    }

    public List<T> getBoard() {
        return board;
    }

    public Case[] toArray() {
        int sizeBoard = board.size();
        Case[] cases = new Case[sizeBoard];
        for (int i = 0; i < sizeBoard; ++ i) {
            cases[i] = (Case) board.get(i);
        }
        return cases;
    }
}
