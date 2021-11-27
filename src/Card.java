/*
Base class - Card
 */
import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {

	protected enum Color{ //protected keyword for inherited class to use
		Red,Black;
	}

	protected enum Suit {
		Spades, Hearts, Clubs, Diamonds
	}
	public static String cardBackFilename = "back001",
			cardOutlineFilename = "bottom01",
			fpBaseFilename = "fpBase0";
	public static String directory = "cards", extension = ".gif";
	private Image im;
	private int value;
	private String suit;
	private boolean faceUp;
	private Card.Color colour;
	private Boolean hasBeenMoved = false;

	public Boolean getHasBeenMoved() {
        return hasBeenMoved;
    }

    public void setHasBeenMoved(Boolean hasBeenMoved) {
        this.hasBeenMoved = hasBeenMoved;
    }

    
    public Card(int value, Card.Suit suit) {
		this.value = value;
		switch(suit) {
		case Clubs:
			this.suit = "c";
			colour = Card.Color.Black;
			break;
		case Diamonds:
			this.suit = "d";
			colour = Card.Color.Red;
			break;
		case Spades:
			this.suit = "s";
			colour = Card.Color.Black;
			break;
		case Hearts:
			this.suit = "h";
			colour = Card.Color.Red;
			break;
		}
		
		faceUp = false;
		
		try {
			ImageIcon ii = new ImageIcon(getClass().getResource(directory + cardFile(suit, value)));
			im = ii.getImage();
		}catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private String cardFile(Card.Suit s, int val) {
		char ch;
		
		if (val < 1 || val > 13)
			throw new IllegalArgumentException("Bad Card Number");
		
		if(s == Card.Suit.Clubs) {
			ch = 'c';
		}else if(s == Card.Suit.Hearts) {
			ch = 'h';
		}else if(s == Card.Suit.Spades) {
			ch = 's';
		}else if(s == Card.Suit.Diamonds) {
			ch = 'd';
		}
		else throw new IllegalArgumentException("Bad Card Suit");
		
		if(val < 10)
			return "/0" + val + ch + extension;
		else
			return "/" + val + ch + extension;
	}

	public Image getCardImage() {
		return im;
	}


	public boolean isFaceUp() {
		return faceUp;
	}


	public Card.Color getColour() {
		return colour;
	}


	@Override
	public String toString() {
		return value + " of " + suit ;
	}
	
	public static Image getFoundationBase(int suit) { //get gif for the card
		ImageIcon ii = new ImageIcon(
				Card.class.getResource(directory + "/" + fpBaseFilename + suit + extension));
		Image image = ii.getImage();
		return image;
	}

	public static Image getCardOutline() {
		ImageIcon ii = new ImageIcon(
				Card.class.getResource(directory + "/" + cardOutlineFilename + extension));
		Image image = ii.getImage();
		return image;
	}

	public static Image getCardBack() {
		ImageIcon ii = new ImageIcon(
				Card.class.getResource(directory + "/" + cardBackFilename + extension));
		Image image = ii.getImage();
		return image;
	}

	public int getValue() {
		return value;
	}


	public String getSuit() {
		return suit;
	}


	public void showFace() {
		faceUp = true;
	}

}

