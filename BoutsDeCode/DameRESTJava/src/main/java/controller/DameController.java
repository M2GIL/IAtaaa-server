package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import model.STATUS;
import paquetsJSON.EntreeIaGamesEndId;
import paquetsJSON.EntreeIaGamesStart;
import paquetsJSON.EntreeIaStatus;
import paquetsJSON.EntreeRetourIaGamePlayId;
import paquetsJSON.RetourIaGamesEndId;
import paquetsJSON.RetourIaGamesStart;
import paquetsJSON.RetourIaStatus;


@RestController
public class DameController {

    @RequestMapping(value = "/ai/status", method = RequestMethod.POST)
    public RetourIaStatus status(@RequestBody EntreeIaStatus token) {
        //TODO Demander l'état d'une IA
        //test de retour
        return new RetourIaStatus(token.getToken(), STATUS.available);
    }
    

    @RequestMapping(value="/ai/games/start/", method = RequestMethod.POST)
    public RetourIaGamesStart beginGame(@RequestBody EntreeIaGamesStart e) {
        //TODO Prévenir les IA qu’une partie les concernant va commencer
        //test de retour
        return new RetourIaGamesStart(e.getToken(), STATUS.available, "testTokenPartie");
    }
    
    @RequestMapping(value = "/ai/games/play/{game_id:.+}", method = RequestMethod.POST)
    public EntreeRetourIaGamePlayId getMove(@PathVariable("game_id") String game_id, @RequestBody EntreeRetourIaGamePlayId e) {
        // TODO:  Demande de jouer un coup sur un plateau donnée sur une partie
        //test de retour
        return new EntreeRetourIaGamePlayId(e.getToken(), e.getDifficulty(), e.getPlayer(), e.getBoard());
    }
    
    @RequestMapping(value="/ai/games/end/{game_id:.+}", method=RequestMethod.POST)
    public RetourIaGamesEndId endGame(@PathVariable("game_id") String game_id, @RequestBody EntreeIaGamesEndId e) {
        // TODO: Annoncer la fin de partie avec code explicatif pour gérer les parties avortées et les parties standards.
        //test de retour
        return new RetourIaGamesEndId (game_id + " " + e.getToken(), STATUS.available);
    }

}
