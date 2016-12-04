/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.domain.game.util.observable;

import java.beans.PropertyChangeListener;

public interface Observable {

// REQUESTS
    /**
     * the list of listener
     */
    PropertyChangeListener[] getPropertyChangeListeners();

// COMMANDS
     /**
     * Add a listener
     * @pre
     *      listener != null
     */
    void addPropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     * Remove a listener
     * @pre
     *      listener != null
     */
    void removePropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     * Add a listener
     * @pre
     *      listener != null
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove a listener
     * @pre
     *      listener != null
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

}
