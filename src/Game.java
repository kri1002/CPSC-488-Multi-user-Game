import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Game extends JPanel{
	private static JButton lastButtonPressed;
	
	//will need something outside of this GUI to keep track of game mode
	//so can determine player order etc
	public static void createGui1(JFrame frame1, JFrame frame2, JFrame frame3){ //game selection
		JButton gameMode[]= new JButton[4];
		  frame1.setLayout(new GridLayout(4, 0));
		
		   class buttonClicked implements ActionListener{
			   JButton currentButton=null;
			   int numPlayers=0; //have to know how many players for next guis
	        	 public void actionPerformed (ActionEvent e){
	        		 JButton currentButton= (JButton)e.getSource(); //save which button pressed in variable
	        		 //sets how many players and the calls next GUI screen depending on button pressed
	        		 if(currentButton==gameMode[0]) {
	        			 System.out.println("1 Player vs 1 Player");
	        			  numPlayers= 2;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        			  }
	        		 else if(currentButton==gameMode[1]) {
	        			 System.out.println("1 Player vs 1 Computer Player");
	        			 numPlayers= 2;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else if(currentButton==gameMode[2]) {
	        			System.out.println("Two-Verus-Two Team Battle");
	        			numPlayers=4;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else {
	        			 System.out.println("4 Player Free-for-All Game");
	        			 numPlayers=4;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        			
	        	 }
	         }
		 
		   for(int i=0; i<4; i++) {//add the buttons to the frame
	       	gameMode[i]= new JButton(); //create button
	       	frame1.add(gameMode[i]); //add to frame
	       	gameMode[i].setBackground(Color.white); //set color of button
	       	gameMode[i].addActionListener(new buttonClicked()); //add listener to button
	       }
		  
		   //put what each option is on the buttons
		   gameMode[0].setText("1 Player vs 1 Player");
		   gameMode[1].setText("1 Player vs 1 Computer Player");
		   gameMode[2].setText("Two-Verus-Two Team Battle");
		   gameMode[3].setText("4 Player Free-for-All Game");
		 
		   //finish creating GUI
		   frame1.setSize(600,600);
		   frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame1.setVisible(true);    
	}
	
	public static void createGui2(JFrame frame1, JFrame frame2, JFrame frame3, int numPlayers) {//team selection
		frame1.dispose(); //get rid of previous gui and create new one
		JButton teams[]= new JButton[4];
		  frame2.setLayout(new GridLayout(4, 0));
		
		  
		  //will have to keep track of num of players after each click, after there's no more players to select team go to next screen
		   class buttonClicked implements ActionListener{
			   JButton currentButton=null;
	        	 public void actionPerformed (ActionEvent e){
	        		 JButton currentButton= (JButton)e.getSource(); //save which button pressed in variable
	        		 if(currentButton==teams[0]) {
	        			 System.out.println("Basic");
	        		 }
	        		 else if(currentButton==teams[1]) {
	        			 System.out.println("Reckless");
	        		 }
	        		 else if(currentButton==teams[2]) {
	        			System.out.println("Fast");
	        		 }
	        		 else {
	        			 System.out.println("DPS");
	        		 }
	        	 	
	        	 }
	        	 }
		  
		  
		   for(int i=0; i<4; i++) {//add the buttons to the frame
	       	teams[i]= new JButton(); //create button
	       	frame2.add(teams[i]); //add to frame
	       	teams[i].setBackground(Color.white); //set color of button
	       teams[i].addActionListener(new buttonClicked()); //add listener to button
	       }
		   
		   //put what each option is on the buttons
		   teams[0].setText("Basic-warrior, ranger, rogue, healer, damage mage");
		   teams[1].setText("Reckless-2 warriors, 1 ranger, 1 rogue, 1 damage mage");
		   teams[2].setText("Fast- 2 rangers, 2 rogues, 1 healer");
		   teams[3].setText("DPS- 2 warriors, 2 damage mages, 1 healer");
		  
		   //finish making frame
		   frame2.setSize(600,600);
		   frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame2.setVisible(true);
	}
	

	public static void createGui3(JFrame frame2, JFrame frame3,int numPlayers) {
		frame2.dispose();
        frame3.setLayout(new GridLayout(0, 10));
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
        	frame3.add(tile[i]); //add to frame
        	tile[i].setBackground(Color.white); //set color of button
        	tile[i].addActionListener(new tileClicked()); //add listener to button
        }
        
        frame3.setSize(600,600);
        JMenuBar gameMenuBar = new JMenuBar(); //create menu bar
        frame3.setJMenuBar(gameMenuBar); //mount it onto frame
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
        
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setVisible(true);
        
        //have to finally create the game pieces based off of previous GUI selections
        Player Player1 = new Player(1, 4);
        Player1.createGamePieces(tile, "Blue", 10, 20, 0, 1, 2);
        Player Player2 = new Player(2, 3);
        Player2.createGamePieces(tile, "Red", 7, 8, 9, 19, 29);
        Player Player3 =new Player(3,2);
        Player3.createGamePieces(tile, "Green", 70, 80, 90, 91, 92);
        Player Player4 =new Player(4,1);
        Player4.createGamePieces(tile, "Yellow", 97, 98, 99, 89, 79);
        
	}
	
    public static void main(String[] args){
    	JFrame frame1= new JFrame();
    	JFrame frame2= new JFrame();
    	JFrame frame3= new JFrame();
    	createGui1(frame1, frame2, frame3);
    	//createGui3(frame2, frame3, 4);
    }  
  }

        
