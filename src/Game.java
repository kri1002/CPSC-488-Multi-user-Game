import java.awt.*;
import java.awt.event.*;
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
        
        JMenuBar gameMenuBar = new JMenuBar(); //create menu bar
        frame.setJMenuBar(gameMenuBar); //mount it onto frame
        JMenu options = new JMenu("Options"); //create "options" option
        gameMenuBar.add(options); //mount it onto menu bar
        JMenuItem key = new JMenuItem("Key"); //create and name options within "options"
        JMenuItem endturn = new JMenuItem("End Turn");
        JMenuItem exit = new JMenuItem("Exit");
        options.add(key); //put them into "options" within menu bar
        options.add(endturn);
        options.add(exit);
        class exitAction implements ActionListener{ //allow "exit" to close program on click
    		public void actionPerformed (ActionEvent e) {
    			System.exit(0);
    		}
    	}
        class keyAction implements ActionListener{ //placeholder for key window 
        	public void actionPerformed (ActionEvent e) {
        		System.out.println("Placeholder for key");
        	}
        }
        class endturnAction implements ActionListener{ //placeholder for endturn
        	public void actionPerformed (ActionEvent e) {
        		System.out.println("Placeholder for endturn function");
        	}
        }
        exit.addActionListener(new exitAction()); //add action listeners to menu bar
        key.addActionListener(new keyAction());
        endturn.addActionListener(new endturnAction());
        
    }  
}