package fr.univ.iataaaserver.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by z3ddycus on 04/12/16.
 * @param <T>
 */
public class Board<T> {
    private final ArrayList<T> board;

    public Board(T[] array) {
        board = new ArrayList<>(array.length);
        Collections.addAll(board, array);
    }

    public Board(Board<T> board) {
        this.board = new ArrayList<>(board.size());
        this.board.addAll(board.getBoard());
    }

    public List<T> getBoard() {
        return board;
    }

    public int size() {
        return board.size();
    }

    public T[] toArray() {
        return (T[]) board.toArray();
    }
}
