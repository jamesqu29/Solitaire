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
	private static MoveCounter moves = MoveCounter.getInstance();
	
	//build GUI components
	public Solitaire() {
	    setTitle("Solitaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JButton newGameButton = new JButton("New Game");
		add(newGameButton,BorderLayout.SOUTH);
		newGameButton.setFocusable(false);
		JButton howToButton =  new JButton("How To Play");
		howToButton.setFocusable(false);
		add(howToButton,BorderLayout.NORTH);
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
			new Solitaire().setVisible(true);
			score.resetScore();
			moves.resetMoves();
		});
	}

	public static void main(String[] args) {

		new Solitaire().setVisible(true);

	}

}
