package fr.univrouen.iataaaserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by z3ddycus on 04/12/16.
 * @param <T>
 */
public class Board<T> {
    
    @JsonProperty
    private final ArrayList<T> board;

    public Board(T[] array) {
        board = new ArrayList<>(array.length);
        Collections.addAll(board, array);
    }

    public Board(Board<T> board) {
        this.board = new ArrayList<>();
        this.board.addAll(board.getBoard());
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
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        Iterator<T> it = board.iterator();
        if (it.hasNext()) {
            res.append(it.next());
        }
        while(it.hasNext()) {
            res.append(" | ");
            res.append(it.next());
        }
        res.append("]");
        return res.toString();
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
