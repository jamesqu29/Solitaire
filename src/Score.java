import java.awt.*;
import javax.swing.*;

public class Score extends JLabel {

    private int score = 0;
    private static Score score_instance = null;
    private boolean vegas_rules = false;
    
  

    private Score() {
        

        super.setText(String.valueOf(score));
      
        super.setFont(new Font("Verdana", Font.PLAIN, 15));
        super.setBorder(BorderFactory.createBevelBorder(1));
        super.setOpaque(true);
        super.setHorizontalAlignment(JTextField.CENTER);
        
        
   
        
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        updateScore(score);
    }
    
    public void resetScore() {
        score = 0;
        updateScore(score);
        
        
    }

    public void addPoints(int points) {
        
        score += points;
        setScore(score);
        updateScore(score);
        
    }
    
    public void removePoints(int points) {
        
        score -= points;
        setScore(score);
        updateScore(score);
        
    }
    
    public void updateScore(int score) {
        
        super.setText(String.valueOf(score));
        
    }
    
    public void setBounds(int x, int y) {
        
        super.setBounds(x, y, 100, 50);
    }
    
    public static Score getInstance() {
        
        if (score_instance == null) {
            score_instance = new Score();
            
        }
        
        return score_instance;
        
        
        
    }
    
    public boolean isVegas_rules() {
        return vegas_rules;
    }

    public void setVegas_rules(boolean vegas_rules) {
        this.vegas_rules = vegas_rules;
        System.out.println("Vegas Rules Enabled");
    }
    
}
