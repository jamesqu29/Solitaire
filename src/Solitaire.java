/*
Driver class
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Solitaire extends JFrame{
	
	static protected GamePanel gamePanel;
	public static final int PANEL_WIDTH = 700, PANEL_HEIGHT = 600;
	private static Score score = Score.getInstance();
	private static Solitaire solitaire_instance = null;
	
	

    public static void setSolitaire_instance(Solitaire solitaire_instance) {
        Solitaire.solitaire_instance = solitaire_instance;
    }


    //build GUI components
	private Solitaire() {
	    setTitle("Solitaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JButton newGameButton = new JButton("New Game");
		add(newGameButton,BorderLayout.SOUTH);
		newGameButton.setFocusable(false);
		JButton howToButton =  new JButton("How To Play");
		howToButton.setFocusable(false);
		add(howToButton,BorderLayout.NORTH);
		setVisible(true);
		
		
		
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		add(gamePanel);
		pack();//nothing will display without this method
		
		//listener for how to play button
		howToButton.addActionListener(e -> {
			new HowToWindow();
		});
		
	


		//listener for new game button
		newGameButton.addActionListener(e -> { //Java lambda notation
			setVisible(false);
			setSolitaire_instance(null);
			getInstance();
			score.setVegas_rules(false);
			score.resetScore();
		});
	}
	
	
	  public static Solitaire getInstance() {
	        
	        if (solitaire_instance == null) {
	            solitaire_instance = new Solitaire();
	            
	        }
	        
	        return solitaire_instance;
	     
	        
	        
	    }
	  
	  public void newGame(boolean vegas_rules) {
	      

	      setVisible(false);
	      setSolitaire_instance(null);
	      getInstance();
          score.setVegas_rules(true);
          score.resetScore();
          score.setScore(-52);
	      
	      
	  }

	public static void main(String[] args) {

		getInstance();

	}

}
