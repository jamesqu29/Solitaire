/*
Foundation pile class - inherited cardPile class - this is suitPile in the previous version
 */
import java.awt.Graphics;

public class FoundationPile extends CardPile {
	
	private int suit;

	public FoundationPile(int x, int y, int i) { //Foundation pile is suitPile in our first iteration
		super(x, y);
		super.setSize(72, 96);
		this.suit = i;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.isEmpty()) {
			g.drawImage(Card.getFoundationBase(suit), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}else {
			g.drawImage(this.topCard().getCardImage(),
					0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public void moveFromWaste(DiscardCardPile source, Card card) { //move logic
		if(accepts(card)) {
			this.push(source.pop());
			source = null;
		}
	}
	
	public void moveTo(TablePile destination, Card card) { //move logic
		if(destination.accepts(card)) {
			destination.push(this.pop());
		}
	}

	public boolean accepts(Card card) { //logic for checking whether the pile can take a card
		if(!this.isEmpty()) {
			return this.topCard().getValue() == card.getValue() - 1 && this.topCard().getSuit().equals(card.getSuit());
		}
		return card.getValue() == 1 && intToSuit(card.getSuit()); // Ace
	}
	
	private boolean intToSuit(String pSuit) {
		if (pSuit.equals("c")) {
			return this.suit == 3;
		} else if (pSuit.equals("s")) {
			return this.suit == 1;
		} else if (pSuit.equals("h")) {
			return this.suit == 2;
		} else if (pSuit.equals("d")) {
			return this.suit == 4;
		}
		return false;
	}

}
