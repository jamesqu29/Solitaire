/*
Discard pile class - inherited from cardPile class for building discard pile
 */
import java.awt.Graphics;

public class DiscardCardPile extends CardPile {

	public DiscardCardPile(int x, int y) { //initially, discard pile is empty
		super(x, y);
		super.setSize(72, 96);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.isEmpty()) {
			g.drawImage(Card.getCardOutline(), 0, 0, 72, this.getHeight(), this);
		}else {
			g.drawImage(this.topCard().getCardImage(), 0, 0, 72, this.getHeight(), this);
		}
	}
}
