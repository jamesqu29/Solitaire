import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class DragPanel extends JPanel{
    

    boolean dragValid = false;
    
    ImageIcon image;
    int HEIGHT;
    int WIDTH;
    Point imageCorner;
    Point prevPt;
    
    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }


   
    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int wIDTH) {
        WIDTH = wIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int hEIGHT) {
        HEIGHT = hEIGHT;
    }

    public Point getImageCorner() {
        return imageCorner;
    }

    public void setImageCorner(Point imageCorner) {
        this.imageCorner = imageCorner;
    }

    public Point getPrevPt() {
        return prevPt;
    }

    public void setPrevPt(Point prevPt) {
        this.prevPt = prevPt;
    }


   
    
   
    DragPanel(Image i){
        
    
    System.out.println(i);
    imageCorner = new Point(0, 0);
    setImageCorner(imageCorner);
    image = new ImageIcon(i);
    setImage(image);
    WIDTH = image.getIconWidth();
    setWIDTH(WIDTH);
    HEIGHT = image.getIconHeight();
    setHEIGHT(HEIGHT);
    
    /*
    this.setVisible(true);
    super.setBounds(imageCorner.x, imageCorner.y, WIDTH, HEIGHT);
    */
    
    ClickListener clickListener = new ClickListener();
    DragListener dragListener = new DragListener();
    
    this.addMouseListener(clickListener);
    this.addMouseMotionListener(dragListener);
    
    }
    
    /*
    DragPanel(){
        image = null;
        WIDTH = 0;
        HEIGHT = 0;
        imageCorner = new Point(0, 0);
        
        
        
    }
    
    */
    
public void paintComponent(Graphics g) {
        
        
    
   
        super.paintComponent(g);
        image.paintIcon(this, g, (int)imageCorner.x, (int)imageCorner.y);}
        
        
        
    
    
private class ClickListener extends MouseAdapter{
    public void mousePressed(MouseEvent e) {  
            prevPt = e.getPoint(); 
            dragValid = (e.getPoint().getX() > imageCorner.getX()) && 
                    (e.getPoint().getX() < (imageCorner.getX() + WIDTH)) &&
                    (e.getPoint().getY() > imageCorner.getY()) &&
                    (e.getPoint().getY() < (imageCorner.getY() + HEIGHT));
    }
}
    
    private class DragListener extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e) { 
                if(dragValid){
                    Point currentPt = e.getPoint();    
                    imageCorner.translate(
                            (int)(currentPt.getX() - prevPt.getX()),
                            (int)(currentPt.getY() - prevPt.getY())
                    );
                    prevPt = currentPt;
                    repaint();
                }
        }
    }
    
    
    
}
