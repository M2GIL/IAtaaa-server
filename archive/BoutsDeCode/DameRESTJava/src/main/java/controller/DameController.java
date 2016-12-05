package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Board;

@RestController
public class DameController {

    private static String URL ="http://localhost:8080/rest-ws";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "hello Dame";
    }
    
    /*
     * Retourne un objet Board serializé en Json
     */
    @RequestMapping(value="/basicBoard", method=RequestMethod.GET)
    public Board getBoard() {
        //RestTemplate rest = new RestTemplate();
        char[] board = {'1','2','3'};
        Board b = new Board(board);
        return b;
    }
    
    /*
     * Attend un flux Json en entrée, 
     * le convertie en objet Board Java et le 
     * retourne de nouveau en tant que Json 
     */
    
    @RequestMapping(value = "/sendAndReturnBoardJson", method = RequestMethod.POST)
    public Board update(@RequestBody Board b) {
        // TODO: call persistence layer to update
        return new ResponseEntity<Board>(b, HttpStatus.OK).getBody();
    }
    
    /*
     * Va chercher un flux json à l'url donnée, le convertie en Board
     * et le retourne en le reconvertisant en Json
     */
    @RequestMapping(value="/TestRecupData", method=RequestMethod.GET)
    public Board getBoardSend() {
        RestTemplate rest = new RestTemplate();
        Board b2 = rest.getForObject(URL + "/basicBoard", Board.class);
        return b2;
    }

}
