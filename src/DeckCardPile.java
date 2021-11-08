/*
Deck card pile class - inherited cardPile class for building deck pile
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;

public class DeckCardPile extends CardPile {

	public DeckCardPile(int x, int y) {
		super(x, y);
		super.setSize(72, 96);
		for(Card.Suit suit : Card.Suit.values()) {
			for(int j = 1; j <= 13; ++j) {
				push(new Card(j, suit));
			}
		}
		
		Collections.shuffle(cards);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, 72, this.getHeight());


		if (!isEmpty()) {
			g.drawImage(Card.getCardBack(), 0, 0, 72, this.getHeight(), this);
		}
	}

}
