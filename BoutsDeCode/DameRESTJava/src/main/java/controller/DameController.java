package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Board;

@RestController
public class DameController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "hello Dame";
    }
    
    @RequestMapping(value="/basicBoard", method=RequestMethod.GET)
    public char[] getStatus() {
        Board b = new Board();
        return b.getBoard();
    }
}
