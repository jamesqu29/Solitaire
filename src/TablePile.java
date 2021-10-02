
import java.awt.*;
import java.util.Deque;
import java.util.Enumeration;

class TablePile extends CardPile {

	TablePile (int x, int y, int c) {
		// initialize the parent class
		super(x,y);	
		// then initialize our pile of cards
		for (int i = 0; i < c; i++) {
			//provide code here
			addCard(Solitaire.deckPile.pop());
		}
		// flip topmost card face up
		top().flip(); //top() is provided by cardPile class
	}

	public boolean canTake (Card aCard) {//provide code here
		if (isEmpty())
			return aCard.rank() == 12;
		Card topCard = top();
		return (aCard.color()!= topCard.color()) &&
				(aCard.rank() == topCard.rank() -1 );
		
	}

	public boolean includes (int tx, int ty) {
		if (isEmpty())
			return false;
		// don't test bottom of card
		return x <= tx && tx <= x + Card.width && y <= ty;
	}

	public void select (int tx, int ty) {
		if (isEmpty())
			return;

		// if face down, then flip
		Card topCard = top();
		if (!topCard.faceUp()) {
			topCard.flip();
			return;
		}

		// else see if any suit pile can take card

		for (int i = 0; i < 4; i++){
			if (Solitaire.suitPile[i].canTake(topCard)) {
				Solitaire.suitPile[i].addCard(pop());
				return;
			}
		}
		/*
			// else see if any other table pile can take card
		for (int i = 0; i < 7; i++)
			if (Solitaire.tableau[i].canTake(topCard)) {
				Solitaire.tableau[i].addCard(topCard);
				return;
				}
			// else put it back on our pile
		addCard(topCard);*/


		//create temporary sub-pile
		CardPile tempPile = new CardPile(10,10);
		//get the card for this sub-pile from the pile
		while(!isEmpty()){
			if (!top().faceUp())
				break;
			tempPile.addCard(pop());
		}
		// if play only one card
		if(tempPile.top() == topCard){

			//put it back to the table pile
			addCard(tempPile.pop());
			//test if any other table piles can take card
			for (int i = 0; i < 7; i++)
				if (Solitaire.tableau[i].canTake(topCard)) {
					Solitaire.tableau[i].addCard(pop());
					return;
				}

		} else { //play sub-pile
			topCard = tempPile.top();
			// test if any other table pile can take this sub-pile
			for (int i = 0; i< 7; i++) {
				if (Solitaire.tableau[i].canTake(topCard)) {
					while (!tempPile.isEmpty())
						Solitaire.tableau[i].addCard(tempPile.pop());
					return;
				}
			}
			//put every card in this sub-pile to the pile
			while (!tempPile.isEmpty()){
				addCard(tempPile.pop());
			}
		}

	}

	public void display (Graphics g) {
		int localy = y;
		for (Enumeration e = thePile.elements(); e.hasMoreElements(); ) {
			Card aCard = (Card) e.nextElement();
			aCard.draw (g, x, localy);
			localy += 35;
			}
	}
}