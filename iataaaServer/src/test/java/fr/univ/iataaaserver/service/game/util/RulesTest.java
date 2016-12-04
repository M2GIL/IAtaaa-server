package fr.univ.iataaaserver.service.game.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.univ.iataaaserver.IataaaServerApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IataaaServerApp.class)

public class RulesTest {

	@Test
	public void testGetAvailableMoves() {

		// pour que ça enlève l'erreure il faut mettre le constructeur de la
		// classe Rules en public
		// et mettre la méthode getAvalaibleMoves sur la classe Rules en public
		// modifier la visibilité des méthodes de Rules

		//Rules rule = new Rules();
                /*
		Piece[] pieces = new Piece[15];
		for (int i = 0; i < 15; i++) {
			pieces[i] = Piece.WHITE_PIECE;
		}*/

		// le test passe dans le cas ou on modifier la methode getAvalableMoves
		// avec la valeur 1 de i le test passe
		// fillAvalaibleMovesForOnePiece(pieces, 1);

		//List<Piece[]> lp = rule.getAvalaibleMoves(pieces);
		//assertThat(lp).isNotNull();

	}

}
