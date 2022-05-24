import java.util.ArrayList;

public class AI_High extends AI {

	private ArrayList<Integer> counterSides = new ArrayList<Integer>();
	int[] prioArrPerson;
	Corner corner = null;
	
	public void addPiece(Table table, Person person) {
		
		Piece[] playerHand = getPlayerHand();
		Piece compare = null;
		int count = 0;
		definePrio(person);
		
		int i = checkPlayables(table);
		if(i==-1) return ; 
		
		if(corner==null) return ;
		
		while(true) {
			if (!table.addPiece(playerHand[i], corner)) {
				playerHand[i].setPrio(0);
				playerHand = setPlayerHand(playerHand);
				
				if (count > 0 && compare == playerHand[i]) return;

				if (count == 0) compare = playerHand[i];
				count++;
				
				i = checkPlayables(table);
				if(i==-1) return ;
			}
			else break;
		}
			
		removePiece(playerHand[i]);
		playerHand = getPlayerHand();
		printPlay(playerHand[i], corner);
		resetPrio();
		return ;
	}
	
	private int checkPlayables(Table table) {
		Piece[] playerHand = getPlayerHand();
		playerHand = setPlayerHand(prioSort());
		
		boolean handOfCounters = handOfCounters(table); // if the hand is only with counter moves
		
		int i;
		for(i = 0; i < playerHand.length; i++) {
			int A = playerHand[i].getSideA();
			int B = playerHand[i].getSideB();
			int prio = playerHand[i].getPrio();			
			boolean isCounterSide = (A == counterSides.get(i) || B == counterSides.get(i)) && prio > 999;
			
			if(isCounterSide) { 
				if(A==counterSides.get(i)) corner = table.findPlayableCounterCorner(B);
				else corner = table.findPlayableCounterCorner(A);
				
				if(corner!=null) return i;
				
				if(handOfCounters) {
					if(A==counterSides.get(i)) corner = table.findPlayableCounterCorner(A);
					else corner = table.findPlayableCounterCorner(B);
				}
				
				if(corner!=null) return i;		
				continue;
			}
			
			corner = table.findPlayableCorner(A, B);
			outer:
			if(corner!=null) {
				for(int j = 0; j < prioArrPerson.length; j++) {
					boolean isCounterCorner = corner.getPiece().getSideA()==prioArrPerson[j] && prioArrPerson[j]==0;
					if(isCounterCorner) {
						corner=null;
						break outer;
					}
				}
				return i;
			}
		}
		
		if(corner==null) return -1;
		else return i;
	}
	
	private boolean handOfCounters(Table table) {
		
		Piece[] pH = getPlayerHand();
		int length = pH.length;
		if(pH[length].getPrio() > 999) return true;
		
		return false;
	}
	
	private void definePrio(Person person) {
		
		Piece[] playerHand = getPlayerHand();	
		int prio = 0;
		int[] prioArr = numberOfSameSides(playerHand);
		prioArrPerson = numberOfSameSides(person.getPlayerHand());
		
		for(int i = 0; i < playerHand.length; i++) { 	
			if(playerHand[i].dual()) prio+=20;		
			prio+=prioArr[playerHand[i].getSideA()]+7; // number of common
			prio+=prioArr[playerHand[i].getSideB()]+7;
			prio+=playerHand[i].getSideA();
			prio+=playerHand[i].getSideB();
			
			for (int j = 0; j < prioArrPerson.length; i++) { // set counter priorities
				if (prioArrPerson[j]==0) { // if player has no piece
					int A = playerHand[i].getSideA();
					int B = playerHand[i].getSideB();
					if (j==A || j==B) {
						prio+=999;
						if(j==A) counterSides.add(A);
						else counterSides.add(B);
					}
				}
			}
			playerHand[i].setPrio(prio);
			prio=0;
		}
	}

	@Override
	public void addPiece(Table table) {
		// TODO Auto-generated method stub
		
	}
	
}
