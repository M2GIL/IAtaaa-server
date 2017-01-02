package fr.univrouen.iataaaserver.services.util;

import fr.univrouen.iataaaserver.entities.Case;
import fr.univrouen.iataaaserver.entities.EnumPlayer;
import fr.univrouen.iataaaserver.services.game.Game;

import java.util.ArrayList;
import java.util.List;

public class ReverseRules {

    private static final int FIRST_POSITION_OF_LAST_LINE = Game.CASE_NB_OF_LINE
        * (Game.LINE_NB - 1);
    private static final int LAST_POSITION_OF_FIRST_LINE = Game.CASE_NB_OF_LINE
        - 1;


    private static List<Case[]> availableMoves = new ArrayList<>();
    private static int sizeMove = 1;

    // positionpieces est la liste des position pièces que l'on veut analyser.
    public static List<Case[]> getAvailableMoves(Case[] cases, EnumPlayer p) {
        List<Integer> whiteCases;
        if (p == EnumPlayer.J2) {
            cases = reverseCases(cases);
        }
        whiteCases = getWhiteCases(cases);
        for (int i : whiteCases) {
            fillAvalaibleMovesForOneCase(cases, i);
        }
        
        transformPieceToQueen();

        List<Case[]> avalaibleMovesList;
        if (p == EnumPlayer.J2) {
            avalaibleMovesList = new ArrayList<>();
            availableMoves.stream().forEach((c) -> {
                avalaibleMovesList.add(reverseCases(c));
            });
        } else {
            avalaibleMovesList = availableMoves;
        }

        List<Case[]> res = new ArrayList<>();
        for (Case[] c : avalaibleMovesList) {
            res.add(c);
        }

        availableMoves = new ArrayList<>();
        sizeMove = 1;

        return res;
    }


// PRIVATE
// Elle devront être privé quand les tests ne serviront plus.

    // Donne la position de la case supérieur gauche.
    // Retourne -1 s'il n'y en a pas.
    public static int getTopLeftCornerPosition(int position) {
        int res = -1;
        if ( !(position % 10 == 0 || position >= FIRST_POSITION_OF_LAST_LINE) ) {
            res = position + (position / 5) % 2 + (Game.PIECE_SIZE / 10) - 1;
        }
        return res;
    }

    // Donne la position de la case supérieur droite.
    // Retourne -1 s'il n'y en a pas.
    public static int getTopRightCornerPosition(int position) {
        int res = -1;
        if ( !( (position + 1) % 10 == 0 || position >= FIRST_POSITION_OF_LAST_LINE) ) {
            res = position + (position / 5) % 2 + (Game.PIECE_SIZE / 10);
        }
        return res;
    }

    // Donne la position de la case inférieur gauche.
    // Retourne -1 s'il n'y en a pas.
    public static int getBottomLeftCornerPosition(int position) {
        int res = -1;
        if ( !(position % 10 == 0 || position <= LAST_POSITION_OF_FIRST_LINE) ) {
            // Arrière gauche : pos - (SIZE/10)-1 - (pos/5)%2
            res = position - (Game.PIECE_SIZE / 10)  - (((position / 5) + 1) % 2);
        }
        return res;
    }

    // Donne la position de la case inférieur droite.
    // Retourne -1 s'il n'y en a pas.
    public static int getBottomRightCornerPosition(int position) {
        int res = -1;
        if ( !( (position + 1) % 10 == 0 || position <= LAST_POSITION_OF_FIRST_LINE) ) {
            res = position - Game.PIECE_SIZE / 10  - ((position / 5 + 1) % 2) + 1;
        }
        return res;
    }

    // Donne la position de la case supérieur gauche pour manger une pièce.
    // Retourne -1 s'il n'y en a pas.
    public static int getTopLeftCornerPosition(int position, int distance) {
        int tmpPosition = position;
        do {
            tmpPosition = getTopLeftCornerPosition(tmpPosition);
            --distance;
        } while (tmpPosition != -1 && distance > 0);
        return tmpPosition;
    }

    // Donne la position de la case supérieur droite pour manger une pièce.
    // Retourne -1 s'il n'y en a pas.
    public static int getTopRightCornerPosition(int position, int distance) {
        int tmpPosition = position;
        do {
            tmpPosition = getTopRightCornerPosition(tmpPosition);
            --distance;
        } while (tmpPosition != -1 && distance > 0);
        return tmpPosition;
    }

    // Donne la position de la case inférieur gauche pour manger une pièce.
    // Retourne -1 s'il n'y en a pas.
    public static int getBottomLeftCornerPosition(int position, int distance) {
        int tmpPosition = position;
        do {
            tmpPosition = getBottomLeftCornerPosition(tmpPosition);
            --distance;
        } while (tmpPosition != -1 && distance > 0);
        return tmpPosition;
    }

    // Donne la position de la case inférieur droite pour manger une pièce.
    // Retourne -1 s'il n'y en a pas.
    public static int getBottomRightCornerPosition(int position, int distance) {
        int tmpPosition = position;
        do {
            tmpPosition = getBottomRightCornerPosition(tmpPosition);
            --distance;
        } while (tmpPosition != -1 && distance > 0);
        return tmpPosition;
    }


    // Donne toutes les positions des cases supérieurs gauches.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllTopLeftCornerPosition(int position) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getTopLeftCornerPosition(position); tmpPosition != -1;
            tmpPosition = getTopLeftCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases supérieurs droites.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllTopRightCornerPosition(int position) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getTopRightCornerPosition(position); tmpPosition != -1;
            tmpPosition = getTopRightCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases inférieurs gauches.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllBottomLeftCornerPosition(int position) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getBottomLeftCornerPosition(position); tmpPosition != -1;
            tmpPosition = getBottomLeftCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases inférieurs droites.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllBottomRightCornerPosition(int position) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getBottomRightCornerPosition(position); tmpPosition != -1;
            tmpPosition = getBottomRightCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases supérieurs gauches jusqu'à tgtPosition.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllTopLeftCornerPosition(int src, int tgt) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getTopLeftCornerPosition(src);
            tmpPosition != -1 && tmpPosition != tgt;
            tmpPosition = getTopLeftCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases supérieurs droites jusqu'à tgtPosition.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllTopRightCornerPosition(int src, int tgt) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getTopRightCornerPosition(src);
            tmpPosition != -1 && tmpPosition != tgt;
            tmpPosition = getTopRightCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases inférieur gauches jusqu'à tgtPosition.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllBottomLeftCornerPosition(int src, int tgt) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getBottomLeftCornerPosition(src);
            tmpPosition != -1 && tmpPosition != tgt;
            tmpPosition = getBottomLeftCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Donne toutes les positions des cases inférieur droites jusqu'à tgtPosition.
    // Retourne -1 s'il n'y en a pas.
    public static List<Integer> getAllBottomRightCornerPosition(int src, int tgt) {
        List<Integer> res = new ArrayList<>();
        for (int tmpPosition = getBottomRightCornerPosition(src);
            tmpPosition != -1 && tmpPosition != tgt;
            tmpPosition = getBottomRightCornerPosition(tmpPosition)) {
            res.add(tmpPosition);
        }
        return res;
    }

    // Rempli avalaibleMoves de tous les coups possible de la pièce en position
    // position.
    // Retourne vrai si un pion est sauté.
    private static void fillAvalaibleMovesForOneCase(Case[] pieces, int srcPosition) {
        assert pieces[srcPosition] != Case.BLACK_PIECE;
        assert pieces[srcPosition] != Case.BLACK_QUEEN;
        assert pieces[srcPosition] != Case.EMPTY;

        boolean isPossibleToJump = sequenceJump(pieces, srcPosition, 1,
            new ArrayList<>());
        if (!isPossibleToJump && sizeMove == 1) { // S'il n'est pas possible de sauter une pièce.
            // Si sizeMove n'est pas égale à 1 alors il est inutile d'aller plus loin car il existe des mouvement
            // plus long dans avalaibleMove.
            if (pieces[srcPosition] == Case.WHITE_PIECE) { // Si la piece source est un pion est on ne peut pas sauter de pion.

            //------------------------------------------------------------------
            // Coté gauche du pion
                int topLeftCornerPosition = getTopLeftCornerPosition(srcPosition);
                if (topLeftCornerPosition != -1) {
                    Case topleftcornerCase = pieces[topLeftCornerPosition];
                    if (topleftcornerCase == Case.EMPTY) { // Si je peux déplacer mon pion en haut à gaucher.
                        Case[] move = getMoveWhiteCase(pieces, srcPosition, topLeftCornerPosition);
                        availableMoves.add(move);
                    }
                }
                //------------------------------------------------------------------
                // Coté droit du pion
                int topRightCornerPosition = getTopRightCornerPosition(srcPosition);
                if (topRightCornerPosition != -1) {
                    Case topRightcornerCase = pieces[topRightCornerPosition];
                    if (topRightcornerCase == Case.EMPTY) { // Si je peux déplacer mon pion en haut à droite.
                        Case[] move = getMoveWhiteCase(pieces, srcPosition, topRightCornerPosition);
                        availableMoves.add(move);
                    }
                }
            } else { // Le pion est une dame et on ne peut pas sauter de pion.
                int k, i;
                Case[] newCases;
                List<Integer> positions;
                int sizePositions;

                positions = getAllTopLeftCornerPosition(srcPosition);
                sizePositions = positions.size();
                if (!positions.isEmpty()) {
                    i = 0;
                    k = positions.get(i);
                    while (i < sizePositions && pieces[k] == Case.EMPTY) { // On ajoute toute les position de positions
                        // jusqu'à ce qu'on rencontre une pièce non vide.
                        newCases = getMoveWhiteCase(pieces, srcPosition, k);
                        availableMoves.add(newCases);
                        k = positions.get(i);
                        ++i;
                        if (i < sizePositions) {
                            k = positions.get(i);
                        }
                    }
                }


                positions = getAllTopRightCornerPosition(srcPosition);
                sizePositions = positions.size();
                if (!positions.isEmpty()) {
                    i = 0;
                    k = positions.get(i);
                    while (i < sizePositions && pieces[k] == Case.EMPTY) { // On ajoute toute les position de positions
                        // jusqu'à ce qu'on rencontre une pièce non vide.
                        newCases = getMoveWhiteCase(pieces, srcPosition, k);
                        availableMoves.add(newCases);
                        k = positions.get(i);
                        ++i;
                        if (i < sizePositions) {
                            k = positions.get(i);
                        }
                    }
                }

                positions = getAllBottomLeftCornerPosition(srcPosition);
                sizePositions = positions.size();
                if (!positions.isEmpty()) {
                    i = 0;
                    k = positions.get(i);
                    while (i < sizePositions && pieces[k] == Case.EMPTY) { // On ajoute toute les position de positions
                        // jusqu'à ce qu'on rencontre une pièce non vide.
                        newCases = getMoveWhiteCase(pieces, srcPosition, k);
                        availableMoves.add(newCases);
                        k = positions.get(i);
                        ++i;
                        if (i < sizePositions) {
                            k = positions.get(i);
                        }
                    }
                }

                positions = getAllBottomRightCornerPosition(srcPosition);
                sizePositions = positions.size();
                if (!positions.isEmpty()) {
                    i = 0;
                    k = positions.get(i);
                    while (i < sizePositions && pieces[k] == Case.EMPTY) { // On ajoute toute les position de positions
                        // jusqu'à ce qu'on rencontre une pièce non vide.
                        newCases = getMoveWhiteCase(pieces, srcPosition, k);
                        availableMoves.add(newCases);
                        k = positions.get(i);
                        ++i;
                        if (i < sizePositions) {
                            k = positions.get(i);
                        }
                    }
                }
            }
        }
    }
    private static boolean isInInterval(int src, int tgt, Direction direction,
        List<Integer> prohibited) {
        boolean res = false;

        List<Integer> positions;

        switch (direction) {
            case TOP_LEFT :
                positions = getAllTopLeftCornerPosition(src, tgt);
                break;
            case TOP_RIGHT :
                positions = getAllTopRightCornerPosition(src, tgt);
                break;
            case BOTTOM_LEFT :
                positions = getAllBottomLeftCornerPosition(src, tgt);
                break;
            default :
                positions = getAllBottomRightCornerPosition(src, tgt);
                break;
        }

        for (int p : prohibited) {
            if (positions.contains(p)) {
                res = true;
                break;
            }
        }

        return res;
    }
    // Retourne faux s'il n'est pas possible de sauter une pion, sinon vrai
    // et enregistre le coup le/les coups les plus longs dans availableMove.
    // La direction est un côté sur lequel on ne souhaite se déplacer.
    private static boolean sequenceJump(Case[] pieces, int srcPosition, final int size,
        List<Integer> jumpedCases) {
        assert pieces[srcPosition] != Case.BLACK_PIECE;
        assert pieces[srcPosition] != Case.BLACK_QUEEN;
        assert pieces[srcPosition] != Case.EMPTY;

        boolean isPossibleToJump = false;

        if (pieces[srcPosition] == Case.WHITE_PIECE) {
            int tgtPosition;
            int jumpedPosition;
            boolean addJumpedMoveToSequence;

        // Haut gauche
                jumpedPosition = getTopLeftCornerPosition(srcPosition);
                tgtPosition = getTopLeftCornerPosition(srcPosition, 2);
                addJumpedMoveToSequence = addJumpedMoveToSequence(pieces,
                    srcPosition, jumpedPosition, tgtPosition, size);
                isPossibleToJump = isPossibleToJump || addJumpedMoveToSequence;

        // Haut droit
                jumpedPosition = getTopRightCornerPosition(srcPosition);
                tgtPosition = getTopRightCornerPosition(srcPosition, 2);
                addJumpedMoveToSequence = addJumpedMoveToSequence(pieces,
                    srcPosition, jumpedPosition, tgtPosition, size);
                isPossibleToJump = isPossibleToJump || addJumpedMoveToSequence;

        // Bas gauche
                jumpedPosition = getBottomLeftCornerPosition(srcPosition);
                tgtPosition = getBottomLeftCornerPosition(srcPosition, 2);
                addJumpedMoveToSequence = addJumpedMoveToSequence(pieces,
                    srcPosition, jumpedPosition, tgtPosition, size);
                isPossibleToJump = isPossibleToJump || addJumpedMoveToSequence;

        // Bas droit
                jumpedPosition = getBottomRightCornerPosition(srcPosition);
                tgtPosition = getBottomRightCornerPosition(srcPosition, 2);
                addJumpedMoveToSequence = addJumpedMoveToSequence(pieces,
                    srcPosition, jumpedPosition, tgtPosition, size);
                isPossibleToJump = isPossibleToJump || addJumpedMoveToSequence;

        } else { // Si la pièce source est une dame.
            List<Integer> positions;
            Case[] newCases;
            int jumpedPos;
            Couple<List<Integer>, Integer> couple;

            // Haut gauche
                positions = getAllTopLeftCornerPosition(srcPosition);
                couple = getPositionsToJump(pieces, positions);
                positions = couple.getFirst();
                jumpedPos = couple.getSecond();
                if (jumpedPos != -1) {
                    for (int tgtPos : positions) {
                        if (!isInInterval(srcPosition, tgtPos, Direction.TOP_LEFT, jumpedCases)) {
                            isPossibleToJump = true;
                            newCases = getJumpBlackCase(pieces, srcPosition, jumpedPos, tgtPos);
                            jumpedCases.add(jumpedPos);
                            sequenceJump(newCases, tgtPos, size + 1, jumpedCases);
                            jumpedCases.remove(jumpedCases.indexOf(jumpedPos));
                        }
                    }
                }

            // Haut droit
                positions = getAllTopRightCornerPosition(srcPosition);
                couple = getPositionsToJump(pieces, positions);
                positions = couple.getFirst();
                jumpedPos = couple.getSecond();
                if (jumpedPos != -1) {
                    for (int tgtPos : positions) {
                        if (!isInInterval(srcPosition, tgtPos, Direction.TOP_RIGHT, jumpedCases)) {
                            isPossibleToJump = true;
                            newCases = getJumpBlackCase(pieces, srcPosition, jumpedPos, tgtPos);
                            jumpedCases.add(jumpedPos);
                            sequenceJump(newCases, tgtPos, size + 1, jumpedCases);
                            jumpedCases.remove(jumpedCases.indexOf(jumpedPos));
                        }
                    }
                }


            // Bas gauche
                positions = getAllBottomLeftCornerPosition(srcPosition);
                couple = getPositionsToJump(pieces, positions);
                positions = couple.getFirst();
                jumpedPos = couple.getSecond();
                if (jumpedPos != -1) {
                    for (int tgtPos : positions) {
                        if (!isInInterval(srcPosition, tgtPos, Direction.BOTTOM_LEFT, jumpedCases)) {
                            isPossibleToJump = true;
                            newCases = getJumpBlackCase(pieces, srcPosition, jumpedPos, tgtPos);
                            jumpedCases.add(jumpedPos);
                            sequenceJump(newCases, tgtPos, size + 1, jumpedCases);
                            jumpedCases.remove(jumpedCases.indexOf(jumpedPos));
                        }
                    }
                }


            // Bas droit
                positions = getAllBottomRightCornerPosition(srcPosition);
                couple = getPositionsToJump(pieces, positions);
                positions = couple.getFirst();
                jumpedPos = couple.getSecond();
                if (jumpedPos != -1) {
                    for (int tgtPos : positions) {
                        if (!isInInterval(srcPosition, tgtPos, Direction.BOTTOM_RIGHT, jumpedCases)) {
                            isPossibleToJump = true;
                            newCases = getJumpBlackCase(pieces, srcPosition, jumpedPos, tgtPos);
                            jumpedCases.add(jumpedPos);
                            sequenceJump(newCases, tgtPos, size + 1, jumpedCases);
                            jumpedCases.remove(jumpedCases.indexOf(jumpedPos));
                        }
                    }
                }

        }

        if (size != 1 && !isPossibleToJump) {
            /* Si size == 1 alors la methode n'a pas eu d'appel récursif.
               On ne souhaite pas ajouter de move.
               Si il est possible de sauter encore un pion alors le mouvement n'est pas encore complet
               et on ne souhaite pas ajouter ce Move. C'est un appel recursif sur la suite du mouvement
               qui ajoutera le Move en entier.
            */

            if (size == sizeMove) {
                availableMoves.add(pieces);
            } else if (size > sizeMove) {
                sizeMove = size;
                availableMoves.clear();
                availableMoves.add(pieces);
            }
        }

        return isPossibleToJump;
    }

    // positions est une liste de position diagonale. Cette méthode retourne une liste des positions
    // où une dame peut aller après avoir sauté un pion. Et elle retourne aussi la position du pion sauté.
    // couple.getSecond == -1 si on ne peut pas sauter de pion.
    private static Couple<List<Integer>, Integer> getPositionsToJump(Case[] pieces, List<Integer> positions) {
        List<Integer> positionsToJump = new ArrayList<>();
        int jumpedPosition = -1;
        if (!positions.isEmpty()) {
            int i = 0;
            int k = positions.get(i);
            while (pieces[k] == Case.EMPTY && i < positions.size()) {
                ++i;
                if (i < positions.size()) {
                    k = positions.get(i);
                }
            }
            if (i < positions.size() &&
                (pieces[k] == Case.BLACK_PIECE
                || pieces[k] == Case.BLACK_QUEEN)) {

                jumpedPosition = k;
                ++i;
                if (i < positions.size()) {
                    k = positions.get(i);
                    if (pieces[k] != Case.EMPTY) {
                        jumpedPosition = -1;
                    }
                    while (pieces[k] == Case.EMPTY && i < positions.size()) {
                        positionsToJump.add(k);
                        ++i;
                        if (i < positions.size()) {
                            k = positions.get(i);
                        }
                    }
                }
            }
        }
        return new Couple(positionsToJump, jumpedPosition);
    }


    private static boolean addJumpedMoveToSequence(Case[] pieces, int srcPosition,
        int jumpedPosition, int tgtPosition, int size) {
        boolean isPossibleToJump = false;
        if (jumpedPosition != -1 && tgtPosition != -1) {
            if (pieces[tgtPosition] == Case.EMPTY
                && (pieces[jumpedPosition] == Case.BLACK_PIECE
                    || pieces[jumpedPosition] == Case.BLACK_QUEEN)) {
            // Si on peut sauter un pion adverse dans le coin supérieur gauche
                isPossibleToJump = true;
                Case[] newCases = getJumpBlackCase(pieces, srcPosition, jumpedPosition, tgtPosition);
                sequenceJump(newCases, tgtPosition, size + 1, null);
            }
        }
        return isPossibleToJump;
    }


    // Retourne un tableau avec le pion noir en jumpedPosition sauté.
    // Et la pièce blanche en srcPosition est déplacé en tgtPosition.
    private static Case[] getJumpBlackCase(Case[] pieces, int srcPosition,
        int jumpedPosition, int tgtPosition) {

        assert (pieces[srcPosition] == Case.WHITE_QUEEN)
            || pieces[srcPosition] == Case.WHITE_PIECE;
        assert pieces[tgtPosition] == Case.EMPTY;
        assert pieces[jumpedPosition] == Case.BLACK_PIECE
            || pieces[jumpedPosition] == Case.BLACK_QUEEN;
        Case[] res = pieces.clone();
        Case srcPi = res[srcPosition];
        res[srcPosition] = Case.EMPTY;
        res[jumpedPosition] = Case.EMPTY;
        res[tgtPosition] = srcPi;
        return res;
    }

    // Retourne une copie de pieces avec une piece déplacé de positionSrc à
    // positionTgt.
    // Je suppose que cette fonction va prendre de la place en mémoire.
    // Faudra la supprimer je suppose.
    private static Case[] getMoveWhiteCase(Case[] pieces, int srcPosition, int tgtPosition) {
        assert pieces[srcPosition] != Case.BLACK_PIECE;
        assert pieces[srcPosition] != Case.BLACK_QUEEN;
        assert pieces[srcPosition] != Case.EMPTY;
        assert pieces[tgtPosition] == Case.EMPTY;

        Case[] res = pieces.clone();
        Case p = res[srcPosition];
        res[srcPosition] = Case.EMPTY;
        res[tgtPosition] = p;
        return res;
    }

    private static List<Integer> getWhiteCases(Case[] pieces) {
        List<Integer> positions = new ArrayList<>(15);
        for (int i = 0; i < pieces.length; ++i) {
            if (pieces[i] == Case.WHITE_PIECE || pieces[i] == Case.WHITE_QUEEN) {
                positions.add(i);
            }
        }
        return positions;
    }

    private static int reverseCaseIndice(int indice) {
        assert indice >= 0 && indice < Game.PIECE_SIZE : "indice = " + indice;
        return 49 - indice;
    }

    public static Case[] reverseCases(Case[] pieces) {
        Case[] reverseCases = new Case[Game.PIECE_SIZE];
        for (int i = 0; i < Game.PIECE_SIZE; ++i) {
            reverseCases[reverseCaseIndice(i)] = oppositeColor(pieces[i]);
        }

        return reverseCases;
    }

    private static Case oppositeColor(Case piece) {
        Case opposite;
        switch (piece) {
            case WHITE_PIECE :
                opposite = Case.BLACK_PIECE;
                break;
            case BLACK_PIECE :
                opposite = Case.WHITE_PIECE;
                break;
            case WHITE_QUEEN :
                opposite = Case.BLACK_QUEEN;
                break;
            case BLACK_QUEEN :
                opposite = Case.WHITE_QUEEN;
                break;
            default :
                opposite = Case.EMPTY;
                break;
        }

        return opposite;
    }
    
    private static void transformPieceToQueen() {
        for(Case[] pcs : availableMoves) {
            for (int i = 49; i >= 45; --i) {
                if (pcs[i] == Case.WHITE_PIECE) {
                    pcs[i] = Case.WHITE_QUEEN;
                }
            }
        }
    }


// ENUM

    private enum Direction {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
    }

}
