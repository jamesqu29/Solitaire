/*
class for listening to mouse click, drag and release for simulating drag and drop gesture
 */
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

//class to manage mouse click, drag and drop
public class GameMoveListener extends MouseInputAdapter {

	private DeckCardPile deckPile = GamePanel.getDeck();
	private DiscardCardPile discardPile = null;
	private TablePile selectedTableau = null;
	private FoundationPile selectedFoundationPile = null;
	private Card selectedCard = null;
	private boolean isGameWon = false;

	@Override
	public void mousePressed(MouseEvent e) {
		Component pressedComponent = e.getComponent().getComponentAt(e.getPoint());
		if(pressedComponent instanceof FoundationPile) { //if clicked on foundation pile
			selectedFoundationPile = (FoundationPile) pressedComponent;
			System.out.println("clicked on Foundation pile");
			selectedTableau = null;
			discardPile = null;
			selectedCard = selectedFoundationPile.topCard();

		}else if(pressedComponent instanceof TablePile) { //if selected tableau
			selectedTableau = (TablePile) pressedComponent;
			System.out.println("clicked on Tableau pile");
			discardPile = null;
			try{ //this is for catching the index out of bound error when the table pile is empty
				selectedCard = selectedTableau.getClickedCard(e.getY() - 150);
				for(FoundationPile foundationPile : GamePanel.getFoundationPiles()) {
					if(selectedTableau.moveTo(foundationPile, selectedCard)) {
						selectedTableau = null;
						break;
					}
				}
			}catch (IndexOutOfBoundsException error){
				System.out.println(error);
			}

		}else if(pressedComponent instanceof DeckCardPile) { //if selected deck card
			selectedTableau = null;
			System.out.println("clicked on Deck pile");
			if(!deckPile.isEmpty()) { //if deck pile is not empty
				DiscardCardPile discardPile = GamePanel.getDiscardPile();
				discardPile.push(deckPile.pop());
				discardPile.topCard().showFace();
			}else if(deckPile.isEmpty()){ //otherwise, put the discard pile back to deck pile in the same order it was discarded
				DiscardCardPile discardPile = GamePanel.getDiscardPile();
				while (!discardPile.isEmpty()){
					deckPile.push(discardPile.pop());
				}
			}

		}else if(pressedComponent instanceof DiscardCardPile) { //if selected discard pile
			System.out.println("clicked on Discard pile");
			selectedTableau = null;
			discardPile = GamePanel.getDiscardPile();
			selectedCard = discardPile.topCard();
			if(selectedCard != null) { //check to see if foundation pile can take cards from the discard pile
				for(FoundationPile foundationPile : GamePanel.getFoundationPiles()) {
					foundationPile.moveFromWaste(discardPile, selectedCard);
				}
			}
		}

		//check if the game is won, if it is, do something
		int count = 0;
		for (TablePile tp : GamePanel.getTablePiles()){
			if(tp.isEmpty()){
				count+=1;
			}
		}
		if (count == 7) {
			isGameWon = true;
			System.out.println(isGameWon);
		}

		e.getComponent().repaint(); //every time the mouse is clicked, repaint
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(selectedCard != null) {
			Component releasedComponent = e.getComponent().getComponentAt(e.getPoint());
			if(releasedComponent instanceof TablePile) { //if mouse is released on the table pile
				if(discardPile != null) { //if
					TablePile destination = (TablePile) releasedComponent;
					if(!discardPile.isEmpty()) {
						destination.moveFromDiscard(discardPile, selectedCard);
					}
					discardPile.repaint();

				}else if(selectedTableau != null) {
					TablePile source = selectedTableau;
					TablePile destination = (TablePile) releasedComponent;
					source.moveTo(destination, selectedCard);
					source.repaint();
				}else if(selectedFoundationPile != null) {
					FoundationPile source = selectedFoundationPile;
					TablePile destination = (TablePile) releasedComponent;
					source.moveTo(destination, selectedCard);
					source.repaint();
					destination.repaint();
				}
			}
		}
		//otherwise, if selected card is null
		e.getComponent().repaint();
		selectedCard = null;
		selectedFoundationPile = null;
		selectedTableau = null;
		discardPile = null;
	}
}
