import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer extends JLabel implements ActionListener {

    JFrame frame = new JFrame();
    
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    
    boolean started = false;
    //if empty will be represented as "00" 
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    
    //Timer will update every 1 second/1000 milliseconds
    Timer timer = new Timer(1000, new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            
            elapsedTime+=1000;
            hours = (elapsedTime/3600000);
            //%60 because we don't want them to display 60
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            
            updateTimer(seconds, minutes, hours);
        }
        
    });
    
    public void updateTimer(int seconds, int minutes, int hours) {
        
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        
        super.setText(hours_string+":"+minutes_string+":"+seconds_string);
        
    }
    
    public GameTimer(int x, int y) {
        
        
        super.setText(hours_string+":"+minutes_string+":"+seconds_string);
        super.setBounds(x, y, 100, 50);
        super.setFont(new Font("Verdana", Font.PLAIN, 15));
        super.setBorder(BorderFactory.createBevelBorder(1));
        super.setOpaque(true);
        super.setHorizontalAlignment(JTextField.CENTER);
        
       
      
        
    }
    
  

    void start() {
        timer.start();
    }
    
    void stop() {
        timer.stop();
        
    }
    
    void reset() {
        started = false;
        timer.stop();
        
        elapsedTime=0;
        seconds=0;
        minutes=0;
        hours=0;
        
        updateTimer(seconds, minutes, hours);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
