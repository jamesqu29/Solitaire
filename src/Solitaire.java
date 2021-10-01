
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import java.awt.event.*;
import java.util.Enumeration;

public class Solitaire {
	static public DeckPile deckPile;
	static public DiscardPile discardPile;
	static public TablePile tableau [ ];
	static public SuitPile suitPile [ ];
	static public CardPile allPiles [ ];
	private Frame window;

	static public void main (String [ ] args) {
		Solitaire world = new Solitaire();
	}

	public Solitaire () {
		window = new SolitareFrame();
		init();
		window.setVisible(true);
	}

	public void init () {
			// first allocate the arrays
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
			// then fill them in
		allPiles[0] = deckPile = new DeckPile(335, 30);
		allPiles[1] = discardPile = new DiscardPile(268, 30);
		for (int i = 0; i < 4; i++)
			allPiles[2+i] = suitPile[i] =
				new SuitPile(15 + (Card.width+10) * i, 30);
		for (int i = 0; i < 7; i++)
			allPiles[6+i] = tableau[i] =
				new TablePile(15 + (Card.width+5) * i, Card.height + 35, i+1); 
		}

	private class SolitareFrame extends Frame {

		private class RestartButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				init();
				window.repaint();
			}
		}

		private class MouseKeeper extends MouseAdapter {

			@Override
			public void mousePressed (MouseEvent e) {
				int x = e.getX();
				int y = e.getY();


				for (int i = 0; i < 13; i++) {
					if (allPiles[i].includes(x, y))
					{

						allPiles[i].select(x, y);
						repaint();

					}
				}
			}
		}
		
		public SolitareFrame() {
			setSize(600, 500);
			setTitle("Solitaire Game");
			
			 //allows the program to be closed when x icon is clicked
            addMouseListener (new MouseKeeper());
               addWindowListener(new WindowAdapter(){
                   public void windowClosing(WindowEvent we){
                     System.exit(0);
                   }
                 });
               
			addMouseListener (new MouseKeeper());
			Button restartButton = new Button("New Game");
			restartButton.addActionListener(new RestartButtonListener());
			add("South", restartButton);
		}

		public void paint(Graphics g) {
			for (int i = 0; i < 13; i++) { allPiles[i].display(g); }
		}
	}
}
