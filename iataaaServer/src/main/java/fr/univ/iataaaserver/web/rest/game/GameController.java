package fr.univ.iataaaserver.web.rest.game;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {


    @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
    public String index(ModelMap model) {
       return String.format("Ceci est un test");
    }
    
}
