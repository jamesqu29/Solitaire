import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MoveCounter extends JLabel {
    
    private int moves = 0;
    
    private static MoveCounter moves_instance = null;
    
    
    
    private MoveCounter() {
        
    super.setText(String.valueOf(moves));
    
    super.setFont(new Font("Verdana", Font.PLAIN, 15));
    super.setBorder(BorderFactory.createBevelBorder(1));
    super.setOpaque(true);
    super.setHorizontalAlignment(JTextField.CENTER);
    
    
    
    }
    
    
    
    public int getMoves() {
        return moves;
    }






    public void setMoves(int moves) {
        this.moves = moves;
    }

    
    
    
    
    public void addMove() {
        
        moves += 1;
        setMoves(moves);
        updateMoves(moves);
        
    }
    
  
    
    public void updateMoves(int moves) {
        
        super.setText(String.valueOf(moves));
        
    }
    public void resetMoves(){
        moves = 0;
        updateMoves(moves);
    }
    
    public void setBounds(int x, int y) {
        
        super.setBounds(x, y, 100, 50);
    }
    
    public static MoveCounter getInstance() {
        
        if (moves_instance == null) {
            moves_instance = new MoveCounter();
            
        }
        
        return moves_instance;
    }
}
