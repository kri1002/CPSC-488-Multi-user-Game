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
	public static void removeImage(JButton currentButton) {
		for (int i=0; i<5; i++) {
			if (currentButton.getIcon() == Player1.PieceImages[i]) {
				Player1.piecesLeft--;
				if (Player1.piecesLeft>1) {
					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[i].name + " has been defeated!  Player 1 has " + Player1.piecesLeft + " pieces left!");
				}
				else if (Player1.piecesLeft==1) {
					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[i].name + " has been defeated!  Player 1 has 1 piece left!");
				}
				else {
					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[i].name + " has been defeated!  Player 1 has lost the game!");
				}
				
			}
			if (currentButton.getIcon() == Player2.PieceImages[i]) {
				Player2.piecesLeft--;
				if (Player2.piecesLeft>1) {
					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[i].name + " has been defeated!  Player 2 has " + Player2.piecesLeft + " pieces left!");
				}
				else if (Player2.piecesLeft==1) {
					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[i].name + " has been defeated!  Player 2 has 1 piece left!");
				}
				else {
					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[i].name + " has been defeated!  Player 2 has lost the game!");
				}
			}
			if (currentButton.getIcon() == Player3.PieceImages[i]) {
				Player3.piecesLeft--;
				if (Player3.piecesLeft>1) {
					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[i].name + " has been defeated!  Player 3 has " + Player3.piecesLeft + " pieces left!");
				}
				else if (Player3.piecesLeft==1) {
					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[i].name + " has been defeated!  Player 3 has 1 piece left!");
				}
				else {
					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[i].name + " has been defeated!  Player 3 has lost the game!");
				}
			}
			if (currentButton.getIcon() == Player4.PieceImages[i]) {
				Player4.piecesLeft--;
				if (Player4.piecesLeft>1) {
					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[i].name + " has been defeated!  Player 4 has " + Player4.piecesLeft + " pieces left!");
				}
				else if (Player4.piecesLeft==1) {
					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[i].name + " has been defeated!  Player 4 has 1 piece left!");
				}
				else {
					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[i].name + " has been defeated!  Player 4 has lost the game!");
				}
			}
		}
		currentButton.setIcon(null);
		currentButton.revalidate();
	}
	
	public static void setCoordinates(JButton currentButton, int numPlayers, JButton tile[][] ) {//set the coordinate of current clicked game piece and output what piece it is
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
        	public void actionPerformed (ActionEvent e) {
        		 JButton currentButton= (JButton)e.getSource();
       		 if(lastButtonPressed!=null){//if two tiles were clicked
       			if(lastButtonPressed ==currentButton) {//if the same tiles clicked
       				if(lastButtonPressed.getIcon()!=null && currentButton.getIcon()!=null) {// if both tiles have an icon
       				 setCoordinates(lastButtonPressed, numPlayers, tile); //set coords had to be moved
   					 for (int i=0; i<tile.length; i++) {
        			 		for(int j=0; j<tile[i].length; j++) {
        			 			if (currentButton == tile[i][j]) {
        			 				destinationYCoord = i;
        			 				destinationXCoord = j;
        			 			}
        			 		}
        			 	}
   						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
   			
   			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){ //check only locations 3 &4 bc teams sets healers to those locations & check to make sure piece is healer
   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing itself");//message to user
   				Piece targetPiece;//alias for piece being healed
   				targetPiece = Player1.playersTeam.teamPieces[3];//set target piece to the healer
   				int tempHp = targetPiece.getCurrHp();//save hp it currently has
   					
   				if(tempHp != targetPiece.getMaxHp()){
   				Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal piece
   					if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the maximum hp
   						targetPiece.currHp=targetPiece.getMaxHp();
   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   					}
   					else {
   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   						}
   				}
   				else {
   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   					}
   			}
   			
   			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " is healing itself");
   					Piece targetPiece;
   					targetPiece = Player1.playersTeam.teamPieces[4];
   					int tempHp = targetPiece.getCurrHp();
   					
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player1.playersTeam.teamPieces[4].heal(targetPiece);
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   						}
   							
   						else {
   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   						}
   					}
   				else {
   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   					}
   			}			 
   			
   			if(tempImg==Player2.PieceImages[3] && Player1.playersTeam.teamPieces[3].name == "Healer") {
   					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " is healing itself");
   					Piece targetPiece;
   					targetPiece = Player2.playersTeam.teamPieces[3];
   					int tempHp = targetPiece.getCurrHp();
   					
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player2.playersTeam.teamPieces[3].heal(targetPiece);
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   						}
   						else {
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   						}
   					}
   			else {
   				System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   				}
   			}
   			
   			if(tempImg==Player2.PieceImages[4] && Player2.playersTeam.teamPieces[4].name == "Healer") {
   				System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " is healing itself");
   				Piece targetPiece;
   				targetPiece = Player2.playersTeam.teamPieces[4];
   				int tempHp = targetPiece.getCurrHp();
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player2.playersTeam.teamPieces[4].heal(targetPiece);
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   						}	
   						else {
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   						}
   					}
   					else {
   						System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   						}
   			}
   			
   			 if(numPlayers==4) {
   				 if(tempImg==Player3.PieceImages[3] && Player3.playersTeam.teamPieces[3].name == "Healer") {
         			System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " is healing itself");
           			Piece targetPiece;	
           			targetPiece = Player3.playersTeam.teamPieces[3];
           			int tempHp = targetPiece.getCurrHp();
           			
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player3.playersTeam.teamPieces[3].heal(targetPiece);
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           					}
           							
           					else {
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           					}
           				}
           			else {
           				System.out.println("Player 3's " + targetPiece.name + " is already at full health");
           				}
         			} 
   				 
   				 if(tempImg==Player3.PieceImages[4] && Player3.playersTeam.teamPieces[4].name =="Healer") {
        			System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " is healing itself");
           			Piece targetPiece;
           			targetPiece = Player3.playersTeam.teamPieces[4];
           			int tempHp = targetPiece.getCurrHp();
           			
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player3.playersTeam.teamPieces[4].heal(targetPiece);
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           					}
           							
           					else {
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           					}
           				}
           				else {
           					System.out.println("Player 3's " + targetPiece.name + " is already at full health");
           					}	
        			}
         			
  					 if(tempImg==Player4.PieceImages[3] && Player4.playersTeam.teamPieces[3].name=="Healer") {
           				System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " is healing itself");
           				Piece targetPiece;
           				targetPiece = Player4.playersTeam.teamPieces[3];
           				int tempHp = targetPiece.getCurrHp();
           				
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player4.playersTeam.teamPieces[3].heal(targetPiece);
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           					}
           					else {
           						System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           					}
           				}
           				else {
           				System.out.println("Player 4's " + targetPiece.name + "is already at full health");
           				}
   					 }
  						
  					 if(tempImg==Player4.PieceImages[4]) {
           				System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " is healing itself");
           				Piece targetPiece;
           				targetPiece = Player4.playersTeam.teamPieces[4];
           				int tempHp = targetPiece.getCurrHp();
           				
           					if(tempHp != targetPiece.getMaxHp()) {
           						Player4.playersTeam.teamPieces[4].heal(targetPiece);
           						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           							targetPiece.currHp=targetPiece.getMaxHp();
           							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           						}
           						else {
           							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           						}
           					}
           					else {
           						System.out.println("Player 4's " + targetPiece.name + "is already at full health");
           					}			
  					 }
   				} //end of if players =4
       		}
       	}//end of self healing
       			
       			 if(lastButtonPressed !=currentButton){ //if different tiles clicked
       				 if(lastButtonPressed.getIcon()!=null&&currentButton.getIcon()!=null){//if both tiles have an icon 
       					 setCoordinates(lastButtonPressed, numPlayers, tile); //set coords had to be moved
       					 for (int i=0; i<tile.length; i++) {
            			 		for(int j=0; j<tile[i].length; j++) {
            			 			if (currentButton == tile[i][j]) {
            			 				destinationYCoord = i;
            			 				destinationXCoord = j;
            			 			}
            			 		}
            			 	}
       						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
       			
       			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){//checks 3& 4 bc teams only set those as healers & make sure it is a healer
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
       					//This checks for the range of the healing piece^
       				if (currentButton.getIcon()==Player1.PieceImages[0] || currentButton.getIcon()==Player1.PieceImages[1] || currentButton.getIcon()==Player1.PieceImages[2] || currentButton.getIcon()==Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing");//message to user
       					Piece targetPiece;//alias for piece being healed
       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       							if(tempHp != targetPiece.getMaxHp()){
       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       							}
       							}
       							else {
       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       							}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       			}//end of range check
       			else {
       				System.out.println("That is an invalid heal, cannot a piece further than you healer's range");
       			}
       			}
       			
       			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
       				if(currentButton.getIcon()==Player1.PieceImages[0] && currentButton.getIcon()==Player1.PieceImages[1] && currentButton.getIcon()==Player1.PieceImages[2] && currentButton.getIcon()==Player1.PieceImages[3]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player1.playersTeam.teamPieces[4].heal(targetPiece);
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       							}
       							else {
       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       							}
       						}
       					}
       				}
       				else{
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than healer's range");
       				}
       			}			 
       			
       			if(tempImg==Player2.PieceImages[3] && Player1.playersTeam.teamPieces[3].name == "Healer") {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].range) {
       				if (currentButton.getIcon()==Player2.PieceImages[0] || currentButton.getIcon()==Player2.PieceImages[1] || currentButton.getIcon()!=Player2.PieceImages[2] || currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player2.playersTeam.teamPieces[3].heal(targetPiece);
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       							}
       							
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       							else {
       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       							}
       						}
       					}
       				}
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than healer's range");
       				}
       			}
       			
       			if(tempImg==Player2.PieceImages[4] && Player2.playersTeam.teamPieces[4].name == "Healer") {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].range) {
       				if (currentButton.getIcon()==Player2.PieceImages[0] || currentButton.getIcon()==Player2.PieceImages[1] || currentButton.getIcon()==Player2.PieceImages[2] || currentButton.getIcon()==Player2.PieceImages[3]){
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player2.playersTeam.teamPieces[4].heal(targetPiece);
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       							
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       							else {
       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       							}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       				}
       			}
       			 if(numPlayers==4) {
       				 if(tempImg==Player3.PieceImages[3] && Player3.playersTeam.teamPieces[3].name == "Healer") {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].range) {
             				if (currentButton.getIcon()==Player3.PieceImages[0] || currentButton.getIcon()==Player3.PieceImages[1] || currentButton.getIcon()==Player3.PieceImages[2] || currentButton.getIcon()==Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player3.playersTeam.teamPieces[3].heal(targetPiece);
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               					}
               							else {
               								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       					}
             			} 
       				 
       				 if(tempImg==Player3.PieceImages[4] && Player3.playersTeam.teamPieces[4].name =="Healer") {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].range) {
            				if (currentButton.getIcon()==Player3.PieceImages[0] || currentButton.getIcon()==Player3.PieceImages[1] || currentButton.getIcon()!=Player3.PieceImages[2] || currentButton.getIcon()==Player3.PieceImages[3]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player3.playersTeam.teamPieces[4].heal(targetPiece);
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               							}
               							
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               							else {
               								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
               							}	
               					}
               					}
           				}
           				else {
           					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       					}
            			}
             			
      					 if(tempImg==Player4.PieceImages[3] && Player4.playersTeam.teamPieces[3].name=="Healer") {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].range) {
               				if (currentButton.getIcon()==Player4.PieceImages[0] || currentButton.getIcon()==Player4.PieceImages[1] || currentButton.getIcon()==Player4.PieceImages[2] || currentButton.getIcon()==Player4.PieceImages[4]){
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player4.playersTeam.teamPieces[3].heal(targetPiece);
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							}
               						
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               							else {
               								System.out.println("Player 4's " + targetPiece.name + "is already at full health");
               							}
               					}
               					}
               				}
               				else {
               					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
               				} 
      						}//end of range check
      						else {
      							System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
      						}
       					 }
      					 
      					 if(tempImg==Player4.PieceImages[4]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].range) {
               				if (currentButton.getIcon()==Player4.PieceImages[0] || currentButton.getIcon()==Player4.PieceImages[1] || currentButton.getIcon()==Player4.PieceImages[2] || currentButton.getIcon()==Player4.PieceImages[3]){
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               							Player4.playersTeam.teamPieces[4].heal(targetPiece);
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid heal, cannot heal the other team's game pieces");
               				}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
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
              
        class attackGamePiece implements ActionListener{
        	JButton currentButton=null;
        	public void actionPerformed (ActionEvent e) {
        		 JButton currentButton= (JButton)e.getSource();
        		 //When a player clicks their first button, if it has a piece on it, it will save the coords of that piece and output what it is
        		 //setCoordinates(currentButton, numPlayers, tile);
        		 //System.out.println(pieceXCoord);
       		 if(lastButtonPressed!=null){//if two tiles were clicked
       			 if(lastButtonPressed !=currentButton){ //if different tiles clicked
       				 if(lastButtonPressed.getIcon()!=null&&currentButton.getIcon()!=null){//if both tiles have an icon 
       					 setCoordinates(lastButtonPressed, numPlayers, tile); //set coords had to be moved
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
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].range) {
       					//This checks for the range of the attacking piece^
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[0].name + " attacks!");//message to user
       					Piece targetPiece;//alias for piece being attacked
       					for(int i=0; i<5; i++) {//find piece being attacked, searches all 3 opposing teams for a match
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);//make attack
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								/*try {
       								currentButton.setIcon(null); //sets the tile with the image null
       								currentButton.revalidate(); //resets and updates button
       								}
       								catch (Exception ee) {}
       							*/ //dont try this way it destroys the program 
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       							}
       							}
       					}
       					if(numPlayers == 4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       								}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       			}//end of range check
       			else {
       				System.out.println("That is an invalid attack, cannot a piece further than your piece's range");
       			}
       			}
       			if(tempImg==Player1.PieceImages[1]){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].range) {
       				if (currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[1].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       								}
       							else { 
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							}
       							}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("that is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player1.PieceImages[2]){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].range) {
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}			 
       			if(tempImg==Player1.PieceImages[3]) {
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[4]) {
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}	
       			
       			if(tempImg==Player1.PieceImages[4]) {
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[0]) {
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       							}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[0]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[0].range) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[0].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[1]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[1].range) {
       				if (currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[1].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[2]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[2].range) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[2].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[3]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].range) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[4]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].range) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[0]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton);
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			 if(numPlayers==4) {
       				 if(tempImg==Player3.PieceImages[0]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[0].range) {
            				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[0].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
            			}
       				 if(tempImg==Player3.PieceImages[1]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[1].range) {
             				if (currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[1].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               					}
               						}	
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			}
       				 if(tempImg==Player3.PieceImages[2]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[2].range) {
             				if(currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]){
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[2].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			}
       				 if(tempImg==Player3.PieceImages[3]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].range) {
             				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               					}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			} 
       				 if(tempImg==Player3.PieceImages[4]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].range) {
            				if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[0]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               							}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
            			}
             			 if(tempImg==Player4.PieceImages[0]) {
             				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[0].range) {
             				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]){
             					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[0].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
             			}//end of range check
             			else {
             				System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");	
             			}
             			}
      					 if(tempImg==Player4.PieceImages[1]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[1].range) {
              				if(currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
              					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[1].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
      					}//end of range check
      					else {
      						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      					}
      					 }
      					 if(tempImg==Player4.PieceImages[2]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[2].range) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[2].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      						}
       					 }
      					 if(tempImg==Player4.PieceImages[3]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].range) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				} 
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      						}
       					 }
      					 if(tempImg==Player4.PieceImages[4]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].range) {
               				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[0]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton);
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               						}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
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
        		 setCoordinates(currentButton, numPlayers, tile);
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
       			System.out.println("Attack Mode Selected");
       		}
       	}
       
       	class movePiece implements ActionListener{//sets the action listener of the gameboard to move pieces
       		public void actionPerformed (ActionEvent e) {
       			for(int i=0; i<10; i++) {//add the buttons to the frame
       	        	for (int j=0; j<10; j++) {
       	        		tile[i][j].addActionListener(new tileClicked());
       	        	}
       	        }
       			System.out.println("Move Mode Selected");
       		}
       	}
        
        class healPiece implements ActionListener{
          	 public void actionPerformed (ActionEvent e){
           			for(int i=0; i<10; i++) {//add the buttons to the frame
           	        	for (int j=0; j<10; j++) {
           	        		tile[i][j].addActionListener(new healGamePiece()); //need to implement healGamePiece
           	        	}
           	        }
           			System.out.println("Heal Mode Selected");
          	 }
        }
         
        class cancelAction implements ActionListener{
         	 public void actionPerformed (ActionEvent e){
         		 
         		for(int i=0; i<10; i++) {//add the buttons to the frame
       	        	for (int j=0; j<10; j++) {
       	        		//remove the action listener, might need to keep track of action listener in a variable
       	        	}
       	        }
         		System.out.println("Action Mode Canceled.  Please select an Action Mode");
       		
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

        
