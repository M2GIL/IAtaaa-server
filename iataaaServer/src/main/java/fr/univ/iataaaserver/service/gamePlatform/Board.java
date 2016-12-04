package fr.univ.iataaaserver.service.gamePlatform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z3ddycus on 04/12/16.
 */
public class Board<T> {
    private ArrayList<T> board;

    public Board(T[] array) {
        board = new ArrayList<T>(array.length);
        for (T element : array) {
            board.add(element);
        }
    }

    public List<T> getBoard() {
        return board;
    }

    public T[] toArray() {
        return (T[]) board.toArray();
    }
}
