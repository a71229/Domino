import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTests {
	@Test
	void testDealHand() {
		ArrayList<Piece> deck = new ArrayList<>();
		for(int i = 0; i < )
		Player jogador = new Person();
		Player AI1 = new Person();
		Player AI2 = new AI();
		Player AI3 = new AI();
		Player AI4 = new AI();
		
		jogador.dealHand(jogo.getDeck());
		Piece[] hand = jogador.getPlayerHand();
		assertEquals(7, hand.length);
		
		AI1.dealHand(jogo.getDeck());
		for(int i = 0; i < 7; i++) {
			
		}
	}
	
}
