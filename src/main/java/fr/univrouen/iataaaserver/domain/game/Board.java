package fr.univrouen.iataaaserver.domain.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by z3ddycus on 04/12/16.
 * @param <T>
 */
public class Board<T> {
    
    @JsonProperty
    private final ArrayList<T> cases;

    public Board(T[] array) {
        cases = new ArrayList<>(array.length);
        Collections.addAll(cases, array);
    }

    public Board(Board<T> cases) {
        this.cases = new ArrayList<>();
        this.cases.addAll(cases.getCases());
    }

    public List<T> getCases() {
        return cases;
    }

    public Case[] toArray() {
        int sizeBoard = cases.size();
        Case[] cases = new Case[sizeBoard];
        for (int i = 0; i < sizeBoard; ++ i) {
            cases[i] = (Case) this.cases.get(i);
        }
        return cases;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        Iterator<T> it = cases.iterator();
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
        if (!Objects.equals(this.cases, other.cases)) {
            return false;
        }
        return true;
    }


}
