/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game;

import fr.univ.iataaaserver.domain.game.Board;
import fr.univ.iataaaserver.domain.game.Piece;
import fr.univ.iataaaserver.domain.game.PlayerEnum;
import fr.univ.iataaaserver.service.game.util.Rules;
import java.util.List;

/**
 *
 * @author anto
 */
public class GameServiceImpl implements GameService {
    
    private final Board board;
    private List<Piece[]> avalaiblePiecesList;
    private PlayerEnum looser;

// CONSTRUCTOR
    /**
     * Initialise un tableau de jeu de dames avec 40 pions.
     */
    public GameServiceImpl() {
        board = new Board();
        initializeGame();
    }
    
    public Board getBoard() {
        return board;
    }

    public PlayerEnum getCurrentPlayer() {
        return board.getCurrentPlayer();
    }
    
    public boolean move(Piece[] pieces, PlayerEnum player) {
        boolean res = false;
        if (player == getCurrentPlayer() && isInGame()) {
            if (avalaiblePiecesList.contains(pieces)) {
                board.setPieces(pieces);
                changePlayer();
                if (lostGame(board.getCurrentPlayer())) {
                    looser = getCurrentPlayer();           
                } else {
                    calculateAvalaibleMoves();
                }
                res = true;
            }
        }
        return res;
    }
    
    public boolean isInGame() {
        return looser != null;
    }
    
    public PlayerEnum getWinner() {
        if (looser == PlayerEnum.PLAYER_1) {
            return PlayerEnum.PLAYER_2;
        }
        return PlayerEnum.PLAYER_1;
    }
    
    public PlayerEnum getLooser() {
        return looser;
    }
    
// PRIVATE
    
    // Rempli un jeu de dames de 20 pieces blanches et noir.
    // Le reste des pièces sont vides.
    private void initializeGame() {
        Piece[] pieces = new Piece[GameService.PIECE_SIZE];
        for (int i = 0; i < GameService.CASE_NB_OF_LINE * 4; ++i) {
            pieces[i] = Piece.BLACK_PIECE;
        }
        
        for (int i = GameService.CASE_NB_OF_LINE * 4; 
            i < GameService.CASE_NB_OF_LINE * 6; ++i) {
            pieces[i] = Piece.EMPTY;
        }
        
        for (int i = GameService.CASE_NB_OF_LINE * 6; 
            i < GameService.PIECE_SIZE; ++i) {
            pieces[i] = Piece.WHITE_PIECE;
        }
        board.setPieces(pieces);
        board.setCurrentPlayer(PlayerEnum.PLAYER_1);
    }
    
    private int reversePieceIndice(int indice) {
        assert indice > 0 && indice < 49; 
        return indice + (45 - 10 * (indice / 5)); 
    }
    
    private Piece[] reversePieces(Piece[] pieces) {
        Piece[] reversePieces = new Piece[GameService.PIECE_SIZE];
        for (int i = 0; i < GameService.PIECE_SIZE; ++i) {
            reversePieces[reversePieceIndice(i)] = oppositeColor(pieces[i]);
        }
        
        return reversePieces;
    }
    
    private Piece oppositeColor(Piece piece) {
        Piece opposite;
        switch (piece) {
            case WHITE_PIECE :
                opposite = Piece.BLACK_PIECE;
                break;
            case BLACK_PIECE :
                opposite = Piece.WHITE_PIECE;
                break;  
            case WHITE_QUEEN :
                opposite = Piece.BLACK_QUEEN;
                break;
            case BLACK_QUEEN :
                opposite = Piece.WHITE_QUEEN;
                break;  
            default :
                opposite = Piece.EMPTY;
                break;
        }
        
        return opposite;
    }
    
    // Change le joueur courant et calcule les prochains coups possibles.
    private void changePlayer() {
        PlayerEnum oldPlayer = board.getCurrentPlayer();
        PlayerEnum newPlayer;
        if (oldPlayer == PlayerEnum.PLAYER_1) {
            newPlayer = PlayerEnum.PLAYER_2;
        } else {
            newPlayer = PlayerEnum.PLAYER_1;
        }
        board.setCurrentPlayer(newPlayer);
    }
    
    private void calculateAvalaibleMoves() {
        PlayerEnum newPlayer = board.getCurrentPlayer();
        Piece[] pieces = board.getPieces();
        Piece[] reversePieces;
        if (newPlayer == PlayerEnum.PLAYER_1) {
             // On renverse le tableau si on veut vérifier les coups possible du joueur 1.
             // car Rules.getAvalaibleMoves ne fonctionne qu'avec un joueur ayant le bord (0,1,2,3,4).
             reversePieces = reversePieces(pieces);
             List<Piece[]> avalaiblePiecesReverse = Rules.getAvalaibleMoves(reversePieces);
             avalaiblePiecesList.clear();
             avalaiblePiecesReverse.stream().forEach((avalaiblePieces) -> {
                 avalaiblePiecesList.add(reversePieces(avalaiblePieces));
         });
        } else {
             reversePieces = pieces.clone();
             avalaiblePiecesList = Rules.getAvalaibleMoves(reversePieces);
        }
    }
    
    private boolean lostGame(PlayerEnum player) {
        boolean res = false;
        Piece[] pieces = board.getPieces();
        if (player == PlayerEnum.PLAYER_1) {
            for (Piece p : pieces) {
                if (p == Piece.WHITE_PIECE || p == Piece.WHITE_QUEEN) {
                    return true;
                }
            }
        } else {
            for (Piece p : pieces) {
                if (p == Piece.BLACK_PIECE || p == Piece.BLACK_QUEEN) {
                    return true;
                }
            }
        }
        return res;
    }
    
}
