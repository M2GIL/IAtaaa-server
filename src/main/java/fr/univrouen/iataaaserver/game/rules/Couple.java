package fr.univrouen.iataaaserver.game.rules;

/**
 *
 * @author Anthony Godin
 * @param <X>
 * @param <Y>
 */
public class Couple<X, Y> {
    
    private X first;
    private Y second;
    
    public Couple(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }
    
    
}
