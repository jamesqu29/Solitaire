/*
class for listening to mouse click, drag and release for simulating drag and drop gesture
 */
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

//class to manage mouse click, drag and drop
public class GameMoveListener extends MouseInputAdapter {

	private DeckCardPile deckPile = GamePanel.getDeck();
	private DiscardCardPile discardPile = null;
	private TablePile selectedTableau = null;
	private FoundationPile selectedFoundationPile = null;
	private Card selectedCard = null;
	private boolean isGameWon = false;
	private static Score score = Score.getInstance();
	private static Boolean vegas_rules = score.isVegas_rules();
	private static MoveCounter moveCounter = MoveCounter.getInstance();
	private boolean hasMovedToFoundation = false;
	
	public boolean isHasMovedToFoundation() {
        return hasMovedToFoundation;
    }

    public void setHasMovedToFoundation(boolean hasMovedToFoundation) {
        this.hasMovedToFoundation = hasMovedToFoundation;
    }

    //private static JEditorPane gameWinningMsg = new JEditorPane("text/html", "");
	protected static final JPanel table = new JPanel();
	private static JTextField statusBox = new JTextField();// status messages
	private int shuffle_count = 0;

	@Override
	public void mousePressed(MouseEvent e) {
	    if(isGameWon) {return;}
	    Boolean vegas_rules = score.isVegas_rules();
	    //resets this bool to allow the user to drag and drop a card i able to
	    setHasMovedToFoundation(false);
	    
	   
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
				moveCounter.addMove();
			}else if(deckPile.isEmpty()){ //otherwise, put the discard pile back to deck pile in the same order it was discarded
				DiscardCardPile discardPile = GamePanel.getDiscardPile();
				moveCounter.addMove();
				while (!discardPile.isEmpty()){
					deckPile.push(discardPile.pop());
				}
				
				if(vegas_rules) {
				    //Game will end here for Vegas Rules since we have opted to go with the Draw 1 game mode.
				    isGameWon = true;
				    JOptionPane.showMessageDialog(table,"Game Over!"+"\n"+"Your Score: "+score.getScore()+ "\nPress New Game/Vegas Rule to start over");
		            statusBox.setText("Click New Game to Start Over");
		            
				}
				
				shuffle_count++;
				System.out.println("Shuffle Count: "+shuffle_count);
				if(shuffle_count > 4) {
				    System.out.println("Deducted points for shuffling more than 4 times");
				    score.removePoints(20);
				}
			}

		}else if(pressedComponent instanceof DiscardCardPile) { //if selected discard pile
			System.out.println("clicked on Discard pile");
			selectedTableau = null;
			discardPile = GamePanel.getDiscardPile();
			selectedCard = discardPile.topCard();
			if(selectedCard != null) { //check to see if foundation pile can take cards from the discard pile
			    
			  
			    
				for(FoundationPile foundationPile : GamePanel.getFoundationPiles()) {
				    
					if(foundationPile.moveFromWaste(discardPile, selectedCard)) {
					    //if the the card has been moved to foundation. This bool will prevent the card underneath it from being accidentally dragged onto the tableau
					    setHasMovedToFoundation(true);
					}
					
					
					 
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
			JOptionPane.showMessageDialog(table,"Congratulations! You've Won!"+"\n"+"Your Score: "+score.getScore());
			statusBox.setText("Click New Game to Start Over");
			
		}

		e.getComponent().repaint(); //every time the mouse is clicked, repaint
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	    
	    //The statement directly below means that the player has clicked on the card in question on sent it to foundation. This overrides the drag n' drop feature to prevent bugs.
	    if(isHasMovedToFoundation()) {return;}
	    if(isGameWon) {return;}
	    Boolean vegas_rules = score.isVegas_rules();
		if(selectedCard != null) {
			Component releasedComponent = e.getComponent().getComponentAt(e.getPoint());
			if(releasedComponent instanceof TablePile) { //if mouse is released on the table pile
				if(discardPile != null) { //if
				    

					TablePile destination = (TablePile) releasedComponent;
					
					if(!discardPile.isEmpty()) {
						
					    //Only gives points if the card actually moved
                        if(destination.accepts(selectedCard)) {
                            System.out.println("Moved from discard to a row stack 5 points");
                            selectedCard.setHasBeenMoved(true);
                            //same score for vegas rules in this case
                            score.addPoints(5);
                            moveCounter.addMove();
                          }
					    
					    destination.moveFromDiscard(discardPile, selectedCard);
					  
						
					}
					
					
					
					
					discardPile.repaint();

				}else if(selectedTableau != null) {
					TablePile source = selectedTableau;
					TablePile destination = (TablePile) releasedComponent;
					
					//Prevents points from being accrued if the card has already been moved within the tableau pile
					source.moveTo(destination, selectedCard);
					if(source != destination) {
					    if(selectedCard.getHasBeenMoved() == false) { 
					        System.out.println("Moved from one row stack to another 3 points"); 
					        
					            if(!vegas_rules) {
					            score.addPoints(3);
					            }
					            
					        moveCounter.addMove();
					        selectedCard.setHasBeenMoved(true);
					        }}
					
					source.repaint();
					
				}else if(selectedFoundationPile != null) {
					FoundationPile source = selectedFoundationPile;
					TablePile destination = (TablePile) releasedComponent;
					source.moveTo(destination, selectedCard);
					source.repaint();
					destination.repaint();
					//same score for vegas rules
					System.out.println("Moved from foundation to tableau -15 points");
					score.removePoints(15);
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
