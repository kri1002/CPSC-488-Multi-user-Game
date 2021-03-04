import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Game extends JPanel{
	private static JButton lastButtonPressed;

    public static void main(String[] args){
    	JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(0, 10));
        JButton tile[] = new JButton[100]; 
       
        class tileClicked implements ActionListener{
        	 JButton currentButton=null;
        	 public void actionPerformed (ActionEvent e){
        		 JButton currentButton= (JButton)e.getSource(); //save which button pressed in variable
        		 //System.out.println(currentButton);
        		 
        		 if(lastButtonPressed!=null){//if two tiles were clicked
        			 if(lastButtonPressed !=currentButton){ //if different tiles clicked
        				 if(lastButtonPressed.getIcon()!=null&&currentButton.getIcon()==null){//if first tile had icon and second was empty
        					 //try{
        						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
        						//Image img = ImageIO.read(Game.class.getResource("/images/BlueCircle.png")); //will have to change image src to variable so it can work with any token
        						lastButtonPressed.setIcon(null); //sets the tile with the image null
        						currentButton.setIcon(tempImg/*new ImageIcon(img)*/); //move image to tile that was clicked
        						lastButtonPressed.revalidate(); //resets and updates button
        						currentButton.revalidate();     //resets and updates button
        			        	//}catch (IOException ex){}
        					currentButton=null; //resets the ActionListener
        					}
        				}	
        			}
        			lastButtonPressed=currentButton; //sets initial value for LBP
        		}
        	 }
        
        for(int i=0; i<100; i++) {//add the buttons to the frame
        	tile[i]= new JButton(); //create button
        	frame.add(tile[i]); //add to frame
        	tile[i].setBackground(Color.white); //set color of button
        	tile[i].addActionListener(new tileClicked()); //add listener to button
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
        
        
        //will need a method to call to create the players 
        //and decide their starting location based off of game mode
        Player Player1 = new Player(1, 4);
        Player1.createGamePieces(tile, "Blue", 10, 20, 0, 1, 2);
        Player Player2 = new Player(2, 3);
        Player2.createGamePieces(tile, "Red", 7, 8, 9, 19, 29);
        Player Player3 =new Player(3,2);
        Player3.createGamePieces(tile, "Green", 70, 80, 90, 91, 92);
        Player Player4 =new Player(4,1);
        Player4.createGamePieces(tile, "Yellow", 97, 98, 99, 89, 79);
       
    }  
  }

        
