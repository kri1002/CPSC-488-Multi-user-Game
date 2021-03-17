import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Game extends JPanel{
	private static JButton lastButtonPressed;
	public static int timesClicked = 0;//I feel like there is a better place to put this, but I couldnt get it to work anywhere else
	public static int pieceXCoord; //Coordinates for the piece to be used in the action listener
 	public static int pieceYCoord;
 	public static int destinationXCoord; //coordinates for the destination space to be used in the action listener
 	public static int destinationYCoord;
	public static Player Player1 = new Player(1);//players are now generated at the start of the game class so they can be referenced anywhere
	public static Player Player2 = new Player(2);
	public static Player Player3 = new Player(3);
	public static Player Player4 = new Player(4);
	public static JButton move =new JButton("Move"); //buttons to take actions in the game
    public static JButton attack =new JButton("Attack");
    public static JButton heal =new JButton("Heal"); 
	public static JButton cancel =new JButton("Cancel Action");
	
	//will need something outside of this GUI to keep track of game mode
	//so can determine player order etc
	
	public static void swapImage(JButton currentButton, Icon tempImg) { //swaps the images on the tiles
		lastButtonPressed.setIcon(null); //sets the tile with the image null
		currentButton.setIcon(tempImg); //move image to tile that was clicked
		lastButtonPressed.revalidate(); //resets and updates button
		currentButton.revalidate(); 
	}
	
	public static ImageIcon returnGamePiece(Icon tempImg2){//was trying to use this to figure out the second game piece without having to put a bunch of ifs in the attack piece
		if(tempImg2==Player1.PieceImages[0]) {
			return Player1.PieceImages[0];
		}
		if(tempImg2==Player1.PieceImages[1]) {
			return Player1.PieceImages[1];
		}
		if(tempImg2==Player1.PieceImages[2]) {
			return Player1.PieceImages[2];
		}
		if(tempImg2==Player1.PieceImages[3]) {
			return Player1.PieceImages[3];
		}
		if(tempImg2==Player1.PieceImages[4]) {
			return Player1.PieceImages[4];
		}
		if(tempImg2==Player2.PieceImages[0]) {
			return Player2.PieceImages[0];
		}
		if(tempImg2==Player2.PieceImages[1]) {
			return Player2.PieceImages[1];
		}
		if(tempImg2==Player2.PieceImages[2]) {
			return Player2.PieceImages[2];
		}
		if(tempImg2==Player2.PieceImages[3]) {
			return Player2.PieceImages[3];
		}
		if(tempImg2==Player2.PieceImages[4]) {
			return Player2.PieceImages[4];
		}
		if(tempImg2==Player3.PieceImages[0]) {
			return Player3.PieceImages[0];
		}
		if(tempImg2==Player3.PieceImages[1]) {
			return Player3.PieceImages[1];
		}
		if(tempImg2==Player3.PieceImages[2]) {
			return Player3.PieceImages[2];
		}
		if(tempImg2==Player3.PieceImages[3]) {
			return Player3.PieceImages[3];
		}
		if(tempImg2==Player3.PieceImages[4]) {
			return Player3.PieceImages[4];
		}
		if(tempImg2==Player4.PieceImages[0]) {
			return Player4.PieceImages[0];
		}
		if(tempImg2==Player4.PieceImages[1]) {
			return Player4.PieceImages[1];
		}
		if(tempImg2==Player4.PieceImages[2]) {
			return Player4.PieceImages[2];
		}
		if(tempImg2==Player4.PieceImages[3]) {
			return Player4.PieceImages[3];
		}
		if(tempImg2==Player4.PieceImages[4]) {
			return Player4.PieceImages[4];
		
		}
		else {
			return null;
		}
	}
	
	
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
	        			 System.out.println("1 Player vs 1 Player game mode selected");
	        			  numPlayers= 2; 
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        			  }
	        		 else if(currentButton==gameMode[1]) {
	        			 System.out.println("1 Player vs 1 Computer Player game mode selected");
	        			 numPlayers= 2;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else if(currentButton==gameMode[2]) {
	        			System.out.println("Two-Versus-Two Team Battle game mode selected");
	        			numPlayers=4;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else {
	        			 System.out.println("4 Player Free-for-All Game game mode selected");
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
	        			 timesClicked++;
	        			 System.out.println("Player "+ timesClicked + " has chosen the Basic Team");
	        			 if (timesClicked == 1) { //the player currently selecting is based on the number of times the button has been clicked 
	        				 Player1.setTeamChosen(1);
	        			 }
	        			 if (timesClicked == 2) {
	        				 Player2.setTeamChosen(1);
	        			 }
	        			 if (timesClicked == 3) {
	        				 Player3.setTeamChosen(1);
	        			 }
	        			 if (timesClicked == 4) {
	        				 Player4.setTeamChosen(1);
	        			 }
	        			 if (timesClicked == numPlayers) {//once all players have chosen a team
	        				 System.out.println("Generating game board and chosen pieces");
	        				 createGui3(frame2, frame3, numPlayers); //go to next GUI
	        			 }
	        		 }
	        		 else if(currentButton==teams[1]) {
	        			 timesClicked++;
	        			 System.out.println("Player "+ timesClicked + " has chosen the Reckless Team");
	        			 if (timesClicked == 1) {
	        				 Player1.setTeamChosen(2);
	        			 }
	        			 if (timesClicked == 2) {
	        				 Player2.setTeamChosen(2);
	        			 }
	        			 if (timesClicked == 3) {
	        				 Player3.setTeamChosen(2);
	        			 }
	        			 if (timesClicked == 4) {
	        				 Player4.setTeamChosen(2);
	        			 }
	        			 if (timesClicked == numPlayers) {
	        				 System.out.println("Generating game board and chosen pieces");
	        				 createGui3(frame2, frame3, numPlayers);
	        			 }
	        		 }
	        		 else if(currentButton==teams[2]) {
	        			timesClicked++;
	        			System.out.println("Player "+ timesClicked + " has chosen the Fast Team");
	        			if (timesClicked == 1) {
	        				 Player1.setTeamChosen(3);
	        			 }
	        			 if (timesClicked == 2) {
	        				 Player2.setTeamChosen(3);
	        			 }
	        			 if (timesClicked == 3) {
	        				 Player3.setTeamChosen(3);
	        			 }
	        			 if (timesClicked == 4) {
	        				 Player4.setTeamChosen(3);
	        			 }
	        			 if (timesClicked == numPlayers) {
	        				 System.out.println("Generating game board and chosen pieces");
	        				 createGui3(frame2, frame3, numPlayers);
	        			 }
	        		 }
	        		 else {
	        			 timesClicked++;
	        			 System.out.println("Player "+ timesClicked + " has chosen the Damage-Focused Team");
	        			 if (timesClicked == 1) {
	        				 Player1.setTeamChosen(4);
	        			 }
	        			 if (timesClicked == 2) {
	        				 Player2.setTeamChosen(4);
	        			 }
	        			 if (timesClicked == 3) {
	        				 Player3.setTeamChosen(4);
	        			 }
	        			 if (timesClicked == 4) {
	        				 Player4.setTeamChosen(4);
	        			 }
	        			 if (timesClicked == numPlayers) {
	        				 System.out.println("Generating game board and chosen pieces");
	        				 createGui3(frame2, frame3, numPlayers);
	        			 }
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
        //JButton tile[] = new JButton[100];
        JButton tile[][] = new JButton[10][10]; //tile is now a matrix
        
        class healGamePiece implements ActionListener{
        	JButton currentButton=null;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        }
              
        class attackGamePiece implements ActionListener{
        	JButton currentButton=null;
        	public void actionPerformed (ActionEvent e) {
        		 JButton currentButton= (JButton)e.getSource();
        		 //When a player clicks their first button, if it has a piece on it, it will save the coords of that piece and output what it is
       		 if(currentButton.getIcon()==Player1.PieceImages[0]) {
					 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[0].name);
					 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i; 
	        					 pieceXCoord = j;
	        					 //System.out.println(pieceYCoord + "" + pieceXCoord);
	        				 }
	        			 }
	        		 }
				 }
       		 if(currentButton.getIcon()==Player1.PieceImages[1]) {
       			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[1].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i; 
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player1.PieceImages[2]) {
       			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[2].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player1.PieceImages[3]) {
       			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[3].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player1.PieceImages[4]) {
       			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[4].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player2.PieceImages[0]) {
       			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[0].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player2.PieceImages[1]) {
       			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[1].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player2.PieceImages[2]) {
       			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[2].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player2.PieceImages[3]) {
       			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[3].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(currentButton.getIcon()==Player2.PieceImages[4]) {
       			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[4].name);
       			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
       		 }
       		 if(numPlayers==4) {
       			 if(currentButton.getIcon()==Player3.PieceImages[0]) {
       			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[0].name);
       			 	for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player3.PieceImages[1]) {
       			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[1].name);
       			 	for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player3.PieceImages[2]) {
       			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[2].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player3.PieceImages[3]) {
       			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[3].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player3.PieceImages[4]) {
       			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[4].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player4.PieceImages[0]) {
       			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[0].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player4.PieceImages[1]) {
       			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[1].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player4.PieceImages[2]) {
       			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[2].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player4.PieceImages[3]) {
       			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[3].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       			 if(currentButton.getIcon()==Player4.PieceImages[4]) {
       			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[4].name);
       				for (int i=0; i<tile.length; i++) {
       			 		for(int j=0; j<tile[i].length; j++) {
       			 			if (currentButton == tile[i][j]) {
       			 				pieceYCoord = i;
       			 				pieceXCoord = j;
       			 			}
       			 		}
       			 	}
       			 }
       		 }
       		 if(lastButtonPressed!=null){//if two tiles were clicked
       			 if(lastButtonPressed !=currentButton){ //if different tiles clicked
       				 if(lastButtonPressed.getIcon()!=null&&currentButton.getIcon()!=null){//if both tiles have an icon 
       					 for (int i=0; i<tile.length; i++) {
            			 		for(int j=0; j<tile[i].length; j++) {
            			 			if (currentButton == tile[i][j]) {
            			 				destinationYCoord = i;
            			 				destinationXCoord = j;
            			 			}
            			 		}
            			 	}
       						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
       						 Icon tempImg2= currentButton.getIcon();
       			if(tempImg==Player1.PieceImages[0])	{
       			//check to make sure piece to be attacked isn't the player's pieces 
       		
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("attack time");
       				 //System.out.println(returnGamePiece(tempImg2)); //only returns the icon...need to return gamepiece
       					//System.out.println(Player1.playersTeam.teamPieces[0].attack);
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player1.PieceImages[1]){
       				if (currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player1.PieceImages[2]){
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("attack time");
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}			 
       			if(tempImg==Player1.PieceImages[3]) {
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[4]) {
       					System.out.println("attack time");
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}	
       			
       			if(tempImg==Player1.PieceImages[4]) {
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[0]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player2.PieceImages[0]) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player2.PieceImages[1]) {
       				if (currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player2.PieceImages[2]) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player2.PieceImages[3]) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			if(tempImg==Player2.PieceImages[4]) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[0]) {
       					System.out.println("attack time");
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}
       			 if(numPlayers==4) {
       				 if(tempImg==Player3.PieceImages[0]) {
            				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
            					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
            			}
       				 if(tempImg==Player3.PieceImages[1]) {
             				if (currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
             			}
       				 if(tempImg==Player3.PieceImages[2]) {
             				if(currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]){
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
             			}
       				 if(tempImg==Player3.PieceImages[3]) {
             				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[4]) {
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
             			} if(tempImg==Player3.PieceImages[4]) {
            				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[0]) {
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
            			}
             			 if(tempImg==Player4.PieceImages[0]) {
             				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]){
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
             			}
      					 if(tempImg==Player4.PieceImages[1]) {
              				if(currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
           					System.out.println("attack time");
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
      					 }
      					 if(tempImg==Player4.PieceImages[2]) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("attack time");
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
       					 }
      					 if(tempImg==Player4.PieceImages[3]) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("attack time");
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				} 
       					 }
      					 if(tempImg==Player4.PieceImages[4]) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[0]) {
               					System.out.println("attack time");
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
               				
       					 }				 				
       			 }//end of if numPlayers ==4			 
       					currentButton=null; //resets the ActionListener
       					}
       			 }
       		 }
       				lastButtonPressed=currentButton;
        	}
        	
        }
        
        
        class tileClicked implements ActionListener{
        	 JButton currentButton=null;
        	 
        	 public void actionPerformed (ActionEvent e){
        		 JButton currentButton= (JButton)e.getSource(); //save which button pressed in variable
        		 //System.out.println(currentButton);
        		 //This allows the system to know the type of piece that has been clicked on and its owner
        		 /*for (int i=0; i<tile.length; i++) {
        			 for(int j=0; j<tile[i].length; j++) {
        				 if (currentButton == tile[i][j]) {
        					 System.out.println("tile clicked is button " + i + j);
        				 }
        			 }
        		 }*/ //this is for debugging, if you want to see the number of the clicked tile
        		 
        		 
        		 //When a player clicks their first button, if it has a piece on it, it will save the coords of that piece and output what it is
        		 if(currentButton.getIcon()==Player1.PieceImages[0]) {
					 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[0].name + ", it can move " + Player1.playersTeam.teamPieces[0].move + " tiles");
					 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i; 
	        					 pieceXCoord = j;
	        					 //System.out.println(pieceYCoord + "" + pieceXCoord);
	        				 }
	        			 }
	        		 }
				 }
        		 if(currentButton.getIcon()==Player1.PieceImages[1]) {
        			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[1].name + ", it can move " + Player1.playersTeam.teamPieces[1].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i; 
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player1.PieceImages[2]) {
        			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[2].name + ", it can move " + Player1.playersTeam.teamPieces[2].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player1.PieceImages[3]) {
        			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[3].name + ", it can move " + Player1.playersTeam.teamPieces[3].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player1.PieceImages[4]) {
        			 System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[4].name + ", it can move " + Player1.playersTeam.teamPieces[4].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player2.PieceImages[0]) {
        			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[0].name + ", it can move " + Player2.playersTeam.teamPieces[0].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player2.PieceImages[1]) {
        			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[1].name + ", it can move " + Player2.playersTeam.teamPieces[1].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player2.PieceImages[2]) {
        			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[2].name + ", it can move " + Player2.playersTeam.teamPieces[2].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player2.PieceImages[3]) {
        			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[3].name + ", it can move " + Player2.playersTeam.teamPieces[3].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(currentButton.getIcon()==Player2.PieceImages[4]) {
        			 System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[4].name + ", it can move " + Player2.playersTeam.teamPieces[4].move + " tiles");
        			 for (int i=0; i<tile.length; i++) {
	        			 for(int j=0; j<tile[i].length; j++) {
	        				 if (currentButton == tile[i][j]) {
	        					 pieceYCoord = i;
	        					 pieceXCoord = j;
	        				 }
	        			 }
	        		 }
        		 }
        		 if(numPlayers==4) {
        			 if(currentButton.getIcon()==Player3.PieceImages[0]) {
        			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[0].name + ", it can move " + Player3.playersTeam.teamPieces[0].move + " tiles");
        			 	for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player3.PieceImages[1]) {
        			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[1].name + ", it can move " + Player3.playersTeam.teamPieces[1].move + " tiles");
        			 	for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player3.PieceImages[2]) {
        			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[2].name + ", it can move " + Player3.playersTeam.teamPieces[2].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player3.PieceImages[3]) {
        			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[3].name + ", it can move " + Player3.playersTeam.teamPieces[3].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player3.PieceImages[4]) {
        			 System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[4].name + ", it can move " + Player3.playersTeam.teamPieces[4].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player4.PieceImages[0]) {
        			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[0].name + ", it can move " + Player4.playersTeam.teamPieces[0].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player4.PieceImages[1]) {
        			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[1].name + ", it can move " + Player4.playersTeam.teamPieces[1].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player4.PieceImages[2]) {
        			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[2].name + ", it can move " + Player4.playersTeam.teamPieces[2].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player4.PieceImages[3]) {
        			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[3].name + ", it can move " + Player4.playersTeam.teamPieces[3].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        			 if(currentButton.getIcon()==Player4.PieceImages[4]) {
        			 System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[4].name + ", it can move " + Player4.playersTeam.teamPieces[4].move + " tiles");
        				for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				pieceYCoord = i;
        			 				pieceXCoord = j;
        			 			}
        			 		}
        			 	}
        			 }
        		 }
        		 if(lastButtonPressed!=null){//if two tiles were clicked
        			 if(lastButtonPressed !=currentButton){ //if different tiles clicked
        				 if(lastButtonPressed.getIcon()!=null&&currentButton.getIcon()==null){//if first tile had icon and second was empty
        					 for (int i=0; i<tile.length; i++) {
             			 		for(int j=0; j<tile[i].length; j++) {
             			 			if (currentButton == tile[i][j]) {
             			 				destinationYCoord = i;
             			 				destinationXCoord = j;
             			 			}
             			 		}
             			 	}
        					 
        					 //try{
        						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
        						//Image img = ImageIO.read(Game.class.getResource("/images/BlueCircle.png")); //will have to change image src to variable so it can work with any token
      
        			//When a second tile is clicked, it makes sure it is a valid move for the piece selected
        			if(tempImg==Player1.PieceImages[0])	{//pieces can move 3, 4, 5, or 6 tiles
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].move) {
        					swapImage(currentButton, tempImg);
        					
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[1]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[2]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}			 
        			if(tempImg==Player1.PieceImages[3]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}		
        			if(tempImg==Player1.PieceImages[4]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[0]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[0].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[1]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[1].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[2]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[2].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[3]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[4]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].move) {
        					swapImage(currentButton, tempImg);
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			 if(numPlayers==4) {
        				 if(tempImg==Player3.PieceImages[0]) {
             				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[0].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
             			}
        				 if(tempImg==Player3.PieceImages[1]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[1].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
        				 if(tempImg==Player3.PieceImages[2]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[2].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
        				 if(tempImg==Player3.PieceImages[3]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			} if(tempImg==Player3.PieceImages[4]) {
             				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
             			}
              			 if(tempImg==Player4.PieceImages[0]) {
              				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[0].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
       					 if(tempImg==Player4.PieceImages[1]) {
               				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[1].move) {
            					swapImage(currentButton, tempImg);
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
       					 }
       					 if(tempImg==Player4.PieceImages[2]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[2].move) {
                					swapImage(currentButton, tempImg);
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				}
        					 }
       					 if(tempImg==Player4.PieceImages[3]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].move) {
                					swapImage(currentButton, tempImg);
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				} 
        					 }
       					 if(tempImg==Player4.PieceImages[4]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].move) {
                					swapImage(currentButton, tempImg);
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				}
        					 }				 				
        			 }//end of if numPlayers ==4			 
        			        	//}catch (IOException ex){}
        					currentButton=null; //resets the ActionListener
        					}
        				}	
        			}// end of if LBR != null	
        			lastButtonPressed=currentButton; //sets initial value for LBP
        		} //end of actionPerformed
        	 } //end of TileClicked
        
        class attackPiece implements ActionListener{//sets the action listener of the gameboard to attack pieces
        	public void actionPerformed (ActionEvent e) {
       			for(int i=0; i<10; i++) {//add the buttons to the frame
       	        	for (int j=0; j<10; j++) {
       	        		tile[i][j].addActionListener(new attackGamePiece());
       	        	}
       	        }
       		}
       	}
       
       	class movePiece implements ActionListener{//sets the action listener of the gameboard to move pieces
       		public void actionPerformed (ActionEvent e) {
       			for(int i=0; i<10; i++) {//add the buttons to the frame
       	        	for (int j=0; j<10; j++) {
       	        		tile[i][j].addActionListener(new tileClicked());
       	        	}
       	        }
       		}
       	}
        
        class healPiece implements ActionListener{
          	 public void actionPerformed (ActionEvent e){
           			for(int i=0; i<10; i++) {//add the buttons to the frame
           	        	for (int j=0; j<10; j++) {
           	        		tile[i][j].addActionListener(new healGamePiece()); //need to implement healGamePiece
           	        	}
           	        }
          	 }
        }
         
        class cancelAction implements ActionListener{
         	 public void actionPerformed (ActionEvent e){
         		 
         		for(int i=0; i<10; i++) {//add the buttons to the frame
       	        	for (int j=0; j<10; j++) {
       	        		//remove the action listener, might need to keep track of action listener in a variable
       	        	}
       	        }
       		
         	 }  
       }
        
        for(int i=0; i<10; i++) {//add the buttons to the frame
        	for (int j=0; j<10; j++) {
        		tile[i][j]=new JButton();
        		frame3.add(tile[i][j]);
        		tile[i][j].setBackground(Color.white);
        		//tile[i][j].addActionListener(new tileClicked());
        	}
        }
        
        move.addActionListener(new movePiece());
        attack.addActionListener(new attackPiece());
        heal.addActionListener(new healPiece());
        cancel.addActionListener(new cancelAction());
        
        frame3.add(move);
        frame3.add(attack);
        frame3.add(heal);
        frame3.add(cancel);
        
        frame3.setSize(700,600);
        JMenuBar gameMenuBar = new JMenuBar(); //create menu bar
        frame3.setJMenuBar(gameMenuBar); //mount it onto frame
        JMenu options = new JMenu("Options"); //create "options" option
        gameMenuBar.add(options); //mount it onto menu bar
        JMenuItem key = new JMenuItem("Key"); //create and name options within "options"
        JMenuItem endturn = new JMenuItem("End Turn");
        JMenuItem concede = new JMenuItem("Concede");
        options.add(key); //put them into "options" within menu bar
        options.add(endturn);
        options.add(concede);
        
        class concedeAction implements ActionListener{ //allow "exit" to close program on click
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
        concede.addActionListener(new concedeAction()); //add action listeners to menu bar
        key.addActionListener(new keyAction());
        endturn.addActionListener(new endturnAction());
        
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setVisible(true);
        
        //have to finally create the game pieces based off of previous GUI selections
        Player1.createGamePieces(tile, "Blue", 10, 20, 0, 1, 2);
        Player2.createGamePieces(tile, "Red", 97, 98, 99, 89, 79); //Player 2's location has been changed so they are opposite player 1 rather than next to them
        if(numPlayers == 4) {
        	Player3.createGamePieces(tile, "Green", 70, 80, 90, 91, 92);
        	Player4.createGamePieces(tile, "Yellow", 7, 8, 9, 19, 29);
        }
	}
	
    public static void main(String[] args){
    	JFrame frame1= new JFrame();
    	JFrame frame2= new JFrame();
    	JFrame frame3= new JFrame();
    	createGui1(frame1, frame2, frame3);
    	//createGui3(frame2, frame3, 4);
    }  
  }

        
