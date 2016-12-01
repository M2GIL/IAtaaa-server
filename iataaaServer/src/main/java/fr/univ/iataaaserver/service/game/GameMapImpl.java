/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.GameBean;
import fr.univ.iataaaserver.domain.game.Player;
import fr.univ.iataaaserver.service.game.util.Contract;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author anto
 */
public class GameMapImpl implements GameMap {
    
     private final Map<String, GameBean> games;
    
    public GameMapImpl() {
        games = new HashMap<>();
    }

    @Override
    public boolean addGame(GameBean game) {
        Contract.checkArgument(game == null, "game should not be null.");
        String key = game.getId();
    
        return games.put(key, game) != null;
    }
    
    @Override
    public boolean addPlayer(String gameId, Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        games.clear();
    }
    
    public Set<String> getAllId() {
        return games.keySet();
    }

    @Override
    public GameBean getGame(String key) {
        return games.get(key);
    }

    @Override
    public boolean removeGame(GameBean game) {
        Contract.checkArgument(game == null, "game should not be null.");
        String key = game.getId();
        
        return games.remove(key) != null;
    }
    
}
