package uglytrivia;

import static org.junit.Assert.*;
import com.adaptionsoft.games.uglytrivia.Game;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class SomeTest {
	//
	@Mock
	private Game game;
	@Test
	public void add_IF_Test(){

		//When
		boolean result = game.add("kevin");
		//Then
		assertTrue("Le joueur a été ajouter ?",result);
	}
/*
	@Test
	public void true_is_true() throws Exception {
		assertTrue(false);
	}

	/* @Test
	void howManyPlayersTest(){
		ArrayList players = new ArrayList();
		players.add("correntin");
		players.add("max");
		players.add("bill");
		players.add("franck");

		howManyPlayers();

		assertEquals(4);
	}
	 */
}
