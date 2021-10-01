import java.awt.*;

public class Card {
	
	// public constants for card width and suits
	final public static int width = 50;
	final public static int height = 70;
	final public static int heart = 0;
	final public static int spade = 1;
	final public static int diamond = 2;
	final public static int club = 3;
	
	// internal data fields for rank and suit
	private boolean faceup;
	private int rank;
	private int suit;

	// constructor
	Card (int sv, int rv) { //provide code here
		suit = sv;
		rank = rv;
		faceup = false;
	}

	// access attributes of card
	public int rank () {
		return rank; //provide code here
		
	}

	public int suit() { //provide code here 
		return suit;
	}

	public boolean faceUp()	{ //provide code here 
		return faceup;
	}

	public void flip() { //provide code here 
		faceup = !faceup;
	}

	public Color color() {//provide code here
		if (faceUp())
			if (suit() == heart || suit() == diamond)
				return Color.red;
			else
				return Color.black;
		return Color.ORANGE;
	}

	public void draw (Graphics g, int x, int y) {
		String names[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
 		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
		g.setColor(Color.blue);
		g.drawRect(x, y, width, height);
		
		// draw body of card
		g.setColor(color());
		if (faceUp()) {
			g.drawString(names[rank()], x+3, y+15); //draw rank
			
			if (suit() == heart) {
				g.drawLine(x+25, y+30, x+35, y+20);
				g.drawLine(x+35, y+20, x+45, y+30);
				g.drawLine(x+45, y+30, x+25, y+60);
				g.drawLine(x+25, y+60, x+5, y+30);
				g.drawLine(x+5, y+30, x+15, y+20);
				g.drawLine(x+15, y+20, x+25, y+30);
			}
			else if (suit() == spade) {
				g.drawLine(x+25, y+20, x+40, y+50);
				g.drawLine(x+40, y+50, x+10, y+50);
				g.drawLine(x+10, y+50, x+25, y+20);
				g.drawLine(x+23, y+45, x+20, y+60);
				g.drawLine(x+20, y+60, x+30, y+60);
				g.drawLine(x+30, y+60, x+27, y+45); 
			}
			else if (suit() == diamond) {
				g.drawLine(x+25, y+20, x+40, y+40);
				g.drawLine(x+40, y+40, x+25, y+60);
				g.drawLine(x+25, y+60, x+10, y+40);
				g.drawLine(x+10, y+40, x+25, y+20);
			}
			else if (suit() == club) {
				g.drawOval(x+20, y+25, 10, 10);
				g.drawOval(x+25, y+35, 10, 10);
				g.drawOval(x+15, y+35, 10, 10);
				g.drawLine(x+23, y+45, x+20, y+55);
				g.drawLine(x+20, y+55, x+30, y+55);
				g.drawLine(x+30, y+55, x+27, y+45); 
			}
		}
		else { // face down
			g.drawLine(x+15, y+5, x+15, y+65);
			g.drawLine(x+25, y+5, x+25, y+65);
			g.drawLine(x+35, y+5, x+35, y+65);
			g.drawLine(x+5, y+20, x+45, y+20);
			g.drawLine(x+5, y+35, x+45, y+35);
			g.drawLine(x+5, y+50, x+45, y+50);
		}
	}
}