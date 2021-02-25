import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Game extends JPanel{
	
	
    public static void main(String[] args){
    	JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(0, 10));
    	
        JButton tile[] = new JButton[100];
        for(int i=0; i<100; i++) {
        	tile[i]= new JButton();
        	frame.add(tile[i]);
        	tile[i].setBackground(Color.white);
        	
        }
        frame.setSize(600,600);
        
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
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        try {
        Image img = ImageIO.read(Game.class.getResource("/images/BlueCircle.png"));
        tile[22].setIcon(new ImageIcon(img));
        }catch (IOException ex) {}
        
        
        
        
    }  
    
   
}