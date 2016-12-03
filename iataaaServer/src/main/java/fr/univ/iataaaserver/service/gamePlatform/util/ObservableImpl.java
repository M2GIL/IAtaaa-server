/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.gamePlatform.util;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ObservableImpl implements Observable {
// ATTRIBUTES

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

// REQUESTS
    @Override
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return support.getPropertyChangeListeners();
    }

// COMMANDS
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener l) {
    	support.addPropertyChangeListener(name, l);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener l) {
    	support.removePropertyChangeListener(name, l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        support.removePropertyChangeListener(l);
    }

// PROTECTED
    /**
     * Execute a firePropertyChange on all Listeners
     */
    protected void firePropertyChange(String property, Object oldValue, Object newValue) {
        support.firePropertyChange(property, oldValue, newValue);
    }
}
