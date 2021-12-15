/*
JPanel putting everything together, initializing the game
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.*;
import javax.swing.border.Border;

public class GamePanel extends JPanel {

	public static final Color DARK_GREEN = new Color(0,102,0);
	protected static int XShift = 80;
	public static Point DECK_POSITION = new Point(500, 20);
	public static Point TABLEAU_POSITION = new Point(20, 150);
	public static Point TIMER_POSITION = new Point(585, 40);
	public static Point SCORE_POSITION = new Point(585, 140);
	public static Point MOVES_POSITION = new Point(585, 240);
	private static int TABLEAU_OFFSET = 80;

	private static DeckCardPile deckPile;
	private static DiscardCardPile discardPile;
	private static FoundationPile[] foundationPiles;
	private static TablePile[] tablePiles;
	private static GameTimer timer;
	private static Score score;
	private static MoveCounter moveCounter;
	private static JLabel timerTitle = new JLabel();
	private static JLabel scoreTitle = new JLabel();
	private static JLabel moveCounterTitle = new JLabel();
	
	

	
	
	//default constructor
	public GamePanel() {
		super.setLayout(null);
		setUIElements();
		initializePiles();
		GameMoveListener l = new GameMoveListener();
		addMouseListener(l);
		addMouseMotionListener(l);
	}

	
	private void setUIElements() {
	    
	    Color backgroundColor = new Color(0, 130, 10);
	    Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
	    
	    timer = new GameTimer(TIMER_POSITION.x, TIMER_POSITION.y);
        add(timer);
        timer.start();
        
        score = Score.getInstance();
        score.setBounds(SCORE_POSITION.x, SCORE_POSITION.y);
        add(score);
        
        moveCounter = MoveCounter.getInstance();
        moveCounter.setBounds(MOVES_POSITION.x, MOVES_POSITION.y);
        add(moveCounter);
        
        
        timerTitle.setBounds(585, 15, 100, 20);
        timerTitle.setText("Time");
        timerTitle.setFont(new Font("Verdana", Font.PLAIN, 15));
        timerTitle.setOpaque(true);
        timerTitle.setHorizontalAlignment(JTextField.CENTER);
        timerTitle.setBackground(backgroundColor);
        timerTitle.setBorder(border);
	    add(timerTitle);
	    
	    scoreTitle.setBounds(585, 115, 100, 20);
	    scoreTitle.setText("Score");
	    scoreTitle.setFont(new Font("Verdana", Font.PLAIN, 15));
	    scoreTitle.setOpaque(true);
	    scoreTitle.setHorizontalAlignment(JTextField.CENTER);
	    scoreTitle.setBackground(backgroundColor);
	    scoreTitle.setBorder(border);
        add(scoreTitle);
        
        
        moveCounterTitle.setBounds(585, 215, 100, 20);
        moveCounterTitle.setText("Moves");
        moveCounterTitle.setFont(new Font("Verdana", Font.PLAIN, 15));
        moveCounterTitle.setOpaque(true);
        moveCounterTitle.setHorizontalAlignment(JTextField.CENTER);
        moveCounterTitle.setBackground(backgroundColor);
        moveCounterTitle.setBorder(border);
        add(moveCounterTitle);
        
        
        JButton vegasRulesButton = new JButton("Vegas Rules");
        vegasRulesButton.setMargin(new Insets(0, 0, 0, 0));
        vegasRulesButton.setSize(100, 20);
        vegasRulesButton.setLocation(585, 340);
        vegasRulesButton.setFocusable(false);
        add(vegasRulesButton);
        
        
        vegasRulesButton.addActionListener(e ->{
           Solitaire solitaire = Solitaire.getInstance();
           solitaire.setVisible(false);
           solitaire.newGame(true);
           
        });
	    
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
