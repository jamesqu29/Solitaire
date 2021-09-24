import java.awt.*;
import java.util.Stack;
import java.util.EmptyStackException;

public class CardPile {
	
	// coordinates of the card pile
	protected int x;
	protected int y;
	protected Stack thePile;

	// constructor
	CardPile (int xl, int yl) { 

		//provide code here
		x = xl;
		y = yl;
		thePile = new Stack();
	}

	// access to cards are not overridden
	public final Card top() { //provide code here 
		return (Card) thePile.peek(); 
	}

	public final boolean isEmpty() { //provide code here 
		return thePile.isEmpty();
	}

	public final Card pop() {//provide code here
		return (Card) thePile.pop();
	}

	// the following are sometimes overridden
	public boolean includes (int tx, int ty) {
		return x <= tx && tx <= x + Card.width && y <= ty && ty <= y + Card.height;
	}
	
	public void select (int tx, int ty) {
		// do nothing
	}

	//add a card to the pile
	public void addCard (Card aCard)  { //provide code here 
		thePile.push(aCard);
	}

	// this will be override by the TablePile class
	public void display (Graphics g) {
		g.setColor(Color.blue);
		if (isEmpty())
			g.drawRect(x, y, Card.width, Card.height);
		else
			top().draw(g, x, y);
	}

	//the default is no ; this is overridden by the two inherit classes i.e. SuitPile and tablePile
	public boolean canTake (Card aCard) {
		return false; 
	}
}
