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

	//build GUI components
	public Solitaire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JButton newGameButton = new JButton("New Game");
		add(newGameButton,BorderLayout.SOUTH);
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		add(gamePanel);
		pack();//nothing will display without this method

		//listener for new game button
		newGameButton.addActionListener(e -> { //Java lambda notation
			setVisible(false);
			new Solitaire().setVisible(true);
		});
	}

	public static void main(String[] args) {

		new Solitaire().setVisible(true);

		if (gamePanel.isGameWon){
			//todo
		}
	}

}
