/*
abstract class for building up a card pile
-build using stack data structure provided by Java
 */

import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JPanel;

public abstract class CardPile extends JPanel {
	
	//protected int x, y;
	protected Stack<Card> cards; //using stack data structure provided by java for storing cards

	public CardPile(int x, int y) {
		super.setLocation(x, y);
		cards = new Stack<>();
		
	}
	
	public Card topCard() { //see what the top card is, without removing it from the pile
		if(!this.cards.isEmpty()) {
			return this.cards.peek();
		}
		return null;
	}
	
	public Card pop() { //pop the top card off the pile
		try {
			return cards.pop();
		}catch(EmptyStackException ese) {
			return null;
		}
	}
	
	public void push(Card someCard) {
		this.cards.push(someCard);
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}

}
