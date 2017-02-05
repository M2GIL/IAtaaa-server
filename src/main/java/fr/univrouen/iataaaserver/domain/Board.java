package fr.univrouen.iataaaserver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by z3ddycus on 04/12/16.
 * @param <T>
 */
public class Board<T> {
    
    @JsonProperty
    private final ArrayList<T> list;

    public Board(T[] array) {
        list = new ArrayList<>(array.length);
        Collections.addAll(list, array);
    }

    public Board(Board<T> cases) {
        this.list = new ArrayList<>();
        this.list.addAll(cases.getCases());
    }

    public List<T> getCases() {
        return list;
    }

    public Case[] toArray() {
        int sizeBoard = list.size();
        Case[] cases = new Case[sizeBoard];
        for (int i = 0; i < sizeBoard; ++ i) {
            cases[i] = (Case) list.get(i);
        }
        return cases;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        Iterator<T> it = list.iterator();
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
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }


}
