import java.awt.*;
import javax.swing.*;
public class Game extends JPanel{
    
public void paint(Graphics g){ //creates game board
		int height=45;
		int width=45;
		int x =105;
        int y =105;
	
        g.setColor(Color.black);
        g.fillRect(100, 100, 505, 505); //creates rectangle to put the tiles on
        
       // g.clearRect(105, 105, 45, 45); initial tile
     
        
        for(int i=0; i<10; i++) { //make tiles
        	for(int j=0; j<10; j++){
        		g.clearRect(x, y, width, height);
        		x=x+50;
        	}
        	x=105; //there probably is a better way to do this
        	y=y+50;
        }
        	
        }
       
    public static void main(String[] args){
    	JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.getContentPane().add(new Game());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }  
}