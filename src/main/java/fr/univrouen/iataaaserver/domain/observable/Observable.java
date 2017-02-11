package fr.univrouen.iataaaserver.domain.observable;

import java.beans.PropertyChangeListener;

public interface Observable {

// REQUESTS
    /**
     * the list of listener
     * @return 
     */
    PropertyChangeListener[] getPropertyChangeListeners();

// COMMANDS
     /**
     * Add a listener
     * @param name
     * @param listener
     * @pre
     *      listener != null
     */
    void addPropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     * Remove a listener
     * @param name
     * @param listener
     * @pre
     *      listener != null
     */
    void removePropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     * Add a listener
     * @param listener
     * @pre
     *      listener != null
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove a listener
     * @param listener
     * @pre
     *      listener != null
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

}
