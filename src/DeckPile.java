
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class DeckPile extends CardPile {

	DeckPile (int x, int y) {
		// first initialize parent
		super (x, y);
		// then create the new deck
		// first put them into a local pile
		for (int i = 0; i < 4; i++){
			for (int j = 0; j <= 12; j++){ 
				
				//provide code here
				Card newCard = new Card(i,j);
				addCard(newCard);
			}
		}

		// then shuffle the cards
		Random generator = new Random();
		for (int i = 0; i < 52; i++) {
			int j = Math.abs(generator.nextInt() % 52);
			
			// swap the two card values
			Object temp = thePile.elementAt(i);
			thePile.setElementAt(thePile.elementAt(j), i);
			thePile.setElementAt(temp, j);
		}
	}

	public void select(int tx, int ty) {
		//if deck pile becomes empty, add from the discard pile
		if (isEmpty()){
			while(!Solitaire.discardPile.isEmpty()){
				addCard(Solitaire.discardPile.pop());
				top().flip();
			}
		}
		Solitaire.discardPile.addCard(pop());

	}
}