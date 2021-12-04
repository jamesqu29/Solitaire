import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HowToWindow extends JFrame {

    public static final Color DARK_GREEN = new Color(0,102,0);

    JFrame frame = new JFrame("How To Play");
    JLabel label = new JLabel("How To Play Solitaire", JLabel.CENTER);
    JLabel instructions = new JLabel("<html><u>How To</u><br>For full instructions and rules on how to play the " +
            "Solitaire game, click the link below:</html>");
    JLabel link = new JLabel("https://bicyclecards.com/how-to-play/solitaire/");
    JLabel howto = new JLabel("<html><u>How To Play This Version</u><br>Cards in the Tableau piles that have a " +
            "move to a Foundation pile can be single clicked to automatically be placed in the correct position." +
            "<br><br>Cards in the Deck pile that have a move to a Foundation pile can also be single clicked to " +
            "automatically be placed in the correct position.<br><br>To move cards from Foundation pile to Foundation" +
            " pile, or from the Deck pile to a Foundation pile, click, hold, and drag and drop the the desired " +
            "position.</html>");

    private static HowToWindow instance;

    public static HowToWindow getInstance() {
        if (instance == null){
            instance = new HowToWindow();
        }
        return instance;
    }

    HowToWindow(){
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setBounds(150,0,200,25);
        label.setForeground(Color.white);
        frame.add(label);

        instructions.setBounds(0,15,500,30);
        instructions.setForeground(Color.white);
        frame.add(instructions);

        link.setBounds(0,45,500,15);
        link.setForeground(Color.red);
        link.setFont(new Font(null, Font.PLAIN, 15));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new
                            URI("https://bicyclecards.com/how-to-play/solitaire/"));
            }
                catch (IOException | URISyntaxException e1){
                    e1.printStackTrace();
                }
        }});
        frame.add(link);

        howto.setBounds(0,70,500, 150);
        howto.setForeground(Color.white);
        frame.add(howto);

        frame.setSize(525,300);
        frame.getContentPane().setBackground(DARK_GREEN);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
