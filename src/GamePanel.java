/*
JPanel putting everything together, initializing the game
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.*;

public class GamePanel extends JPanel {

	public static final Color DARK_GREEN = new Color(0,102,0);
	protected static int XShift = 80;
	public static Point DECK_POSITION = new Point(500, 20);
	public static Point TABLEAU_POSITION = new Point(20, 150);
	private static int TABLEAU_OFFSET = 80;
	private static DeckCardPile deckPile;
	private static DiscardCardPile discardPile;
	private static FoundationPile[] foundationPiles;
	private static TablePile[] tablePiles;
	public boolean isGameWon;

	//default constructor
	public GamePanel() {
		super.setLayout(null);
		initializePiles();
		
		GameMoveListener l = new GameMoveListener();
		addMouseListener(l);
		addMouseMotionListener(l);
	}

	//initialize the piles
	private void initializePiles() {
		deckPile = new DeckCardPile(DECK_POSITION.x, DECK_POSITION.y);
		add(deckPile);
		discardPile = new DiscardCardPile(DECK_POSITION.x - XShift, DECK_POSITION.y);
		add(discardPile);
		foundationPiles = new FoundationPile[4];
		for(int i = 0; i < foundationPiles.length; ++i) {
			foundationPiles[i] = new FoundationPile(20 + XShift * i, 20, i + 1);
			add(foundationPiles[i]);
		}
		tablePiles = new TablePile[7];
		for(int tableauIndex = 1; tableauIndex <= tablePiles.length; ++tableauIndex) {
			tablePiles[tableauIndex -1] = new TablePile(TABLEAU_POSITION.x + TABLEAU_OFFSET * (tableauIndex -1), TABLEAU_POSITION.y, tableauIndex);
			add(tablePiles[tableauIndex -1]);
		}
	}

	public static FoundationPile[] getFoundationPiles() {
		return foundationPiles;
	}

	public static DiscardCardPile getDiscardPile() {
		return discardPile;
	}

	public static DeckCardPile getDeck() {
		return deckPile;
	}

	public static TablePile[] getTablePiles() {return tablePiles;}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(DARK_GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
