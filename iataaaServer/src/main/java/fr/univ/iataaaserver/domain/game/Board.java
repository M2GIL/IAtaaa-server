package fr.univ.iataaaserver.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board<?> other = (Board<?>) obj;
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return true;
    }
    
    
}
