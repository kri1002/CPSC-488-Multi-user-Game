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
        
        try {//set initial icon locations
        Image BCircleImg = ImageIO.read(Game.class.getResource("/images/BlueCircle.png")); 
        tile[0].setIcon(new ImageIcon(BCircleImg));
        Image BTriangleImg = ImageIO.read(Game.class.getResource("/images/BlueTriangle.png"));
        tile[1].setIcon(new ImageIcon(BTriangleImg));
        Image BStarImg = ImageIO.read(Game.class.getResource("/images/BlueStar.png"));
        tile[2].setIcon(new ImageIcon(BStarImg));
        Image BPentagonImg = ImageIO.read(Game.class.getResource("/images/BluePentagon.png"));
        tile[3].setIcon(new ImageIcon(BPentagonImg));
        Image BSquareImg = ImageIO.read(Game.class.getResource("/images/BlueSquare.png"));
        tile[4].setIcon(new ImageIcon(BSquareImg));
        
        Image GCircleImg = ImageIO.read(Game.class.getResource("/images/GreenCircle.png"));
        tile[99].setIcon(new ImageIcon(GCircleImg));
        Image GTriangleImg = ImageIO.read(Game.class.getResource("/images/GreenTriangle.png"));
        tile[98].setIcon(new ImageIcon(GTriangleImg));
        Image GStarImg = ImageIO.read(Game.class.getResource("/images/GreenStar.png"));
        tile[97].setIcon(new ImageIcon(GStarImg));
        Image GPentagonImg = ImageIO.read(Game.class.getResource("/images/GreenPentagon.png"));
        tile[96].setIcon(new ImageIcon(GPentagonImg));
        Image GSquareImg = ImageIO.read(Game.class.getResource("/images/GreenSquare.png"));
        tile[95].setIcon(new ImageIcon(GSquareImg));
        
        Image YCircleImg = ImageIO.read(Game.class.getResource("/images/YellowCircle.png"));
        tile[90].setIcon(new ImageIcon(YCircleImg));
        Image YTriangleImg = ImageIO.read(Game.class.getResource("/images/YellowTriangle.png"));
        tile[91].setIcon(new ImageIcon(YTriangleImg));
        Image YStarImg = ImageIO.read(Game.class.getResource("/images/YellowStar.png"));
        tile[92].setIcon(new ImageIcon(YStarImg));
        Image YPentagonImg = ImageIO.read(Game.class.getResource("/images/YellowPentagon.png"));
        tile[93].setIcon(new ImageIcon(YPentagonImg));
        Image YSquareImg = ImageIO.read(Game.class.getResource("/images/YellowSquare.png"));
        tile[94].setIcon(new ImageIcon(YSquareImg));
        
        Image RCircleImg = ImageIO.read(Game.class.getResource("/images/RedCircle.png"));
        tile[9].setIcon(new ImageIcon(RCircleImg));
        Image RTriangleImg = ImageIO.read(Game.class.getResource("/images/RedTriangle.png"));
        tile[8].setIcon(new ImageIcon(RTriangleImg));
        Image RStarImg = ImageIO.read(Game.class.getResource("/images/RedStar.png"));
        tile[7].setIcon(new ImageIcon(RStarImg));
        Image RPentagonImg = ImageIO.read(Game.class.getResource("/images/RedPentagon.png"));
        tile[6].setIcon(new ImageIcon(RPentagonImg));
        Image RSquareImg = ImageIO.read(Game.class.getResource("/images/RedSquare.png"));
        tile[5].setIcon(new ImageIcon(RSquareImg));
        }catch (IOException ex) {} //This will eventually need to be taken care of by a function so it can load the choices of the user
        
    }  
  }

        
