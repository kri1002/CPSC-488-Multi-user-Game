import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;
public class Game extends JPanel{
	private static JButton lastButtonPressed;
	public static int timesClicked = 0;//I feel like there is a better place to put this, but I couldnt get it to work anywhere else
	public static int pieceXCoord; //Coordinates for the piece to be used in the action listener
 	public static int pieceYCoord;
 	public static int destinationXCoord; //coordinates for the destination space to be used in the action listener
 	public static int destinationYCoord;
 	public static int turnSeed;//the player who is going first
 	public static int turnNumber = 0;//the current number of elapsed turns
 	public static int gameModeSelected; //keeps track of gameMode, will be used to make teams in 2v2
 	public static int playersLeft;//number of players who still have pieces
 	public static int holdTurn=0;//var to help skip the turns of players who do not have pieces left
	public static Player Player1 = new Player(1);//players are now generated at the start of the game class so they can be referenced anywhere
	//public static Player Player2 = new Player(2);
	public static ComPlayer Player2= new ComPlayer(2);
	public static Player Player3 = new Player(3);
	public static Player Player4 = new Player(4);
	public static JButton move =new JButton("Move"); //buttons to take actions in the game
    public static JButton attack =new JButton("Attack");
    public static JButton heal =new JButton("Heal"); 
	public static JButton cancel =new JButton("Cancel Action");
	String [] names = new String[0];
	public static String[] team1 = new String[2];
	public static String[] team2= new String[2];
	
	public static void attack2v2(Icon tempImg, int numPlayers, JButton currentButton, JFrame frame3, int i) {
		Piece targetPiece;//alias for piece being attacked
		if(tempImg==Player1.PieceImages[0]) {
		if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
					if(team1[0]=="Player 1" || team1[1]=="Player 1") {		
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");	
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[0].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[0].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
						}//end of if player 1 team 1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[0].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[0].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of else if player 1 team 2	
		}
		
		else if(currentButton.getIcon()==Player3.PieceImages[i]) {
					if(team1[0]=="Player 1" || team1[1]=="Player 1") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[0].attack(targetPiece);
  							Player1.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[0].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[0].attack(targetPiece);
  							Player1.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[0].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2
		}
		
		else if(currentButton.getIcon()==Player4.PieceImages[i]) {
			if(team1[0]=="Player 1" || team1[1]=="Player 1"){
					if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
					}
					else {
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player1.playersTeam.teamPieces[0].attack(targetPiece);
							Player1.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player1.playersTeam.teamPieces[0].moved==1) {
									endTurn(numPlayers);
									turnSeed=2;
								}
								}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player1.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=2;
							}
							}
					}
				}//end of if p1 in t1
				else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
					if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
					}
					else {
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player1.playersTeam.teamPieces[0].attack(targetPiece);
							Player1.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player1.playersTeam.teamPieces[0].moved==1) {
									endTurn(numPlayers);
									turnSeed=2;
								}
								}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player1.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=2;
							}
							}	
					}
				}//end of if p1 in t2
		}
		}//end if tempimg p1[0]
		
		else if(tempImg==Player1.PieceImages[1]) {
			if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
			if(team1[0]=="Player 1" || team1[1]=="Player 1") {		
					if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						System.out.println("Cannot attack your teammate's game pieces");	
					}
					else {
						targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
							int tempHp = targetPiece.getCurrHp();//save hp it currently has
							Player1.playersTeam.teamPieces[1].attack(targetPiece);//make attack
							Player1.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
								if(Player1.playersTeam.teamPieces[1].moved==1) {
									endTurn(numPlayers); //prints out turn ended & next player
  								turnSeed=2; //sets next to player 2
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
							if(Player1.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=2;
							}
							}
					}
					}//end of if player 1 team 1
				else if(team2[0]=="Player 1" || team2[1]=="Player 1") {
					if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						System.out.println("Cannot attack your teammate's game piece");
					}
					else {
						targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
							int tempHp = targetPiece.getCurrHp();//save hp it currently has
							Player1.playersTeam.teamPieces[1].attack(targetPiece);//make attack
							Player1.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
								if(Player1.playersTeam.teamPieces[1].moved==1) {
									endTurn(numPlayers); //prints out turn ended & next player
  								turnSeed=2; //sets next to player 2
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
							if(Player1.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=2;
							}
							}
					}
				}//end of else if player 1 team 2
		}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[1].attack(targetPiece);
  							Player1.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[1].attack(targetPiece);
  							Player1.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2
			}	
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[1].attack(targetPiece);
  							Player1.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of if p1 in t1
					
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[1].attack(targetPiece);
  							Player1.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2			
			}
			
		}//end of tempimg =p1[1]
		else if(tempImg==Player1.PieceImages[2]) {
			if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {		
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");	
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
						}//end of if player 1 team 1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
						
					}//end of else if player 1 team 2
				
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2
			}	
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of if p1 in t1
					
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[2].attack(targetPiece);
  							Player1.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2			
			}
		}
		
		else if(tempImg==Player1.PieceImages[3]) {
			if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {		
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");	
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
						}//end of if player 1 team 1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of else if player 1 team 2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2	
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[3].attack(targetPiece);
  							Player1.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2	
			}
		}
		
		else if(tempImg==Player1.PieceImages[4]) {
			if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {		
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");	
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
						}//end of if player 1 team 1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
  							int tempHp = targetPiece.getCurrHp();//save hp it currently has
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);//make attack
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
      								turnSeed=2; //sets next to player 2
  								}
  							}
  							else {
  							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of else if player 1 team 2	
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t1
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}
						}
					}//end of if p1 in t1
					
					else if(team2[0]=="Player 1" || team2[1]=="Player 1"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player1.playersTeam.teamPieces[4].attack(targetPiece);
  							Player1.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player1.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers);
  									turnSeed=2;
  								}
  								}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player1.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								turnSeed=2;
  							}
  							}	
						}
					}//end of if p1 in t2	
			}
		}
		
		else if(tempImg==Player2.PieceImages[0]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[0].attack(targetPiece);
  							Player2.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[0].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t1
						
						
					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[0].attack(targetPiece);
  							Player2.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[0].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[0].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							
						}
						
					}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[0].attack(targetPiece);
							Player2.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
						}
						}//end of if p1 in t1
						else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							if(team2[0]=="Player 3" || team2[1]=="Player 3"){
								System.out.println("Cannot attack your teammate's game pieces");
							}
							else {	targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[0].attack(targetPiece);
							Player2.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}	
							}
						}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {	
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[0].attack(targetPiece);
							Player2.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
							
						}
					}//end if p2 in t1
					else if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[0].attack(targetPiece);
  							Player2.playersTeam.teamPieces[0].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[0].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[0].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							
						}
						
					}// end of if p2 in t2
			}
		}
		
		else if(tempImg==Player2.PieceImages[1]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[1].attack(targetPiece);
  							Player2.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[1].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t1

					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[1].attack(targetPiece);
  							Player2.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[1].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[1].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							if(team1[0]=="Player 3" || team1[1]=="Player 3"){
								System.out.println("Cannot attack your teammate's game pieces");
							}
							else {
								targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[1].attack(targetPiece);
  							Player2.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[1].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							}
							}//end of if p1 in t1
							else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
								if(team2[0]=="Player 3" || team2[1]=="Player 3"){
									System.out.println("Cannot attack your teammate's game pieces");
								}
								else {	targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[1].attack(targetPiece);
  							Player2.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[1].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}	
								}
							}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {	
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[1].attack(targetPiece);
							Player2.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
							
						}
					}//end if p2 in t1
					else if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[1].attack(targetPiece);
  							Player2.playersTeam.teamPieces[1].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[1].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[1].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							
						}
						
					}// end of if p2 in t2
			}
		}
		
		else if(tempImg==Player2.PieceImages[2]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[2].attack(targetPiece);
  							Player2.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[2].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t1

					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[2].attack(targetPiece);
  							Player2.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[2].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[2].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							if(team1[0]=="Player 3" || team1[1]=="Player 3"){
								System.out.println("Cannot attack your teammate's game pieces");
							}
							else {
								targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[2].attack(targetPiece);
  							Player2.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[2].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							}
							}//end of if p1 in t1
							else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
								if(team2[0]=="Player 3" || team2[1]=="Player 3"){
									System.out.println("Cannot attack your teammate's game pieces");
								}
								else {	targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[2].attack(targetPiece);
  							Player2.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[2].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}	
								}
							}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {	
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[2].attack(targetPiece);
							Player2.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
							
						}
					}//end if p2 in t1
					else if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[2].attack(targetPiece);
  							Player2.playersTeam.teamPieces[2].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[2].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[2].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							
						}
						
					}// end of if p2 in t2
			}
		}
		
		else if(tempImg==Player2.PieceImages[3]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[3].attack(targetPiece);
  							Player2.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[3].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t1

					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[3].attack(targetPiece);
  							Player2.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[3].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[3].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							if(team1[0]=="Player 3" || team1[1]=="Player 3"){
								System.out.println("Cannot attack your teammate's game pieces");
							}
							else {
								targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[3].attack(targetPiece);
  							Player2.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[3].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							}
							}//end of if p1 in t1
							else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
								if(team2[0]=="Player 3" || team2[1]=="Player 3"){
									System.out.println("Cannot attack your teammate's game pieces");
								}
								else {	targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[3].attack(targetPiece);
  							Player2.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[3].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}	
								}
							}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {	
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[3].attack(targetPiece);
							Player2.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
							
						}
					}//end if p2 in t1
					else if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[3].attack(targetPiece);
  							Player2.playersTeam.teamPieces[3].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[3].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[3].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							
						}
						
					}// end of if p2 in t2
			}
		}
		
		else if(tempImg==Player2.PieceImages[4]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[4].attack(targetPiece);
  							Player2.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[4].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t1

					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[4].attack(targetPiece);
  							Player2.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[4].moved==1) {
  									endTurn(numPlayers); //prints out turn ended & next player
  									if(numPlayers==2){ //if 2 players, set next to player 1
  										turnSeed=1;	
  									}
  									else { //if 4 players, set next to player 3
  									turnSeed=3;
  									}
  								}
  							}
  							else {
  							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[4].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							if(team1[0]=="Player 3" || team1[1]=="Player 3"){
								System.out.println("Cannot attack your teammate's game pieces");
							}
							else {
								targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[4].attack(targetPiece);
  							Player2.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[4].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
							}
							}//end of if p1 in t1
							else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
								if(team2[0]=="Player 3" || team2[1]=="Player 3"){
									System.out.println("Cannot attack your teammate's game pieces");
								}
								else {	targetPiece = Player3.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[4].attack(targetPiece);
  							Player2.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[4].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}	
								}
							}//end of if p2 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 2" || team1[1]=="Player 2") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {	
						targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player2.playersTeam.teamPieces[4].attack(targetPiece);
							Player2.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player2.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player2.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							if(numPlayers==2){
							turnSeed=1;	
							}
							else {
								turnSeed=3;
							}
							}
							}
							
						}
					}//end if p2 in t1
					else if(team2[0]=="Player 2" || team2[1]=="Player 2"){
						if(team2[0]=="Player 4" || team2[1]=="Player 4"){
							System.out.println("Cannot attack your teammate's game piece");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
  							int tempHp = targetPiece.getCurrHp();
  							Player2.playersTeam.teamPieces[4].attack(targetPiece);
  							Player2.playersTeam.teamPieces[4].tookAction++;
  							if(targetPiece.currHp<1) {//don't want negative numbers for hp
  								targetPiece.currHp=0;
  								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
  								removeImage(currentButton, frame3);
  								if(Player2.playersTeam.teamPieces[4].moved==1) {
  								endTurn(numPlayers);
  								if(numPlayers==2){
  								turnSeed=1;	
  								}
  								else {
  									turnSeed=3;
  								}
  								}
  							}
  							else {
  							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
  							if(Player2.playersTeam.teamPieces[4].moved==1) {
  							endTurn(numPlayers);
								if(numPlayers==2){
								turnSeed=1;	
								}
								else {
									turnSeed=3;
								}
  							}
  							}
						}
					}// end of if p2 in t2
			}
		}
		
		else if(tempImg==Player3.PieceImages[0]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						System.out.println("Cannot attack your teammate's game pieces");
					}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					} //end of if p3 in t2	
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[0].attack(targetPiece);
							Player3.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t2	
			}
		}
		
		else if(tempImg==Player3.PieceImages[1]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						System.out.println("Cannot attack your teammate's game pieces");
					}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					} //end of if p3 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[1].attack(targetPiece);
							Player3.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t2	
			}
		}
		
		else if(tempImg==Player3.PieceImages[2]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						System.out.println("Cannot attack your teammate's game pieces");
					}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					} //end of if p3 in t2	
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[2].attack(targetPiece);
							Player3.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t2	
			}
		}
		
		else if(tempImg==Player3.PieceImages[3]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						System.out.println("Cannot attack your teammate's game pieces");
					}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					} //end of if p3 in t2
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[3].attack(targetPiece);
							Player3.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t2		
			}
		}
		
		else if(tempImg==Player3.PieceImages[4]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1"){
						System.out.println("Cannot attack your teammate's game pieces");
					}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=4; //set next to player 4
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of if p3 in t1
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					} //end of if p3 in t2	
			}
			else if(currentButton.getIcon()==Player4.PieceImages[i]) {
				if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						if(team1[0]=="Player 4" || team1[1]=="Player 4") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t1
					
					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
						if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player4.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player3.playersTeam.teamPieces[4].attack(targetPiece);
							Player3.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player3.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=4;
								}
							}
							else {
							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player3.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=4;
							}
							}
						}
					}//end of p3 in t2	
			}
		}
		
		else if(tempImg==Player4.PieceImages[0]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of p4 in t2
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of if p4 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[0].attack(targetPiece);
							Player4.playersTeam.teamPieces[0].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[0].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[0].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t2	
			}
		}
		
		else if(tempImg==Player4.PieceImages[1]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of p4 in t2	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of if p4 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[1].attack(targetPiece);
							Player4.playersTeam.teamPieces[1].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[1].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[1].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t2	
			}
		}
		
		else if(tempImg==Player4.PieceImages[2]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of p4 in t2	
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of if p4 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[2].attack(targetPiece);
							Player4.playersTeam.teamPieces[2].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[2].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[2].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t2	
			}
		}
		
		else if(tempImg==Player4.PieceImages[3]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of p4 in t2		
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of if p4 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[3].attack(targetPiece);
							Player4.playersTeam.teamPieces[3].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[3].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[3].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t2	
			}
		}
		
		else if(tempImg==Player4.PieceImages[4]) {
			if(currentButton.getIcon()==Player1.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 1" || team1[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 1" || team2[1]=="Player 1") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player1.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers); //prints out turn ended & next player
								turnSeed=1; //sets next to player 1
								}
							}
							else {
							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of p4 in t2		
			}
			else if(currentButton.getIcon()==Player2.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 2" || team1[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 2" || team2[1]=="Player 2") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player2.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}	
						}
					}//end of if p4 in t2
			}
			else if(currentButton.getIcon()==Player3.PieceImages[i]) {
				if(team1[0]=="Player 4" || team1[1]=="Player 4") {
						if(team1[0]=="Player 3" || team1[1]=="Player 3") {
						System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t1
					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
						if(team2[0]=="Player 3" || team2[1]=="Player 3") {
							System.out.println("Cannot attack your teammate's game pieces");
						}
						else {
							targetPiece = Player3.playersTeam.teamPieces[i];
							int tempHp = targetPiece.getCurrHp();
							Player4.playersTeam.teamPieces[4].attack(targetPiece);
							Player4.playersTeam.teamPieces[4].tookAction++;
							if(targetPiece.currHp<1) {//don't want negative numbers for hp
								targetPiece.currHp=0;
								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
								removeImage(currentButton, frame3);
								if(Player4.playersTeam.teamPieces[4].moved==1) {
								endTurn(numPlayers);
								turnSeed=1;
								}
							}
							else {
							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
							if(Player4.playersTeam.teamPieces[4].moved==1) {
							endTurn(numPlayers);
							turnSeed=1;
							}
							}
						}
					}//end of p4 in t2	
			}
		}
		
	}
	
	public static void set2v2Teams(int gameModeSelected, int numPlayers){ //groups players together in strings
		int randomPlayer1;
		int randomPlayer2;
		
		if(gameModeSelected==2) {
			Random playerGen =new Random();
			randomPlayer1=playerGen.nextInt(numPlayers)+1;
			Random playerGen2 =new Random();
			randomPlayer2=playerGen2.nextInt(numPlayers)+1;
			if(randomPlayer1!=randomPlayer2) {
				if(randomPlayer1==1) {
					team1[0]="Player 1";
					if(randomPlayer2==2) {
						team1[1]="Player 2";
						team2[0]="Player 3";
						team2[1]="Player 4";
					}
					else if(randomPlayer2==3) {
						team1[1]="Player 3";
						team2[0]="Player 2";
						team2[1]="Player 4";
					}
					else {
					team1[1]="Player 4";
					team2[0]="Player 2";
					team2[1]="Player 3";	
					}
				}
				else if(randomPlayer1==2){
					team1[0]="Player 2";
					if(randomPlayer2==1) {
						team1[1]="Player 1";
						team2[0]="Player 3";
						team2[1]="Player 4";
					}
					else if(randomPlayer2==3) {
						team1[1]="Player 3";
						team2[0]="Player 1";
						team2[1]="Player 4";
					}
					else {
					team1[1]="Player 4";
					team2[0]="Player 1";
					team2[1]="Player 3";	
					}	
				}
				else if(randomPlayer1==3){
					team1[0]="Player 3";
					if(randomPlayer2==1) {
						team1[1]="Player 1";
						team2[0]="Player 2";
						team2[1]="Player 4";
					}
					else if(randomPlayer2==2) {
						team1[1]="Player 2";
						team2[0]="Player 1";
						team2[1]="Player 4";
					}
					else {
					team1[1]="Player 4";
					team2[0]="Player 1";
					team2[1]="Player 2";	
					}
				}
				else {
					team1[0]="Player 4";
					if(randomPlayer2==1) {
						team1[1]="Player 1";
						team2[0]="Player 2";
						team2[1]="Player 3";
					}
					else if(randomPlayer2==2) {
						team1[1]="Player 2";
						team2[0]="Player 1";
						team2[1]="Player 3";
					}
					else {
					team1[1]="Player 3";
					team2[0]="Player 1";
					team2[1]="Player 2";	
					}	
				}
			}//end of if generated nums aren't equal
			
			else{ 
				if(randomPlayer1==1) {
				team1[0]="Player 1";
				team1[1]="Player 2";
				team2[0]="Player 3";
				team2[1]="Player 4";
				}
				else if(randomPlayer1==2) {
					team1[0]="Player 2";
					team1[1]="Player 1";
					team2[0]="Player 3";
					team2[1]="Player 4";
					}
				else if(randomPlayer1==3) {
					team1[0]="Player 3";
					team1[1]="Player 2";
					team2[0]="Player 1";
					team2[1]="Player 4";
					}
				else {
					team1[0]="Player 4";
					team1[1]="Player 2";
					team2[0]="Player 3";
					team2[1]="Player 4";
			}
			}
			System.out.println("Team 1 is " + team1[0] + " and " + team1[1]);
			System.out.println("Team 2 is " + team2[0] + " and " + team2[1]);
		}//end of if right gameMode
	}
	
	public static boolean noOtherActions(Piece testPiece, Player turnPlayer) {//check if the passes player has any othe pieces that have acted this turn
		int checkVal=0;
		for (int i=0; i<5; i++) {
			if (turnPlayer.playersTeam.teamPieces[i] != testPiece) {
				if (turnPlayer.playersTeam.teamPieces[i].tookAction > 0 || turnPlayer.playersTeam.teamPieces[i].moved > 0) {
					checkVal++;
				}
			}
		}
		if (checkVal == 0) {
			return true; 
		}
		else {
			return false; 
		}
	}
	
	public static void endTurn(int numPlayers) {
    	int currTurn=whosTurn(numPlayers);
    	for (int i=0; i<5; i++) {//the end of the turn resets the counters
    		Player1.playersTeam.teamPieces[i].tookAction=0;
    		Player1.playersTeam.teamPieces[i].moved=0;
    		Player2.playersTeam.teamPieces[i].tookAction=0;
    		Player2.playersTeam.teamPieces[i].moved=0;
    		if(numPlayers==4) {
    			Player3.playersTeam.teamPieces[i].tookAction=0;
        		Player3.playersTeam.teamPieces[i].moved=0;
        		Player4.playersTeam.teamPieces[i].tookAction=0;
        		Player4.playersTeam.teamPieces[i].moved=0;
    		}
    	}
    	if(numPlayers==2){
    		if(currTurn==1){
    			System.out.println("Player " + currTurn + "'s turn has ended"); //tell user who's turn ended
    			System.out.println("It is Player 2's turn"); //tell user who's turn it is
    			turnSeed=2;
    		}
    		if(currTurn==2){
    			System.out.println("Player " + currTurn + "'s turn has ended");
    			System.out.println("It is Player 1's turn");
    			turnSeed=1;
    		}
    	}//end of if numPlayers==2
    	else{
    	if(currTurn==1){
    		if (Player2.piecesLeft>0) {//if player 1 has pieces
    			if(holdTurn==0) {//if it isnt searching for a turn to go to from a skipped player's turn
    				System.out.println("Player " + currTurn + "'s turn has ended");
    			}
    			else {//if it had to skip 1 or more players turns
    				System.out.println("Player " + holdTurn + "'s turn has ended");
    			}
    			System.out.println("It is Player 2's turn");
    			turnSeed=2;//increment turn
    			holdTurn=0;//reset holdTurn
    		}
    		else {//a players turn needs to be skipped
    			if(holdTurn==0) {
    				holdTurn=1;//keep track of the last player who has pieces
    			}
    			turnSeed=2;//increment turn seed
    			endTurn(numPlayers);//call end turn recursively until a player with pieces can be found
    		}
    	}
    	else if(currTurn==2){
    		if (Player3.piecesLeft>0) {
    			if(holdTurn==0) {
    				System.out.println("Player " + currTurn + "'s turn has ended");
    			}
    			else {
    				System.out.println("Player " + holdTurn + "'s turn has ended");
    			}
    			System.out.println("It is Player 3's turn");
    			turnSeed=3;
    			holdTurn=0;
    		}
    		else {
    			if(holdTurn==0) {
    				holdTurn=2;
    			}
    			turnSeed=3;
    			endTurn(numPlayers);
    		}
    	}
   
    	else if(currTurn==3) {
    		if (Player4.piecesLeft>0) {
    			if(holdTurn==0) {
    				System.out.println("Player " + currTurn + "'s turn has ended");
    			}
    			else {
    				System.out.println("Player " + holdTurn + "'s turn has ended");
    			}
    			System.out.println("It is Player 4's turn"); 
    			turnSeed=4;
    			holdTurn=0;
    		}
    		else {
    			if(holdTurn==0) {
    				holdTurn=3;
    			}
    			turnSeed=4;
    			endTurn(numPlayers);
    		}
    	}
    	else {
    		if(Player1.piecesLeft>0) {
    			if(holdTurn==0) {
    				System.out.println("Player " + currTurn + "'s turn has ended");
    			}
    			else {
    				System.out.println("Player " + holdTurn + "'s turn has ended");
    			}
				System.out.println("It is Player 1's turn");
    			turnSeed=1;
    			holdTurn=0;
    		}
    		else {
    			if(holdTurn==0) {
    				holdTurn=4;
    			}
    			turnSeed=1;
    			endTurn(numPlayers);
    		}
    	}
   } //end of if 4 players
    }
	
	public static int whosTurn(int numPlayers) {//this function determines who's turn it is based on the number of players
		if (numPlayers == 2) {
			if((turnNumber+turnSeed)%numPlayers == 1) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			if ((turnNumber+turnSeed)%numPlayers == 1) {
				return 1;
			}
			else if((turnNumber+turnSeed)%numPlayers == 2) {
				return 2;
			}
			else if((turnNumber+turnSeed)%numPlayers == 3) {
				return 3;
			}
			else {
				return 4;
			}
		}
	}
	
	public static Piece findPiece(Icon tempImg) {//returns the piece that corresponds to the given image
		Piece foundPiece = null;
		for (int i=0; i<5; i++) {
			 if(tempImg==Player1.PieceImages[i]) {
				 foundPiece=Player1.playersTeam.teamPieces[i];
			 }
			 if(tempImg==Player2.PieceImages[i]) {
				 foundPiece=Player2.playersTeam.teamPieces[i];
			 }
			 if(tempImg==Player3.PieceImages[i]) {
				 foundPiece=Player3.playersTeam.teamPieces[i];
			 }
			 if(tempImg==Player4.PieceImages[i]) {
				 foundPiece=Player4.playersTeam.teamPieces[i];
			 }
		 }
		return foundPiece;
	}
	
	public static int genTurnSeed(int numPlayers) {//randomly generates the player who is going first
		Random turnRand = new Random();
		return turnRand.nextInt(numPlayers)+1;
	}
	
	public static void swapImage(JButton currentButton, Icon tempImg) { //swaps the images on the tiles
		lastButtonPressed.setIcon(null); //sets the tile with the image null
		currentButton.setIcon(tempImg); //move image to tile that was clicked
		lastButtonPressed.revalidate(); //resets and updates button
		currentButton.revalidate(); 
	}
	
	public static void removeAllImages(int numPlayers, JButton tile[][], JFrame currFrame) {
		int currTurn=whosTurn(numPlayers);
		JButton currentButton;
    	if(currTurn==1){
    		for (int i=0; i<tile.length; i++) {//go through all the buttons and remove player 1's pieces
		 		for(int j=0; j<tile[i].length; j++) {
		 			currentButton=tile[i][j];
		 			Icon tempImg = currentButton.getIcon();
		 			if(tempImg==Player1.PieceImages[0]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player1.PieceImages[1]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player1.PieceImages[2]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player1.PieceImages[3]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 			if(tempImg==Player1.PieceImages[4]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 		}
		 			}//end of second for loop
    		Player1.piecesLeft=0; //set pieces left to 0
			System.out.println("Player 1 has conceded and lost the game"); //tell user who conceded
			playersLeft--;//decrement playersLeft since a player lost
    	}
    	else if(currTurn==2){
    		for (int i=0; i<tile.length; i++) {//go through all the buttons and remove player 1's pieces
		 		for(int j=0; j<tile[i].length; j++) {
		 			currentButton=tile[i][j];
		 			Icon tempImg = currentButton.getIcon();
		 			if(tempImg==Player2.PieceImages[0]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player2.PieceImages[1]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player2.PieceImages[2]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player2.PieceImages[3]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 			if(tempImg==Player2.PieceImages[4]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 		}
		 			}//end of second for loop
    		Player2.piecesLeft=0; //set pieces left to 0
			System.out.println("Player 2 has conceded and lost the game"); //tell user who conceded
			playersLeft--;
    	}
    	else if(currTurn==3) {
    		for (int i=0; i<tile.length; i++) {//go through all the buttons and remove player 1's pieces
		 		for(int j=0; j<tile[i].length; j++) {
		 			currentButton=tile[i][j];
		 			Icon tempImg = currentButton.getIcon();
		 			if(tempImg==Player3.PieceImages[0]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player3.PieceImages[1]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player3.PieceImages[2]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player3.PieceImages[3]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 			if(tempImg==Player3.PieceImages[4]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 		}
		 			}//end of second for loop
    		Player3.piecesLeft=0; //set pieces left to 0
			System.out.println("Player 3 has conceded and lost the game"); //tell user who conceded
			playersLeft--;
			
    	}
    	else {
    		for (int i=0; i<tile.length; i++) {//go through all the buttons and remove player 1's pieces
		 		for(int j=0; j<tile[i].length; j++) {
		 			currentButton=tile[i][j];
		 			Icon tempImg = currentButton.getIcon();
		 			if(tempImg==Player4.PieceImages[0]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player4.PieceImages[1]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player4.PieceImages[2]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}
		 			if(tempImg==Player4.PieceImages[3]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 			if(tempImg==Player4.PieceImages[4]) {
		 				currentButton.setIcon(null);
		 				currentButton.revalidate();
		 			}	
		 		}
		 			}//end of second for loop
    		Player4.piecesLeft=0; //set pieces left to 0
			System.out.println("Player 4 has conceded and lost the game"); //tell user who conceded
			playersLeft--;
    	}
    	if (playersLeft==1) {//if theres only 1 player left
    		JOptionPane.showMessageDialog(currFrame, "The Game is is over. Closing game...", "Game Over", JOptionPane.PLAIN_MESSAGE);
			//Display message that the game is ended^
    		System.exit(0);//exit program
    		/*int newGameVar=JOptionPane.showConfirmDialog(currFrame, "This game has ended. Would you like to play again?"); 
    		if (newGameVar==JOptionPane.YES_OPTION) {
    			System.out.println("Playing Again...");
    			currFrame.dispose();
    			JFrame frame1= new JFrame();
    	    	JFrame frame2= new JFrame();
    	    	JFrame frame3= new JFrame();
    	    	createGui1(frame1, frame2, frame3);
    			
    		}
    		else if (newGameVar==JOptionPane.NO_OPTION) {
    			System.out.println("Not Playing Again, Closing Game...");
    			System.exit(0);
    		}
    		else {
    			System.out.println("Not Playing Again, Closing Game...");
    			System.exit(0);
    		}*/
		}
	}
	
	public static void removeImage(JButton currentButton, JFrame currFrame) {
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
					playersLeft--;//decrement playersLeft since a player lost
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
					playersLeft--;
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
					playersLeft--;
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
					playersLeft--;
				}
			}
		}
		currentButton.setIcon(null);
		currentButton.revalidate();
		if (playersLeft==1) {//if only 1 player is left
			JOptionPane.showMessageDialog(currFrame, "The Game is is over. Closing game...", "Game Over", JOptionPane.PLAIN_MESSAGE);
			//Display message that the game is ended^
			System.exit(0);//exit program
    		/*int newGameVar=JOptionPane.showConfirmDialog(currFrame, "This game has ended. Would you like to play again?"); 
    		if (newGameVar==JOptionPane.YES_OPTION) {
    			System.out.println("Playing Again...");
    			currFrame.dispose();
    			JFrame frame1= new JFrame();
    	    	JFrame frame2= new JFrame();
    	    	JFrame frame3= new JFrame();
    	    	createGui1(frame1, frame2, frame3);
    			
    		}
    		else if (newGameVar==JOptionPane.NO_OPTION) {
    			System.out.println("Not Playing Again, Closing Game...");
    			System.exit(0);
    		}
    		else {
    			System.out.println("Not Playing Again, Closing Game...");
    			System.exit(0);
    		}*/
		}
	}
	
	public static void setCoordinates(JButton currentButton, int numPlayers, JButton tile[][] ) {//set the coordinate of current clicked game piece and output what piece it is
		 if(currentButton.getIcon()==Player1.PieceImages[0]) {
			 //System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[0].name);
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
			 //System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[1].name);
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
		 if(currentButton.getIcon()==Player1.PieceImages[2]) {
			 //System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[2].name);
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
		 if(currentButton.getIcon()==Player1.PieceImages[3]) {
			 //System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[3].name);
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
		 if(currentButton.getIcon()==Player1.PieceImages[4]) {
			 //System.out.println("This is player 1's " + Player1.playersTeam.teamPieces[4].name);
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
		 if(currentButton.getIcon()==Player2.PieceImages[0]) {
			 //System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[0].name);
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
		 if(currentButton.getIcon()==Player2.PieceImages[1]) {
			 //System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[1].name);
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
		 if(currentButton.getIcon()==Player2.PieceImages[2]) {
			 //System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[2].name);
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
		 if(currentButton.getIcon()==Player2.PieceImages[3]) {
			 //System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[3].name);
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
		 if(currentButton.getIcon()==Player2.PieceImages[4]) {
			 //System.out.println("This is player 2's " + Player2.playersTeam.teamPieces[4].name);
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
		 if(numPlayers==4) {
			 if(currentButton.getIcon()==Player3.PieceImages[0]) {
			 //System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[0].name);
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
			 if(currentButton.getIcon()==Player3.PieceImages[1]) {
			 //System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[1].name);
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
			 if(currentButton.getIcon()==Player3.PieceImages[2]) {
			 //System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[2].name);
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
			 if(currentButton.getIcon()==Player3.PieceImages[3]) {
			 //System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[3].name);
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
			 if(currentButton.getIcon()==Player3.PieceImages[4]) {
			 //System.out.println("This is player 3's " + Player3.playersTeam.teamPieces[4].name);
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
			 if(currentButton.getIcon()==Player4.PieceImages[0]) {
			 //System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[0].name);
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
			 if(currentButton.getIcon()==Player4.PieceImages[1]) {
			 //System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[1].name);
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
			 if(currentButton.getIcon()==Player4.PieceImages[2]) {
			 //System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[2].name);
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
			 if(currentButton.getIcon()==Player4.PieceImages[3]) {
			 //System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[3].name);
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
			 if(currentButton.getIcon()==Player4.PieceImages[4]) {
			 //System.out.println("This is player 4's " + Player4.playersTeam.teamPieces[4].name);
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
	        			  playersLeft = 2;
	        			  gameModeSelected=0;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        			  }
	        		 else if(currentButton==gameMode[1]) {
	        			 System.out.println("1 Player vs 1 Computer Player game mode selected");
	        			 numPlayers= 2;
	        			 playersLeft = 2;
	        			 gameModeSelected=1;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else if(currentButton==gameMode[2]) {
	        			System.out.println("Two-Versus-Two Team Battle game mode selected");
	        			numPlayers=4;
	        			playersLeft = 4;
	        			gameModeSelected=2;
	        			  createGui2(frame1, frame2, frame3, numPlayers);
	        		 }
	        		 else {
	        			 System.out.println("4 Player Free-for-All Game game mode selected");
	        			 numPlayers=4;
	        			 playersLeft = 4;
	        			 gameModeSelected=3;
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
		timesClicked=0;
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
	        				 if(gameModeSelected==1) {
	        					 createGui4(frame2, frame3, numPlayers); //go to next GUI
	        				 }
	        				 else {
	        				 createGui3(frame2, frame3, numPlayers); //go to next GUI
	        				 }
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
	        				 if(gameModeSelected==1) {
	        					 createGui4(frame2, frame3, numPlayers); //go to next GUI
	        				 }
	        				 else {
	        				 createGui3(frame2, frame3, numPlayers);
	        				 }
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
	        				 if(gameModeSelected==1) {
	        					 createGui4(frame2, frame3, numPlayers); //go to next GUI
	        				 }
	        				 else {
	        				 createGui3(frame2, frame3, numPlayers);
	        				 }
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
	        				 if(gameModeSelected==1) {
	        					 createGui4(frame2, frame3, numPlayers); //go to next GUI
	        				 }
	        				 else {
	        				 createGui3(frame2, frame3, numPlayers);
	        				 }
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
        turnSeed = genTurnSeed(numPlayers);
		System.out.println("Player " + turnSeed + " will be going first");
        set2v2Teams(gameModeSelected, numPlayers);
		
        class healGamePiece implements ActionListener{
        	JButton currentButton=null;
        	Piece tempPiece;
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
   						 tempPiece = findPiece(tempImg);//find the piece that has been clicked
   			if (whosTurn(numPlayers) == 1) {//if it is player 1's turn
   			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){ //check only locations 3 &4 bc teams sets healers to those locations & check to make sure piece is healer
   				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing itself");//message to user
   				Piece targetPiece;//alias for piece being healed
   				targetPiece = Player1.playersTeam.teamPieces[3];//set target piece to the healer
   				int tempHp = targetPiece.getCurrHp();//save hp it currently has
   					
   				if(tempHp != targetPiece.getMaxHp()){
   				Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal piece
   				Player1.playersTeam.teamPieces[3].tookAction++;
   					if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the maximum hp
   						targetPiece.currHp=targetPiece.getMaxHp();
   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   						if(Player1.playersTeam.teamPieces[3].moved==1) {
   						endTurn(numPlayers); //prints out turn ended & next player
   						turnSeed=2; //set the player to player 2
   						}
   					}
   					else {
   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   						if(Player1.playersTeam.teamPieces[3].moved==1) {
   						endTurn(numPlayers);
   						turnSeed=2;	
   						}
   					}
   				}
   				else {
   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   					}
   			}//end of action check
   			else{
   				System.out.println("Only one piece may move and act once each turn");
   			}
   			}
   			
   			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
   				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " is healing itself");
   					Piece targetPiece;
   					targetPiece = Player1.playersTeam.teamPieces[4];
   					int tempHp = targetPiece.getCurrHp();
   					
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player1.playersTeam.teamPieces[4].heal(targetPiece);
   						Player1.playersTeam.teamPieces[4].tookAction++;
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   							if(Player1.playersTeam.teamPieces[4].moved==1) {
   							endTurn(numPlayers);
   							turnSeed=2;
   							}
   						}
   							
   						else {
   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   							if(Player1.playersTeam.teamPieces[4].moved==1) {
   							endTurn(numPlayers);
   							turnSeed=2;
   							}
   						}
   					}
   				else {
   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   					}
   			}//end of action check
   			else{
   	   			System.out.println("Only one piece may move and act once each turn");
   	   		}
   			}	
   			}//end of if it is player 1's turn
   			else if(tempPiece.team == 1){//if player 1's piece has been clicked and it is not their turn
   				System.out.println("It is not Player 1's turn");//send error message
   			}
   			
   			if (whosTurn(numPlayers) == 2) {
   			if(tempImg==Player2.PieceImages[3] && Player2.playersTeam.teamPieces[3].name == "Healer") {
   				if (noOtherActions(Player2.playersTeam.teamPieces[3], Player2) && Player2.playersTeam.teamPieces[3].tookAction<1) {
   					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " is healing itself");
   					Piece targetPiece;
   					targetPiece = Player2.playersTeam.teamPieces[3];
   					int tempHp = targetPiece.getCurrHp();
   					
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player2.playersTeam.teamPieces[3].heal(targetPiece);
   						Player2.playersTeam.teamPieces[3].tookAction++;
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   						if(Player2.playersTeam.teamPieces[3].moved==1) {
   						endTurn(numPlayers); //prints out turn ended & next player
   						if(numPlayers==2) { //if only 2 players, set next to player 1
   							turnSeed=1;
   							}
   						else {
   							turnSeed=3; //if 4 players, player 3 will go next
   							}
   						}
   						}
   						else {
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   							if(Player2.playersTeam.teamPieces[3].moved==1) {
   							endTurn(numPlayers);
   	   						if(numPlayers==2) {
   	   							turnSeed=1;
   	   							}
   	   						else {
   	   							turnSeed=3;
   	   							}
   							}
   						}
   					}
   			else {
   				System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   				}
   			}//end of action check
   			else{
   	   			System.out.println("Only one piece may move and act once each turn");
   	   		}
   			}
   			
   			if(tempImg==Player2.PieceImages[4] && Player2.playersTeam.teamPieces[4].name == "Healer") {
   				if (noOtherActions(Player2.playersTeam.teamPieces[4], Player2) && Player2.playersTeam.teamPieces[4].tookAction<1) {
   				System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " is healing itself");
   				Piece targetPiece;
   				targetPiece = Player2.playersTeam.teamPieces[4];
   				int tempHp = targetPiece.getCurrHp();
   					if(tempHp != targetPiece.getMaxHp()) {
   						Player2.playersTeam.teamPieces[4].heal(targetPiece);
   						Player2.playersTeam.teamPieces[4].tookAction++;
   						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
   							targetPiece.currHp=targetPiece.getMaxHp();
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   							if(Player2.playersTeam.teamPieces[4].moved==1) {
   							endTurn(numPlayers);
   	   						if(numPlayers==2) {
   	   							turnSeed=1;
   	   							}
   	   						else {
   	   							turnSeed=3;
   	   							}
   							}	
   						}
   						else {
   							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
   							if(Player2.playersTeam.teamPieces[4].moved==1) {
   							endTurn(numPlayers);
   	   						if(numPlayers==2) {
   	   							turnSeed=1;
   	   							}
   	   						else {
   	   							turnSeed=3;
   	   							}
   							}
   						}
   					}
   					else {
   						System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   						}
   			}//end of action check
   			else{
   	   			System.out.println("Only one piece may move and act once each turn");
   	   		}
   			}
   			}//end of if it is player 2's turn
   			else if(tempPiece.team == 2){
   				System.out.println("It is not Player 2's turn");
   			}
   			
   			 if(numPlayers==4) {
   				if (whosTurn(numPlayers) == 3) {
   				 if(tempImg==Player3.PieceImages[3] && Player3.playersTeam.teamPieces[3].name == "Healer") {
   					if (noOtherActions(Player3.playersTeam.teamPieces[3], Player3) && Player3.playersTeam.teamPieces[3].tookAction<1) {
         			System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " is healing itself");
           			Piece targetPiece;	
           			targetPiece = Player3.playersTeam.teamPieces[3];
           			int tempHp = targetPiece.getCurrHp();
           			
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player3.playersTeam.teamPieces[3].heal(targetPiece);
           					Player3.playersTeam.teamPieces[3].tookAction++;
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           						if(Player3.playersTeam.teamPieces[3].moved==1) {
           						endTurn(numPlayers); //prints out turn ended & next player
           						turnSeed=4; //sets next player to player 4
           						}
           					}
           							
           					else {
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           						if(Player3.playersTeam.teamPieces[3].moved==1) {
           						endTurn(numPlayers);
               					turnSeed=4;
           						}
           					}
           				}
           			else {
           				System.out.println("Player 3's " + targetPiece.name + " is already at full health");
           				}
         			}//end of action check 
   					else{
   		   				System.out.println("Only one piece may move and act once each turn");
   		   			}
   				 }
   				 
   				 if(tempImg==Player3.PieceImages[4] && Player3.playersTeam.teamPieces[4].name =="Healer") {
   					if (noOtherActions(Player3.playersTeam.teamPieces[4], Player3) && Player3.playersTeam.teamPieces[4].tookAction<1) {
        			System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " is healing itself");
           			Piece targetPiece;
           			targetPiece = Player3.playersTeam.teamPieces[4];
           			int tempHp = targetPiece.getCurrHp();
           			
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player3.playersTeam.teamPieces[4].heal(targetPiece);
           					Player3.playersTeam.teamPieces[4].tookAction++;
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           						if(Player3.playersTeam.teamPieces[4].moved==1) {
           						endTurn(numPlayers);
               					turnSeed=4;
           						}
           					}
           							
           					else {
           						System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           						if(Player3.playersTeam.teamPieces[4].moved==1) {
           						endTurn(numPlayers);
               					turnSeed=4;
           						}
           					}
           				}
           				else {
           					System.out.println("Player 3's " + targetPiece.name + " is already at full health");
           					}	
        			}//end of action check
   					else{
   		   				System.out.println("Only one piece may move and act once each turn");
   		   			}
   				 	}
   					}//end of if it is player 3's turn	
   					else if(tempPiece.team == 3){
   						System.out.println("It is not Player 3's turn");
   					}
   				
   				if (whosTurn(numPlayers) == 4) {
  					 if(tempImg==Player4.PieceImages[3] && Player4.playersTeam.teamPieces[3].name=="Healer") {
  						if (noOtherActions(Player4.playersTeam.teamPieces[3], Player4) && Player4.playersTeam.teamPieces[3].tookAction<1) {
  						System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " is healing itself");
           				Piece targetPiece;
           				targetPiece = Player4.playersTeam.teamPieces[3];
           				int tempHp = targetPiece.getCurrHp();
           				
           				if(tempHp != targetPiece.getMaxHp()) {
           					Player4.playersTeam.teamPieces[3].heal(targetPiece);
           					Player4.playersTeam.teamPieces[3].tookAction++;
           					if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           						targetPiece.currHp=targetPiece.getMaxHp();
           						System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           						if(Player4.playersTeam.teamPieces[3].moved==1) {
           						endTurn(numPlayers);//prints out turn ended & next player
               					turnSeed=1; //sets next player to player 1
           						}
           					}
           					else {
           						System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           						if(Player4.playersTeam.teamPieces[3].moved==1) {
           						endTurn(numPlayers);
               					turnSeed=1;
           						}
           					}
           				}
           				else {
           				System.out.println("Player 4's " + targetPiece.name + "is already at full health");
           				}
   					 }//end of action check
  					else{
  			   			System.out.println("Only one piece may move and act once each turn");
  			   		}
  					 }
  						
  					 if(tempImg==Player4.PieceImages[4]) {
  						if (noOtherActions(Player4.playersTeam.teamPieces[4], Player4) && Player4.playersTeam.teamPieces[4].tookAction<1) {
  						System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " is healing itself");
           				Piece targetPiece;
           				targetPiece = Player4.playersTeam.teamPieces[4];
           				int tempHp = targetPiece.getCurrHp();
           				
           					if(tempHp != targetPiece.getMaxHp()) {
           						Player4.playersTeam.teamPieces[4].heal(targetPiece);
           						Player4.playersTeam.teamPieces[4].tookAction++;
           						if(targetPiece.currHp> targetPiece.getMaxHp()) {//don't want negative numbers for hp
           							targetPiece.currHp=targetPiece.getMaxHp();
           							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
           							if(Player4.playersTeam.teamPieces[4].moved==1) {
           							endTurn(numPlayers);
                   					turnSeed=1;
           							}
           						}
           						else {
           							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
           							if(Player4.playersTeam.teamPieces[4].moved==1) {
           							endTurn(numPlayers);
                   					turnSeed=1;
           							}
           						}
           					}
           					else {
           						System.out.println("Player 4's " + targetPiece.name + "is already at full health");
           					}			
  					 }//end of action check
  						else{
  			   				System.out.println("Only one piece may move and act once each turn");
  			   			}
  					 }
   				}//end of if it is player 4's turn
   				else if(tempPiece.team == 4){
   					System.out.println("It is not Player 4's turn");
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
       						 tempPiece = findPiece(tempImg);
       			
       			if (whosTurn(numPlayers) == 1) {
       			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){//checks 3& 4 bc teams only set those as healers & make sure it is a healer
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
       					//This checks for the range of the healing piece^
       				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
       				if (currentButton.getIcon()==Player1.PieceImages[0] || currentButton.getIcon()==Player1.PieceImages[1] || currentButton.getIcon()==Player1.PieceImages[2] || currentButton.getIcon()==Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing");//message to user
       					Piece targetPiece;//alias for piece being healed
       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       							if(tempHp != targetPiece.getMaxHp()){
       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
       							Player1.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								if(Player1.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers); //prints out turn ended & next player
       								turnSeed=2; //sets next player to player 2
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       							if(Player1.playersTeam.teamPieces[3].moved==1) {
       							endTurn(numPlayers);
       							turnSeed=2;
       							}
       							}
       							}
       							else {
       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       							}
       					}
       					}
       				}
       				else if(gameModeSelected==2) {
       					if(team1[0]=="Player 1" || team1[1]=="Player 1") {//if player 1 is in team 1
   	       					if(team1[0]=="Player 2"||team1[1]=="Player 2") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 2
   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}//end of heal player 4
   	       					
   	       				}//end of if player 1 is in team 1
       					else if(team2[0]=="Player 1" || team2[1] =="Player 1") { //if player 1 is in team 2
   	       				if(team2[0]=="Player 2"||team2[1]=="Player 2") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 2
   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}	
   	       				
   	       				}//end of if player 1 is in team 2	
       				}//end of if gameMode 2
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       			}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       			}//end of range check
       			else {
       				System.out.println("That is an invalid heal, cannot a piece further than you healer's range");
       			}
       			}
       			
       			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
       				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
       				if(currentButton.getIcon()==Player1.PieceImages[0] || currentButton.getIcon()==Player1.PieceImages[1] || currentButton.getIcon()==Player1.PieceImages[2] || currentButton.getIcon()==Player1.PieceImages[3]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player1.playersTeam.teamPieces[4].heal(targetPiece);
       							Player1.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								if(Player1.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
           							turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[4].moved==1) {
       							endTurn(numPlayers);
       							turnSeed=2;
       							}
       							}
       							}
       							else {
       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       							}
       						}
       					}
       				}
       				else if(gameModeSelected==2) {
       					if(team1[0]=="Player 1" || team1[1]=="Player 1") {//if player 1 is in team 1
   	       					if(team1[0]=="Player 2"||team1[1]=="Player 2") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 2
   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}//end of heal player 4
   	       					
   	       				}//end of if player 1 is in team 1
       					else if(team2[0]=="Player 1" || team2[1] =="Player 1") { //if player 1 is in team 2
   	       				if(team2[0]=="Player 2"||team2[1]=="Player 2") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 2
   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player1.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}	
   	       				
   	       				}//end of if player 1 is in team 2
       				}//end of if 2v2 game mode check
       				else{
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than healer's range");
       				}
       			}			 
       			}//end of if it is player 1's turn
       			else if(tempPiece.team == 1){
       				System.out.println("It is not Player 1's turn");
       			}

       			if (whosTurn(numPlayers) == 2) {
       			if(tempImg==Player2.PieceImages[3] && Player2.playersTeam.teamPieces[3].name == "Healer") {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[3], Player2) && Player2.playersTeam.teamPieces[3].tookAction<1) {
       				if (currentButton.getIcon()==Player2.PieceImages[0] || currentButton.getIcon()==Player2.PieceImages[1] || currentButton.getIcon()==Player2.PieceImages[2] || currentButton.getIcon()==Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player2.playersTeam.teamPieces[3].heal(targetPiece);
       							Player2.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								if(Player2.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers); //prints out turn ended & next player
       								if(numPlayers==2){ //if 2 players, set next to player 1
       									turnSeed=1;
       								}
       								else { //if 4 players, set next to player 3
       									turnSeed=3; 
       								}
       								}
       							}
       							
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[3].moved==1) {
       							endTurn(numPlayers);
       							if(numPlayers==2){
       								turnSeed=1;
       							}
       							else {
       								turnSeed=3;
       							}
       							}
       							}
       						}
       							else {
       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       							}
       						}
       					}
       				}
       					
       				else if(gameModeSelected==2) {
       					if(team1[0]=="Player 2" || team1[1]=="Player 2") {//if player 2 is in team 1
   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 1
   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4"){
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}//end of heal player 4
   	       					
   	       				}//end of if player 1 is in team 1
   	       				else if(team2[0]=="Player 2" || team2[1] =="Player 2") { //if player 2 is in team 2
   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 1
   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[3].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[3].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[3].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of else if	
   	       				}//end of if player 2 is in team 2	
       				}
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than healer's range");
       				}
       			}
       			
       			if(tempImg==Player2.PieceImages[4] && Player2.playersTeam.teamPieces[4].name == "Healer") {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[4], Player2) && Player2.playersTeam.teamPieces[4].tookAction<1) {
       				if (currentButton.getIcon()==Player2.PieceImages[0] || currentButton.getIcon()==Player2.PieceImages[1] || currentButton.getIcon()==Player2.PieceImages[2] || currentButton.getIcon()==Player2.PieceImages[3]){
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " is healing");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							if(tempHp != targetPiece.getMaxHp()) {
       							Player2.playersTeam.teamPieces[4].heal(targetPiece);
       							Player2.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
       								targetPiece.currHp=targetPiece.getMaxHp();
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       								if(Player2.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
           							if(numPlayers==2){
           								turnSeed=1;
           							}
           							else {
           								turnSeed=3;
           							}
       								}
       							}
       							
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[4].moved==1) {
       							endTurn(numPlayers);
       							if(numPlayers==2){
       								turnSeed=1;
       							}
       							else {
       								turnSeed=3;
       							}
       							}
       							}
       						}
       							else {
       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       							}
       					}
       					}
       				}
       				else if(gameModeSelected==2) {
       					if(team1[0]=="Player 2" || team1[1]=="Player 2") {//if player 2 is in team 1
   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 1
   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4"){
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       						
   	       					}//end of heal player 4
   	       					
   	       				}//end of if player 2 is in team 1
   	       				else if(team2[0]=="Player 2" || team2[1] =="Player 2") { //if player 2 is in team 2
   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}
   	       							
   	       					}//end of if player 1
   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of if player 3
   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
   	       					Piece targetPiece;//alias for piece being healed
   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
   	       							if(tempHp != targetPiece.getMaxHp()){
   	       							Player2.playersTeam.teamPieces[4].heal(targetPiece);//heal
   	       							Player2.playersTeam.teamPieces[4].tookAction++;
   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
   	       								targetPiece.currHp=targetPiece.getMaxHp();
   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
   	       								if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       								endTurn(numPlayers); //prints out turn ended & next player
   	       								turnSeed=2; //sets next player to player 2
   	       								}
   	       							}
   	       							else {
   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
   	       							if(Player2.playersTeam.teamPieces[4].moved==1) {
   	       							endTurn(numPlayers);
   	       							turnSeed=2;
   	       							}
   	       							}
   	       							}
   	       							else {
   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
   	       							}
   	       						}
   	       					}	
   	       					}//end of else if	
   	       				}//end of if player 2 is in team 2 	     
       				}//end of 2v2 game mode check
       				else {
       					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       				}
       			}
       			}//end of if it is player 2's turn
       			else if(tempPiece.team == 2){
       				System.out.println("It is not Player 2's turn");
       			}

       			 if(numPlayers==4) {
       				if (whosTurn(numPlayers) == 3) {
       				 if(tempImg==Player3.PieceImages[3] && Player3.playersTeam.teamPieces[3].name == "Healer") {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[3], Player3) && Player3.playersTeam.teamPieces[3].tookAction<1) {
       						if (currentButton.getIcon()==Player3.PieceImages[0] || currentButton.getIcon()==Player3.PieceImages[1] || currentButton.getIcon()==Player3.PieceImages[2] || currentButton.getIcon()==Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player3.playersTeam.teamPieces[3].heal(targetPiece);
               							Player3.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								if(Player3.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers); //prints out turn ended & next player
               								turnSeed=4; //sets next player to player 4
               								}
               							}
               							else {
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               								if(Player3.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               					}
               							else {
               								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
               							}
               						}
               					}
           				}
       						else if(gameModeSelected==2) {
       							if(team1[0]=="Player 3" || team1[1]=="Player 3") {//if player 3 is in team 1
       	   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team1[0]=="Player 2" || team1[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 3
       	   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4"){
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of heal player 4
       	   	       					
       	   	       				}//end of if player 1 is in team 1
       	   	       				else if(team2[0]=="Player 3" || team2[1] =="Player 3") { //if player 3 is in team 2
       	   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of else if	
       	   	       				
       	   	       				}//end of if player 3 is in team 2	
       						}  						
       					else {
           					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       					}
             			} 
       				 
       				 if(tempImg==Player3.PieceImages[4] && Player3.playersTeam.teamPieces[4].name =="Healer") {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[4], Player3) && Player3.playersTeam.teamPieces[4].tookAction<1) {
       						if (currentButton.getIcon()==Player3.PieceImages[0] || currentButton.getIcon()==Player3.PieceImages[1] || currentButton.getIcon()!=Player3.PieceImages[2] || currentButton.getIcon()==Player3.PieceImages[3]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player3.playersTeam.teamPieces[4].heal(targetPiece);
               							Player3.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								if(Player3.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
                   							turnSeed=4;
               								}
               							}
               							
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
               							turnSeed=4;
               							}
               							}
               						}
               							else {
               								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
               							}	
               					}
               					}
           				}
       						else if(gameModeSelected==2) {
       							if(team1[0]=="Player 3" || team1[1]=="Player 3") {//if player 3 is in team 1
       	   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team1[0]=="Player 2" || team1[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 3
       	   	       					else if(team1[0]=="Player 4" || team1[1]=="Player 4"){
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of heal player 4
       	   	       					
       	   	       				}//end of if player 1 is in team 1
       	   	       				else if(team2[0]=="Player 3" || team2[1] =="Player 3") { //if player 3 is in team 2
       	   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team2[0]=="Player 4" || team2[1]=="Player 4") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player4.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player4.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player3.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player3.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player3.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 4's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of else if	
       	   	       				
       	   	       				}//end of if player 3 is in team 2	
       						}	
           				else {
           					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
       					}
            			}
       					}//end of if it is player 3's turn
       					else if(tempPiece.team == 3){
       						System.out.println("It is not Player 3's turn");
       					}
       				
       					if (whosTurn(numPlayers) == 4) {
      					 if(tempImg==Player4.PieceImages[3] && Player4.playersTeam.teamPieces[3].name=="Healer") {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[3], Player4) && Player4.playersTeam.teamPieces[3].tookAction<1) {
      						if (currentButton.getIcon()==Player4.PieceImages[0] || currentButton.getIcon()==Player4.PieceImages[1] || currentButton.getIcon()==Player4.PieceImages[2] || currentButton.getIcon()==Player4.PieceImages[4]){
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(tempHp != targetPiece.getMaxHp()) {
               							Player4.playersTeam.teamPieces[3].heal(targetPiece);
               							Player4.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               								if(Player4.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers); //prints out turn ended & next player
                   							turnSeed=1; //sets next player to player 1
               								}
               							}
               						
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
               							turnSeed=1;
               							}
               							}
               						}
               							else {
               								System.out.println("Player 4's " + targetPiece.name + "is already at full health");
               							}
               					}
               					}
               				}
      						else if(gameModeSelected==2) {
      							if(team1[0]=="Player 4" || team1[1]=="Player 4") {//if player 4 is in team 1
       	   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team1[0]=="Player 2" || team1[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3"){
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of heal player 3
       	   	       					
       	   	       				}//end of if player 1 is in team 1
       	   	       				else if(team2[0]=="Player 4" || team2[1] =="Player 4") { //if player 4 is in team 2
       	   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[3].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[3].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[3].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of else if	
       	   	       				
       	   	       				}//end of if player 4 is in team 2		
      						}
               				else {
               					System.out.println("That is an invalid heal, cannot heal other team's game pieces");
               				} 
      						}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
      						}
       					 }
      					 
      					 if(tempImg==Player4.PieceImages[4] && Player4.playersTeam.teamPieces[4].name=="Healer"){
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[4], Player4) && Player4.playersTeam.teamPieces[4].tookAction<1) {
      						if (currentButton.getIcon()==Player4.PieceImages[0] || currentButton.getIcon()==Player4.PieceImages[1] || currentButton.getIcon()==Player4.PieceImages[2] || currentButton.getIcon()==Player4.PieceImages[3]){
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " is healing");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               							Player4.playersTeam.teamPieces[4].heal(targetPiece);
               							Player4.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp> targetPiece.getMaxHp()) {
               								targetPiece.currHp=targetPiece.getMaxHp();
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								if(Player4.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
                   							turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
               							turnSeed=1;
               							}
               							}
               						}
               						}
               					}
               				}
      						else if(gameModeSelected==2) {
      							if(team1[0]=="Player 4" || team1[1]=="Player 4") {//if player 4 is in team 1
       	   	       					if(team1[0]=="Player 1"||team1[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team1[0]=="Player 2" || team1[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team1[0]=="Player 3" || team1[1]=="Player 3"){
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of heal player 3
       	   	       					
       	   	       				}//end of if player 1 is in team 1
       	   	       				else if(team2[0]=="Player 4" || team2[1] =="Player 4") { //if player 4 is in team 2
       	   	       				if(team2[0]=="Player 1"||team2[1]=="Player 1") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 1's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}
       	   	       							
       	   	       					}//end of if player 1
       	   	       					else if(team2[0]=="Player 2" || team2[1]=="Player 2") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 2's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       					}//end of if player 2
       	   	       					else if(team2[0]=="Player 3" || team2[1]=="Player 3") {
       	   	       					Piece targetPiece;//alias for piece being healed
       	   	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
       	   	       						if(currentButton.getIcon()==Player3.PieceImages[i]) {//if found here
       	   	       							targetPiece = Player3.playersTeam.teamPieces[i];//set target piece to alias the found piece
       	   	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       	   	       							if(tempHp != targetPiece.getMaxHp()){
       	   	       							Player4.playersTeam.teamPieces[4].heal(targetPiece);//heal
       	   	       							Player4.playersTeam.teamPieces[4].tookAction++;
       	   	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
       	   	       								targetPiece.currHp=targetPiece.getMaxHp();
       	   	       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       	   	       								if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       								endTurn(numPlayers); //prints out turn ended & next player
       	   	       								turnSeed=2; //sets next player to player 2
       	   	       								}
       	   	       							}
       	   	       							else {
       	   	       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       	   	       							if(Player4.playersTeam.teamPieces[4].moved==1) {
       	   	       							endTurn(numPlayers);
       	   	       							turnSeed=2;
       	   	       							}
       	   	       							}
       	   	       							}
       	   	       							else {
       	   	       								System.out.println("Player 3's " + targetPiece.name + " is already at full health");
       	   	       							}
       	   	       						}
       	   	       					}	
       	   	       						
       	   	       					}//end of else if	
       	   	       				
       	   	       				}//end of if player 4 is in team 2	
      						}
      						else {
               					System.out.println("That is an invalid heal, cannot heal the other team's game pieces");
               				}
      						}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid heal, cannot heal piece further than your healer's range");
      						}
       					 }		
       					}//end of if it is player 4's turn
       					else if(tempPiece.team == 4){
       						System.out.println("It is not Player 4's turn");
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
        	Piece tempPiece;
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
       						 tempPiece = findPiece(tempImg);
       			if (whosTurn(numPlayers) == 1) {
       			if(tempImg==Player1.PieceImages[0])	{
       			//check to make sure piece to be attacked isn't the player's pieces 
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].range) {
       					//This checks for the range of the attacking piece^
       				if (noOtherActions(Player1.playersTeam.teamPieces[0], Player1) && Player1.playersTeam.teamPieces[0].tookAction<1) {
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[0].name + " attacks!");//message to user
       					Piece targetPiece;//alias for piece being attacked
       					for(int i=0; i<5; i++) {//find piece being attacked, searches all 3 opposing teams for a match
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
       							if(gameModeSelected==2) {
       							attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2 game mode
       							else {
       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);//make attack
       							Player1.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
       								if(Player1.playersTeam.teamPieces[0].moved==1) {
       									endTurn(numPlayers); //prints out turn ended & next player
           								turnSeed=2; //sets next to player 2
       								}
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
       							if(Player1.playersTeam.teamPieces[0].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       							}
       						}
       					}
       					if(numPlayers == 4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);
       							Player1.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[0].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[0].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[0].attack(targetPiece);
       							Player1.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[0].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       								}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[0].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				 else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       			}//end of range check
       			else {
       				System.out.println("That is an invalid attack, cannot a piece further than your piece's range");
       			}
       			}
       			if(tempImg==Player1.PieceImages[1]){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].range) {
       				if (noOtherActions(Player1.playersTeam.teamPieces[1], Player1) && Player1.playersTeam.teamPieces[1].tookAction<1) {
       				if (currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[1].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							if(gameModeSelected==2) {
       							attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
       							}//end of if 2v2
       							else {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							Player1.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[1].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[1].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       							attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							Player1.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[1].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       								}
       							else { 
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       								if(Player1.playersTeam.teamPieces[1].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
       							Player1.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[1].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[1].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       							}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("that is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player1.PieceImages[2]){
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].range) {
       				if (noOtherActions(Player1.playersTeam.teamPieces[2], Player1) && Player1.playersTeam.teamPieces[2].tookAction<1) {
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							else {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							Player1.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[2].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       						if(gameModeSelected==2) {
       							attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       						}
       						else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							Player1.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[2].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
       							}//end of if 2v2
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
       							Player1.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[2].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					}
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}			 
       			if(tempImg==Player1.PieceImages[3]) {
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
       				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[4]) {
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							else {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							Player1.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[3].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
       							}//end of if 2v2
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							Player1.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[3].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);		
       							}//end of if 2v2
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
       							Player1.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[3].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					}
       				}
       				else{
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}	
       			
       			if(tempImg==Player1.PieceImages[4]) {
       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
       				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[0]) {
       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
       							}//end of of2v2
       							
       							else {
       							targetPiece = Player2.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							Player1.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[4].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							Player1.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[4].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       							}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);		
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
       							Player1.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player1.playersTeam.teamPieces[4].moved==1) {
       									endTurn(numPlayers);
       									turnSeed=2;
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player1.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								turnSeed=2;
       							}
       							}
       						}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			}//end of if it is player 1's turn
       			else if(tempPiece.team == 1){
       				System.out.println("It is not Player 1's turn");
       			}
       			if (whosTurn(numPlayers) == 2) {
       			if(tempImg==Player2.PieceImages[0]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[0].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[0], Player2) && Player2.playersTeam.teamPieces[0].tookAction<1) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[0].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							Player2.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[0].moved==1) {
       									endTurn(numPlayers); //prints out turn ended & next player
       									if(numPlayers==2){ //if 2 players, set next to player 1
       										turnSeed=1;	
       									}
       									else { //if 4 players, set next to player 3
       									turnSeed=3;
       									}
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[0].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							Player2.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[0].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[0].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2 
       					
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[0].attack(targetPiece);
       							Player2.playersTeam.teamPieces[0].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[0].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[0].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[1]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[1].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[1], Player2) && Player2.playersTeam.teamPieces[1].tookAction<1) {
       				if (currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[1].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							Player2.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[1].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[1].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
 
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							Player2.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[1].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[1].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[1].attack(targetPiece);
       							Player2.playersTeam.teamPieces[1].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[1].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[1].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[2]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[2].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[2], Player2) && Player2.playersTeam.teamPieces[2].tookAction<1) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[2].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       						if(gameModeSelected==2)	{
       							attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       						}//end of if 2v2
       						
       						else {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							Player2.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[2].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       						}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       								
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							Player2.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[2].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[2].attack(targetPiece);
       							Player2.playersTeam.teamPieces[2].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[2].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[2].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[3]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[3], Player2) && Player2.playersTeam.teamPieces[3].tookAction<1) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[0] && currentButton.getIcon()!=Player2.PieceImages[4]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[3].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end if 2v2
       							
       							else {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							Player2.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[3].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							Player2.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[3].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[3].attack(targetPiece);
       							Player2.playersTeam.teamPieces[3].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[3].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[3].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			if(tempImg==Player2.PieceImages[4]) {
       				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].range) {
       				if (noOtherActions(Player2.playersTeam.teamPieces[4], Player2) && Player2.playersTeam.teamPieces[4].tookAction<1) {
       				if (currentButton.getIcon()!=Player2.PieceImages[1] && currentButton.getIcon()!=Player2.PieceImages[2] && currentButton.getIcon()!=Player2.PieceImages[3] && currentButton.getIcon()!=Player2.PieceImages[0]) {
       					System.out.println("Player 2's " + Player2.playersTeam.teamPieces[4].name + " attacks!");
       					Piece targetPiece;
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       						
       							else {
       							targetPiece = Player1.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							Player2.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[4].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					if (numPlayers ==4) {
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player3.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player3.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							Player2.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[4].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       						}
       						}
       					}
       					for(int i=0; i<5; i++) {
       						if(currentButton.getIcon()==Player4.PieceImages[i]) {
       							if(gameModeSelected==2) {
       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
       							}//end of if 2v2
       							
       							else {
       							targetPiece = Player4.playersTeam.teamPieces[i];
       							int tempHp = targetPiece.getCurrHp();
       							Player2.playersTeam.teamPieces[4].attack(targetPiece);
       							Player2.playersTeam.teamPieces[4].tookAction++;
       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
       								targetPiece.currHp=0;
       								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
       								removeImage(currentButton, frame3);
       								if(Player2.playersTeam.teamPieces[4].moved==1) {
       								endTurn(numPlayers);
       								if(numPlayers==2){
       								turnSeed=1;	
       								}
       								else {
       									turnSeed=3;
       								}
       								}
       							}
       							else {
       							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
       							if(Player2.playersTeam.teamPieces[4].moved==1) {
       							endTurn(numPlayers);
   								if(numPlayers==2){
   								turnSeed=1;	
   								}
   								else {
   									turnSeed=3;
   								}
       							}
       							}
       							}
       						}
       					}
       					}
       				}
       				else {
       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
       				}
       				}//end of action check
       				else{
       	   				System.out.println("Only one piece may move and act once each turn");
       	   			}
       				}//end of range check
       				else {
       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       				}
       			}
       			}//end of if it is player 2's turn
       			else if(tempPiece.team == 2){
       				System.out.println("It is not Player 2's turn");
       			}
       			 if(numPlayers==4) {
       				if (whosTurn(numPlayers) == 3) {
       				 if(tempImg==Player3.PieceImages[0]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[0].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[0], Player3) && Player3.playersTeam.teamPieces[0].tookAction<1) {
       						if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[0].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of if 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							Player3.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers); //prints out turn ended & next player
               								turnSeed=4; //set next to player 4
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
               							}//end of if 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							Player3.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of if 2v2
               							
               							else {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[0].attack(targetPiece);
               							Player3.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
            			}
       				 if(tempImg==Player3.PieceImages[1]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[1].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[1], Player3) && Player3.playersTeam.teamPieces[1].tookAction<1) {
       						if (currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[1].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of if 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							Player3.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               						
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							Player3.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[1].attack(targetPiece);
               							Player3.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               					}
               						}	
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			}
       				 if(tempImg==Player3.PieceImages[2]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[2].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[2], Player3) && Player3.playersTeam.teamPieces[2].tookAction<1) {
       						if(currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[4]){
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[2].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							Player3.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							Player3.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[2].attack(targetPiece);
               							Player3.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			}
       				 if(tempImg==Player3.PieceImages[3]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[3], Player3) && Player3.playersTeam.teamPieces[3].tookAction<1) {
       						if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[0] && currentButton.getIcon()!=Player3.PieceImages[4]) {
             					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[3].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							Player3.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               					}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							Player3.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[3].attack(targetPiece);
               							Player3.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
             			} 
       				 if(tempImg==Player3.PieceImages[4]) {
       					if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].range) {
       						if (noOtherActions(Player3.playersTeam.teamPieces[4], Player3) && Player3.playersTeam.teamPieces[4].tookAction<1) {
       						if (currentButton.getIcon()!=Player3.PieceImages[1] && currentButton.getIcon()!=Player3.PieceImages[2] && currentButton.getIcon()!=Player3.PieceImages[3] && currentButton.getIcon()!=Player3.PieceImages[0]) {
            					System.out.println("Player 3's " + Player3.playersTeam.teamPieces[4].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               					
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							Player3.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							Player3.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player4.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player4.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player3.playersTeam.teamPieces[4].attack(targetPiece);
               							Player3.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player3.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=4;
               								}
               							}
               							else {
               							System.out.println("Player 4's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player3.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=4;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
       					}//end of action check
       						else{
       			   				System.out.println("Only one piece may move and act once each turn");
       			   			}
       					}//end of range check
       					else {
       						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
       					}
            			}
       					}//end of if it is player 3's turn
       					else if(tempPiece.team == 3){
       						System.out.println("It is not Player 3's turn");
       					}
       					if (whosTurn(numPlayers) == 4) {
             			 if(tempImg==Player4.PieceImages[0]) {
             				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[0].range) {
             				if (noOtherActions(Player4.playersTeam.teamPieces[0], Player4) && Player4.playersTeam.teamPieces[0].tookAction<1) {
             				if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]){
             					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[0].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							Player4.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers); //prints out turn ended & next player
               								turnSeed=1; //sets next to player 1
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							Player4.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               						if(gameModeSelected==2) {
               							attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               						}//end of if 2v2
               							
               						else {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[0].attack(targetPiece);
               							Player4.playersTeam.teamPieces[0].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[0].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[0].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           				}
             			}//end of action check
             				else{
             	   				System.out.println("Only one piece may move and act once each turn");
             	   			}
             			}//end of range check
             			else {
             				System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");	
             			}
             			}
      					 if(tempImg==Player4.PieceImages[1]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[1].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[1], Player4) && Player4.playersTeam.teamPieces[1].tookAction<1) {
      						if(currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
              					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[1].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               						
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							Player4.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               						
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							Player4.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[1].attack(targetPiece);
               							Player4.playersTeam.teamPieces[1].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[1].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[1].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
           				}
           				else {
           					System.out.println("That is an invalid attack, cannot attack your own game pieces");
           					
           				}
      					}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      					}//end of range check
      					else {
      						System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      					}
      					 }
      					 if(tempImg==Player4.PieceImages[2]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[2].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[2], Player4) && Player4.playersTeam.teamPieces[2].tookAction<1) {
      						if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[2].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							Player4.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							Player4.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[2].attack(targetPiece);
               							Player4.playersTeam.teamPieces[2].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[2].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[2].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
      						}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      						}
       					 }
      					 if(tempImg==Player4.PieceImages[3]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[3], Player4) && Player4.playersTeam.teamPieces[3].tookAction<1) {
      						if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[0] && currentButton.getIcon()!=Player4.PieceImages[4]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[3].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);	
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							Player4.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							Player4.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[3].attack(targetPiece);
               							Player4.playersTeam.teamPieces[3].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[3].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[3].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				} 
      						}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      						}
       					 }
      					 if(tempImg==Player4.PieceImages[4]) {
      						if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].range) {
      						if (noOtherActions(Player4.playersTeam.teamPieces[4], Player4) && Player4.playersTeam.teamPieces[4].tookAction<1) {
      						if (currentButton.getIcon()!=Player4.PieceImages[1] && currentButton.getIcon()!=Player4.PieceImages[2] && currentButton.getIcon()!=Player4.PieceImages[3] && currentButton.getIcon()!=Player4.PieceImages[0]) {
               					System.out.println("Player 4's " + Player4.playersTeam.teamPieces[4].name + " attacks!");
               					Piece targetPiece;
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player1.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player1.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							Player4.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player2.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end of 2v2
               							
               							else {
               							targetPiece = Player2.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							Player4.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               					for(int i=0; i<5; i++) {
               						if(currentButton.getIcon()==Player3.PieceImages[i]) {
               							if(gameModeSelected==2) {
               								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
               							}//end if 2v2
               							
               							else {
               							targetPiece = Player3.playersTeam.teamPieces[i];
               							int tempHp = targetPiece.getCurrHp();
               							Player4.playersTeam.teamPieces[4].attack(targetPiece);
               							Player4.playersTeam.teamPieces[4].tookAction++;
               							if(targetPiece.currHp<1) {//don't want negative numbers for hp
               								targetPiece.currHp=0;
               								System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
               								removeImage(currentButton, frame3);
               								if(Player4.playersTeam.teamPieces[4].moved==1) {
               								endTurn(numPlayers);
               								turnSeed=1;
               								}
               							}
               							else {
               							System.out.println("Player 3's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
               							if(Player4.playersTeam.teamPieces[4].moved==1) {
               							endTurn(numPlayers);
           								turnSeed=1;
               							}
               							}
               							}
               						}
               					}
               				}
               				else {
               					System.out.println("That is an invalid attack, cannot attack your own game pieces");
               				}
      						}//end of action check
      						else{
      			   				System.out.println("Only one piece may move and act once each turn");
      			   			}
      						}//end of range check
      						else {
      							System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
      						}
       					 }	
       					}//end of if it is player 4's turn
       					else if(tempPiece.team == 4){
       						System.out.println("It is not Player 4's turn");
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
        	 Piece tempPiece;
        	 
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
        						 tempPiece = findPiece(tempImg);
        						//Image img = ImageIO.read(Game.class.getResource("/images/BlueCircle.png")); //will have to change image src to variable so it can work with any token
      
        			//When a second tile is clicked, it makes sure it is a valid move for the piece selected
        			if (whosTurn(numPlayers) == 1) {
        			if(tempImg==Player1.PieceImages[0])	{//pieces can move 3, 4, 5, or 6 tiles
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        						swapImage(currentButton, tempImg);
        						tempPiece.moved++;
        						if(tempPiece.tookAction==1) {
        							endTurn(numPlayers); //prints out turn ended & next player
    								turnSeed=2; //sets next to player 2
        						}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[1]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
								turnSeed=2;
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[2]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
        						turnSeed=2;
        					}
        				}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}			 
        			if(tempImg==Player1.PieceImages[3]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
        						turnSeed=2;
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}		
        			if(tempImg==Player1.PieceImages[4]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
							turnSeed=2;
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			}//end of if it is player 1's turn
        			else if(tempPiece.team == 1){
        				System.out.println("It is not Player 1's turn");
        			}
        			if (whosTurn(numPlayers) == 2) {
        			if(tempImg==Player2.PieceImages[0]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[0].move) {
        					if (noOtherActions(tempPiece, Player2) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers); //prints out turn ended & next player
        					if(numPlayers==2){ //if 2 players, set next to player 1
        						turnSeed=1;
        					}
        					else { //if 4 players, set next to player 3
        						turnSeed=3;
        					}
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[1]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[1].move) {
        					if (noOtherActions(tempPiece, Player2) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
        					if(numPlayers==2){
        						turnSeed=1;
        					}
        					else {
        						turnSeed=3;
        					}
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[2]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[2].move) {
        					if (noOtherActions(tempPiece, Player2) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
        					if(numPlayers==2){
        						turnSeed=1;
        					}
        					else {
        						turnSeed=3;
        					}
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[3]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[3].move) {
        					if (noOtherActions(tempPiece, Player2) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
        					if(numPlayers==2){
        						turnSeed=1;
        					}
        					else {
        						turnSeed=3;
        					}
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player2.PieceImages[4]) {
        				if (destinationYCoord <= pieceYCoord+Player2.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player2.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player2.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player2.playersTeam.teamPieces[4].move) {
        					if (noOtherActions(tempPiece, Player2) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
        					if(numPlayers==2){
        						turnSeed=1;
        					}
        					else {
        						turnSeed=3;
        					}
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			}//end of if it is player 2's turn
        			else if(tempPiece.team==2){
        				System.out.println("It is not Player 2's turn");
        			}
        			 if(numPlayers==4) {
        				 if (whosTurn(numPlayers) == 3) {
        				 if(tempImg==Player3.PieceImages[0]) {
             				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[0].move) {
             					if (noOtherActions(tempPiece, Player3) && tempPiece.moved<1) {
             					swapImage(currentButton, tempImg);
             					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers); //prints out turn ended & next player
            					turnSeed=4; //set next to player 4
            					}
             					}//end of action check
             					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
             			}
        				 if(tempImg==Player3.PieceImages[1]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[1].move) {
              					if (noOtherActions(tempPiece, Player3) && tempPiece.moved<1) {
              					swapImage(currentButton, tempImg);
              					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers);
            					turnSeed=4;
            					}
              					}//end of action check
              					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
        				 if(tempImg==Player3.PieceImages[2]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[2].move) {
              					if (noOtherActions(tempPiece, Player3) && tempPiece.moved<1) {
              					swapImage(currentButton, tempImg);
              					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers);
            					turnSeed=4;
            					}
              					}//end of action check
              					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
        				 if(tempImg==Player3.PieceImages[3]) {
              				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[3].move) {
              					if (noOtherActions(tempPiece, Player3) && tempPiece.moved<1) {
              					swapImage(currentButton, tempImg);
              					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers);
            					turnSeed=4;
            					}
              					}//end of action check
              					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			} if(tempImg==Player3.PieceImages[4]) {
             				if (destinationYCoord <= pieceYCoord+Player3.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player3.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player3.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player3.playersTeam.teamPieces[4].move) {
             					if (noOtherActions(tempPiece, Player3) && tempPiece.moved<1) {
             					swapImage(currentButton, tempImg);
             					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers);
            					turnSeed=4;
            					}
             					}//end of action check
             					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
             			}
        				 }//end of if it is player 3's turn
        				 else if (tempPiece.team==3){
        					 System.out.println("It is not Player 3's turn");
        				 }
        				 if (whosTurn(numPlayers) == 4) {
              			 if(tempImg==Player4.PieceImages[0]) {
              				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[0].move) {
              					if (noOtherActions(tempPiece, Player4) && tempPiece.moved<1) {
              					swapImage(currentButton, tempImg);
              					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers); //prints out turn ended & next player
            					turnSeed=1; //sets next to player 1
            					}
              					}//end of action check
              					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
              			}
       					 if(tempImg==Player4.PieceImages[1]) {
               				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[1].move) {
               					if (noOtherActions(tempPiece, Player4) && tempPiece.moved<1) {
               					swapImage(currentButton, tempImg);
               					tempPiece.moved++;
            					if(tempPiece.tookAction==1) {
            					endTurn(numPlayers);
            					turnSeed=1;
            					}
               					}//end of action check
               					else{
            		   				System.out.println("Only one piece may move and act once each turn");
            		   			}
            				}
            				else {
            					System.out.println("That is an invalid move, it exceeds that piece's movement range");
            					//System.out.println(pieceYCoord + "" + pieceXCoord);
            				}
       					 }
       					 if(tempImg==Player4.PieceImages[2]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[2].move) {
                					if (noOtherActions(tempPiece, Player4) && tempPiece.moved<1) {
                					swapImage(currentButton, tempImg);
                					tempPiece.moved++;
                					if(tempPiece.tookAction==1) {
                					endTurn(numPlayers);
                					turnSeed=1;
                					}
                					}//end of action check
                					else{
                		   				System.out.println("Only one piece may move and act once each turn");
                		   			}
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				}
        					 }
       					 if(tempImg==Player4.PieceImages[3]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[3].move) {
                					if (noOtherActions(tempPiece, Player4) && tempPiece.moved<1) {
                					swapImage(currentButton, tempImg);
                					tempPiece.moved++;
                					if(tempPiece.tookAction==1) {
                					endTurn(numPlayers);
                					turnSeed=1;
                					}
                					}//end of action check
                					else{
                		   				System.out.println("Only one piece may move and act once each turn");
                		   			}
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				} 
        					 }
       					 if(tempImg==Player4.PieceImages[4]) {
                				if (destinationYCoord <= pieceYCoord+Player4.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player4.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player4.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player4.playersTeam.teamPieces[4].move) {
                					if (noOtherActions(tempPiece, Player4) && tempPiece.moved<1) {
                					swapImage(currentButton, tempImg);
                					tempPiece.moved++;
                					if(tempPiece.tookAction==1) {
                					endTurn(numPlayers);
                					turnSeed=1;
                					}
                					}//end of action check
                					else{
                		   				System.out.println("Only one piece may move and act once each turn");
                		   			}
                				}
                				else {
                					System.out.println("That is an invalid move, it exceeds that piece's movement range");
                					//System.out.println(pieceYCoord + "" + pieceXCoord);
                				}
        					 }
        				 }//end of if it is player 4's turn
        				 else if(tempPiece.team==4){
        					 System.out.println("It is not Player 4's turn");
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
    			int currTurn=whosTurn(numPlayers);
    			removeAllImages(numPlayers, tile, frame3); 
    			endTurn(numPlayers); //prints out turn ended & next player
    			
    			if(numPlayers==2) {
        			if(currTurn==1){ //have to update variable so next player can go
        				turnSeed=2;
        			}
        			else {
        				turnSeed=1;
        			}
        		}
        	else {
        		if(currTurn==1){
            		turnSeed=2;
            	}
            	else if(currTurn==2){
            		turnSeed=3;
            	}
           
            	else if(currTurn==3) {
            		turnSeed=4;
            
            	}
            	else {
            		turnSeed=1;
            		
            	}		
    			}
    			
    		}
        }
    			
        class keyAction implements ActionListener{ //placeholder for key window 
        	public void actionPerformed (ActionEvent e) {
        		JOptionPane.showMessageDialog(frame3,
        			    "Player 1 is blue \n"
        			    		+ "Player 2 is red \n"
        			    		+ "Player 3 is green \n"
        			    		+ "Player 4 is yellow \n"
        			    		+ "A Warrior's token is a circle. Range:1, Move:3\n"
        			    		+ "A Ranger's token is a square. Range:4, Move:5\n"
        			    		+ "A Rogue's token is a triangle. Range:1, Move:6\n"
        			    		+ "A healer's token is a star. Range:3, Move:4\n"
        			    		+ "A Damage Mage's token is a pentagon. Range:3, Move:4\n",    
        			    "Key",
        			    JOptionPane.PLAIN_MESSAGE);
        	}
        }
        class endturnAction implements ActionListener{ //placeholder for endturn
        	public void actionPerformed (ActionEvent e) {
        		endTurn(numPlayers); //prints out turn ended & next player
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
	
	public static void createGui4(JFrame frame2, JFrame frame3,int numPlayers) {//if playing against computer player
		frame2.dispose();
        frame3.setLayout(new GridLayout(0, 10));
        //JButton tile[] = new JButton[100];
        JButton tile[][] = new JButton[10][10]; //tile is now a matrix
        turnSeed = genTurnSeed(numPlayers);
		System.out.println("Player " + turnSeed + " will be going first");
 
		 class healGamePiece implements ActionListener{
	        	JButton currentButton=null;
	        	Piece tempPiece;
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
	   						 tempPiece = findPiece(tempImg);//find the piece that has been clicked
	   			if (whosTurn(numPlayers) == 1) {//if it is player 1's turn
	   			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){ //check only locations 3 &4 bc teams sets healers to those locations & check to make sure piece is healer
	   				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
	   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing itself");//message to user
	   				Piece targetPiece;//alias for piece being healed
	   				targetPiece = Player1.playersTeam.teamPieces[3];//set target piece to the healer
	   				int tempHp = targetPiece.getCurrHp();//save hp it currently has
	   					
	   				if(tempHp != targetPiece.getMaxHp()){
	   				Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal piece
	   				Player1.playersTeam.teamPieces[3].tookAction++;
	   					if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the maximum hp
	   						targetPiece.currHp=targetPiece.getMaxHp();
	   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	   						if(Player1.playersTeam.teamPieces[3].moved==1) {
	   						endTurn(numPlayers); //prints out turn ended & next player
	   					 if(whosTurn(numPlayers)==2) {
							 Player2.findPieceLocs(tile);
							 Player2.findMove(tile, Player1);
							 Player2.makeMove(tile, turnSeed, frame3);
							 endTurn(numPlayers);
						 }
	   						}
	   					}
	   					else {
	   						System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
	   						if(Player1.playersTeam.teamPieces[3].moved==1) {
	   						endTurn(numPlayers);
	   					 if(whosTurn(numPlayers)==2) {
							 Player2.findPieceLocs(tile);
							 Player2.findMove(tile, Player1);
							 Player2.makeMove(tile, turnSeed, frame3);
							 endTurn(numPlayers);
						 }
	   						}
	   					}
	   				}
	   				else {
	   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
	   					}
	   			}//end of action check
	   			else{
	   				System.out.println("Only one piece may move and act once each turn");
	   			}
	   			}
	   			
	   			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
	   				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
	   				System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " is healing itself");
	   					Piece targetPiece;
	   					targetPiece = Player1.playersTeam.teamPieces[4];
	   					int tempHp = targetPiece.getCurrHp();
	   					
	   					if(tempHp != targetPiece.getMaxHp()) {
	   						Player1.playersTeam.teamPieces[4].heal(targetPiece);
	   						Player1.playersTeam.teamPieces[4].tookAction++;
	   						if(targetPiece.currHp> targetPiece.getMaxHp()) {
	   							targetPiece.currHp=targetPiece.getMaxHp();
	   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	   							if(Player1.playersTeam.teamPieces[4].moved==1) {
	   							endTurn(numPlayers);
	   						 if(whosTurn(numPlayers)==2) {
								 Player2.findPieceLocs(tile);
								 Player2.findMove(tile, Player1);
								 Player2.makeMove(tile, turnSeed, frame3);
								 endTurn(numPlayers);
							 }
	   							}
	   						}
	   							
	   						else {
	   							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	   							if(Player1.playersTeam.teamPieces[4].moved==1) {
	   							endTurn(numPlayers);
	   						 if(whosTurn(numPlayers)==2) {
								 Player2.findPieceLocs(tile);
								 Player2.findMove(tile, Player1);
								 Player2.makeMove(tile, turnSeed, frame3);
								 endTurn(numPlayers);
							 }
	   							}
	   						}
	   					}
	   				else {
	   					System.out.println("Player 1's " + targetPiece.name + " is already at full health");
	   					}
	   			}//end of action check
	   			else{
	   	   			System.out.println("Only one piece may move and act once each turn");
	   	   		}
	   			}	
	   			}//end of if it is player 1's turn
	   			else if(tempPiece.team == 1){//if player 1's piece has been clicked and it is not their turn
	   				System.out.println("It is not Player 1's turn");//send error message
	   			}
	   			
	   			else if(tempPiece.team == 2){
	   				System.out.println("It is not Player 2's turn");
	   			}
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
	       						 tempPiece = findPiece(tempImg);
	       			
	       			if (whosTurn(numPlayers) == 1) {
	       			if(tempImg==Player1.PieceImages[3] && Player1.playersTeam.teamPieces[3].name =="Healer"){//checks 3& 4 bc teams only set those as healers & make sure it is a healer
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
	       					//This checks for the range of the healing piece^
	       				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
	       				if (currentButton.getIcon()==Player1.PieceImages[0] || currentButton.getIcon()==Player1.PieceImages[1] || currentButton.getIcon()==Player1.PieceImages[2] || currentButton.getIcon()==Player1.PieceImages[4]){
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " is healing");//message to user
	       					Piece targetPiece;//alias for piece being healed
	       					for(int i=0; i<5; i++) {//find piece being healed, searches current team for a match
	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {//if found here
	       							targetPiece = Player1.playersTeam.teamPieces[i];//set target piece to alias the found piece
	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
	       							if(tempHp != targetPiece.getMaxHp()){
	       							Player1.playersTeam.teamPieces[3].heal(targetPiece);//heal
	       							Player1.playersTeam.teamPieces[3].tookAction++;
	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {//can't have hp over the mamximum hp of a piece 
	       								targetPiece.currHp=targetPiece.getMaxHp();
	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
	       								endTurn(numPlayers); //prints out turn ended & next player
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
	       							endTurn(numPlayers);
	       						 if(whosTurn(numPlayers)==2) {
									 Player2.findPieceLocs(tile);
									 Player2.findMove(tile, Player1);
									 Player2.makeMove(tile, turnSeed, frame3);
									 endTurn(numPlayers);
								 }
	       							}
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
	       			}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       			}//end of range check
	       			else {
	       				System.out.println("That is an invalid heal, cannot a piece further than you healer's range");
	       			}
	       			}
	       			
	       			if(tempImg==Player1.PieceImages[4] && Player1.playersTeam.teamPieces[4].name == "Healer"){
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
	       				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
	       				if(currentButton.getIcon()==Player1.PieceImages[0] || currentButton.getIcon()==Player1.PieceImages[1] || currentButton.getIcon()==Player1.PieceImages[2] || currentButton.getIcon()==Player1.PieceImages[3]){
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " is healing");
	       					Piece targetPiece;
	       					for(int i=0; i<5; i++) {
	       						if(currentButton.getIcon()==Player1.PieceImages[i]) {
	       							targetPiece = Player1.playersTeam.teamPieces[i];
	       							int tempHp = targetPiece.getCurrHp();
	       							
	       							if(tempHp != targetPiece.getMaxHp()) {
	       							Player1.playersTeam.teamPieces[4].heal(targetPiece);
	       							Player1.playersTeam.teamPieces[4].tookAction++;
	       							if(targetPiece.currHp> targetPiece.getMaxHp()) {
	       								targetPiece.currHp=targetPiece.getMaxHp();
	       								System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
	       								endTurn(numPlayers);
	           						 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 1's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
	       							endTurn(numPlayers);
	       						 if(whosTurn(numPlayers)==2) {
									 Player2.findPieceLocs(tile);
									 Player2.findMove(tile, Player1);
									 Player2.makeMove(tile, turnSeed, frame3);
									 endTurn(numPlayers);
								 }
	       							}
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
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       				}//end of range check
	       				else {
	       					System.out.println("That is an invalid heal, cannot heal piece further than healer's range");
	       				}
	       			}			 
	       			}//end of if it is player 1's turn
	       			else if(tempPiece.team == 1){
	       				System.out.println("It is not Player 1's turn");
	       			}
	       			else if(tempPiece.team == 2){
	       				System.out.println("It is not Player 2's turn");
	       			}
	       					currentButton=null; //resets the ActionListener
	       					}
	       			 }
	       		 }
	       				lastButtonPressed=currentButton;
	        	}
	        }
		 
		 class attackGamePiece implements ActionListener{
	        	JButton currentButton=null;
	        	Piece tempPiece;
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
	       						 tempPiece = findPiece(tempImg);
	       			if (whosTurn(numPlayers) == 1) {
	       			if(tempImg==Player1.PieceImages[0])	{
	       			//check to make sure piece to be attacked isn't the player's pieces 
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].range) {
	       					//This checks for the range of the attacking piece^
	       				if (noOtherActions(Player1.playersTeam.teamPieces[0], Player1) && Player1.playersTeam.teamPieces[0].tookAction<1) {
	       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[0].name + " attacks!");//message to user
	       					Piece targetPiece;//alias for piece being attacked
	       					for(int i=0; i<5; i++) {//find piece being attacked, searches all 3 opposing teams for a match
	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {//if found here
	       							targetPiece = Player2.playersTeam.teamPieces[i];//set target piece to alias the found piece
	       							int tempHp = targetPiece.getCurrHp();//save hp it currently has
	       							Player1.playersTeam.teamPieces[0].attack(targetPiece);//make attack
	       							Player1.playersTeam.teamPieces[0].tookAction++;
	       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
	       								targetPiece.currHp=0;
	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								removeImage(currentButton, frame3);//remove image now needs the frame passed to it as well
	       								if(Player1.playersTeam.teamPieces[0].moved==1) {
	       									endTurn(numPlayers); //prints out turn ended & next player
	           							 if(whosTurn(numPlayers)==2) {
	        								 Player2.findPieceLocs(tile);
	        								 Player2.findMove(tile, Player1);
	        								 Player2.makeMove(tile, turnSeed, frame3);
	        								 endTurn(numPlayers);
	        							 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");//print remaining hp
	       							if(Player1.playersTeam.teamPieces[0].moved==1) {
	       								endTurn(numPlayers);
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       							}
	       							}
	       							
	       						}
	       					}
	       				}
	       				 else {
	       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
	       				}
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       			}//end of range check
	       			else {
	       				System.out.println("That is an invalid attack, cannot a piece further than your piece's range");
	       			}
	       			}
	       			if(tempImg==Player1.PieceImages[1]){
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].range) {
	       				if (noOtherActions(Player1.playersTeam.teamPieces[1], Player1) && Player1.playersTeam.teamPieces[1].tookAction<1) {
	       				if (currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[1].name + " attacks!");
	       					Piece targetPiece;
	       					for(int i=0; i<5; i++) {
	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
	       							targetPiece = Player2.playersTeam.teamPieces[i];
	       							int tempHp = targetPiece.getCurrHp();
	       							Player1.playersTeam.teamPieces[1].attack(targetPiece);
	       							Player1.playersTeam.teamPieces[1].tookAction++;
	       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
	       								targetPiece.currHp=0;
	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								removeImage(currentButton, frame3);
	       								if(Player1.playersTeam.teamPieces[1].moved==1) {
	       									endTurn(numPlayers);
	       								 if(whosTurn(numPlayers)==2) {
	       									 Player2.findPieceLocs(tile);
	       									 Player2.findMove(tile, Player1);
	       									 Player2.makeMove(tile, turnSeed, frame3);
	       									endTurn(numPlayers);
	       								 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	       							if(Player1.playersTeam.teamPieces[1].moved==1) {
	       								endTurn(numPlayers);
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       							}
	       							}
	       							
	       						}
	       					}
	       				}
	       				else {
	       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
	       				}
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       				}//end of range check
	       				else {
	       					System.out.println("that is an invalid attack, cannot attack piece further than your piece's range");
	       				}
	       			}
	       			if(tempImg==Player1.PieceImages[2]){
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].range) {
	       				if (noOtherActions(Player1.playersTeam.teamPieces[2], Player1) && Player1.playersTeam.teamPieces[2].tookAction<1) {
	       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[4]){
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[2].name + " attacks!");
	       					Piece targetPiece;
	       					for(int i=0; i<5; i++) {
	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
	       							targetPiece = Player2.playersTeam.teamPieces[i];
	       							int tempHp = targetPiece.getCurrHp();
	       							Player1.playersTeam.teamPieces[2].attack(targetPiece);
	       							Player1.playersTeam.teamPieces[2].tookAction++;
	       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
	       								targetPiece.currHp=0;
	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								removeImage(currentButton, frame3);
	       								if(Player1.playersTeam.teamPieces[2].moved==1) {
	       									endTurn(numPlayers);
	       								 if(whosTurn(numPlayers)==2) {
	       									 Player2.findPieceLocs(tile);
	       									 Player2.findMove(tile, Player1);
	       									 Player2.makeMove(tile, turnSeed, frame3);
	       									endTurn(numPlayers);
	       								 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	       							if(Player1.playersTeam.teamPieces[2].moved==1) {
	       								endTurn(numPlayers);
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       							}
	       							}
	       						}
	       					}
	       				}
	       				else{
	       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
	       				}
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       				}//end of range check
	       				else {
	       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
	       				}
	       			}			 
	       			if(tempImg==Player1.PieceImages[3]) {
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].range) {
	       				if (noOtherActions(Player1.playersTeam.teamPieces[3], Player1) && Player1.playersTeam.teamPieces[3].tookAction<1) {
	       				if(currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[0] && currentButton.getIcon()!=Player1.PieceImages[4]) {
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[3].name + " attacks!");
	       					Piece targetPiece;
	       					for(int i=0; i<5; i++) {
	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
	       							if(gameModeSelected==2) {
	       								attack2v2(tempImg, numPlayers, currentButton, frame3, i);
	       							}//end of if 2v2
	       							else {
	       							targetPiece = Player2.playersTeam.teamPieces[i];
	       							int tempHp = targetPiece.getCurrHp();
	       							Player1.playersTeam.teamPieces[3].attack(targetPiece);
	       							Player1.playersTeam.teamPieces[3].tookAction++;
	       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
	       								targetPiece.currHp=0;
	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								removeImage(currentButton, frame3);
	       								if(Player1.playersTeam.teamPieces[3].moved==1) {
	       									endTurn(numPlayers);
	       								 if(whosTurn(numPlayers)==2) {
	       									 Player2.findPieceLocs(tile);
	       									 Player2.findMove(tile, Player1);
	       									 Player2.makeMove(tile, turnSeed, frame3);
	       								 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	       							if(Player1.playersTeam.teamPieces[3].moved==1) {
	       								endTurn(numPlayers);
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       							}
	       							}
	       						}
	       						}
	       					}
	       					
	       				}
	       				else{
	       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
	       				}
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       				}//end of range check
	       				else {
	       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
	       				}
	       			}	
	       			
	       			if(tempImg==Player1.PieceImages[4]) {
	       				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].range && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].range && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].range && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].range) {
	       				if (noOtherActions(Player1.playersTeam.teamPieces[4], Player1) && Player1.playersTeam.teamPieces[4].tookAction<1) {
	       				if (currentButton.getIcon()!=Player1.PieceImages[1] && currentButton.getIcon()!=Player1.PieceImages[2] && currentButton.getIcon()!=Player1.PieceImages[3] && currentButton.getIcon()!=Player1.PieceImages[0]) {
	       					System.out.println("Player 1's " + Player1.playersTeam.teamPieces[4].name + " attacks!");
	       					Piece targetPiece;
	       					for(int i=0; i<5; i++) {
	       						if(currentButton.getIcon()==Player2.PieceImages[i]) {
	       							targetPiece = Player2.playersTeam.teamPieces[i];
	       							int tempHp = targetPiece.getCurrHp();
	       							Player1.playersTeam.teamPieces[4].attack(targetPiece);
	       							Player1.playersTeam.teamPieces[4].tookAction++;
	       							if(targetPiece.currHp<1) {//don't want negative numbers for hp
	       								targetPiece.currHp=0;
	       								System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");	
	       								removeImage(currentButton, frame3);
	       								if(Player1.playersTeam.teamPieces[4].moved==1) {
	       									endTurn(numPlayers);
	       								 if(whosTurn(numPlayers)==2) {
	       									 Player2.findPieceLocs(tile);
	       									 Player2.findMove(tile, Player1);
	       									 Player2.makeMove(tile, turnSeed, frame3);
	       								 }
	       								}
	       							}
	       							else {
	       							System.out.println("Player 2's " + targetPiece.name + " went from " + tempHp + " hit points to " +targetPiece.getCurrHp() + " hit points!");
	       							if(Player1.playersTeam.teamPieces[4].moved==1) {
	       								endTurn(numPlayers);
	       							 if(whosTurn(numPlayers)==2) {
	    								 Player2.findPieceLocs(tile);
	    								 Player2.findMove(tile, Player1);
	    								 Player2.makeMove(tile, turnSeed, frame3);
	    								 endTurn(numPlayers);
	    							 }
	       							}
	       							}
	       						}
	       					}
	       				}
	       				else {
	       					System.out.println("That is an invalid attack, cannot attack your own game pieces");
	       				}
	       				}//end of action check
	       				else{
	       	   				System.out.println("Only one piece may move and act once each turn");
	       	   			}
	       				}//end of range check
	       				else {
	       					System.out.println("That is an invalid attack, cannot attack piece further than your piece's range");
	       				}
	       			}
	       			}//end of if it is player 1's turn
	       			else if(tempPiece.team == 1){
	       				System.out.println("It is not Player 1's turn");
	       			}
	       			else if(tempPiece.team == 2){
	       				System.out.println("It is not Player 2's turn");
	       			}
	       			 
	       					currentButton=null; //resets the ActionListener
	       					}
	       			 }
	       		 }
	       				lastButtonPressed=currentButton;
	        	}
	        } 
		 
		 class tileClicked implements ActionListener{
        	 JButton currentButton=null;
        	 Piece tempPiece;
        	 
        	 public void actionPerformed (ActionEvent e){
        		 JButton currentButton= (JButton)e.getSource(); //save which button pressed in variable
        		 
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
        						 Icon tempImg = lastButtonPressed.getIcon(); //holds the image of the last button pressed
        						 tempPiece = findPiece(tempImg);
        						//Image img = ImageIO.read(Game.class.getResource("/images/BlueCircle.png")); //will have to change image src to variable so it can work with any token
      
        			//When a second tile is clicked, it makes sure it is a valid move for the piece selected
        			if (whosTurn(numPlayers) == 1) {
        			if(tempImg==Player1.PieceImages[0])	{//pieces can move 3, 4, 5, or 6 tiles
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[0].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[0].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[0].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[0].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        						swapImage(currentButton, tempImg);
        						tempPiece.moved++;
        						if(tempPiece.tookAction==1) {
        							endTurn(numPlayers); //prints out turn ended & next player
    								 if(whosTurn(numPlayers)==2) {
    									 Player2.findPieceLocs(tile);
    									 Player2.findMove(tile, Player1);
    									 Player2.makeMove(tile, turnSeed, frame3);
    									 endTurn(numPlayers);
    								 }
        						}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[1]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[1].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[1].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[1].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[1].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
								 if(whosTurn(numPlayers)==2) {
									 Player2.findPieceLocs(tile);
									 Player2.findMove(tile, Player1);
									 Player2.makeMove(tile, turnSeed, frame3);
									 endTurn(numPlayers);
								 }
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			if(tempImg==Player1.PieceImages[2]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[2].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[2].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[2].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[2].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
        						 if(whosTurn(numPlayers)==2) {
    								 Player2.findPieceLocs(tile);
    								 Player2.findMove(tile, Player1);
    								 Player2.makeMove(tile, turnSeed, frame3);
    								 endTurn(numPlayers);
    							 }
        					}
        				}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}			 
        			if(tempImg==Player1.PieceImages[3]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[3].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[3].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[3].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[3].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        						endTurn(numPlayers);
        						 if(whosTurn(numPlayers)==2) {
    								 Player2.findPieceLocs(tile);
    								 Player2.findMove(tile, Player1);
    								 Player2.makeMove(tile, turnSeed, frame3);
    								 endTurn(numPlayers);
    							 }
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}		
        			if(tempImg==Player1.PieceImages[4]) {
        				if (destinationYCoord <= pieceYCoord+Player1.playersTeam.teamPieces[4].move && destinationYCoord >= pieceYCoord-Player1.playersTeam.teamPieces[4].move && destinationXCoord <= pieceXCoord+Player1.playersTeam.teamPieces[4].move && destinationXCoord >= pieceXCoord-Player1.playersTeam.teamPieces[4].move) {
        					if (noOtherActions(tempPiece, Player1) && tempPiece.moved<1) {
        					swapImage(currentButton, tempImg);
        					tempPiece.moved++;
        					if(tempPiece.tookAction==1) {
        					endTurn(numPlayers);
							 if(whosTurn(numPlayers)==2) {
								 Player2.findPieceLocs(tile);
								 Player2.findMove(tile, Player1);
								 Player2.makeMove(tile, turnSeed, frame3);
								 endTurn(numPlayers);
						
							 }
							 
        					}
        					}//end of action check
        					else{
        		   				System.out.println("Only one piece may move and act once each turn");
        		   			}
        				}
        				else {
        					System.out.println("That is an invalid move, it exceeds that piece's movement range");
        					//System.out.println(pieceYCoord + "" + pieceXCoord);
        				}
        			}
        			}//end of if it is player 1's turn
        			else if(tempPiece.team == 1){
        				System.out.println("It is not Player 1's turn");
        			}
        
        			else if(tempPiece.team==2){
        				System.out.println("It is not Player 2's turn");
        			}	
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
	    			int currTurn=whosTurn(numPlayers);
	    			removeAllImages(numPlayers, tile, frame3); 
	    			endTurn(numPlayers); //prints out turn ended & next player
	        			if(currTurn==1){ //have to update variable so next player can go
	        				turnSeed=2;
	        			}
	        			else {
	        				turnSeed=1;
	        			}
	    		}
	        }
	    			
	        class keyAction implements ActionListener{ //placeholder for key window 
	        	public void actionPerformed (ActionEvent e) {
	        		JOptionPane.showMessageDialog(frame3,
	        			    "Player 1 is blue \n"
	        			    		+ "Player 2 is red \n"
	        			    		+ "Player 3 is green \n"
	        			    		+ "Player 4 is yellow \n"
	        			    		+ "A Warrior's token is a circle. Range:1, Move:3\n"
	        			    		+ "A Ranger's token is a square. Range:4, Move:5\n"
	        			    		+ "A Rogue's token is a triangle. Range:1, Move:6\n"
	        			    		+ "A healer's token is a star. Range:3, Move:4\n"
	        			    		+ "A Damage Mage's token is a pentagon. Range:3, Move:4\n",    
	        			    "Key",
	        			    JOptionPane.PLAIN_MESSAGE);
	        	}
	        }
	        class endturnAction implements ActionListener{ //placeholder for endturn
	        	public void actionPerformed (ActionEvent e) {
	        		endTurn(numPlayers); //prints out turn ended & next player
	        		 if(whosTurn(numPlayers)==2) {
	        			 Player2.findPieceLocs(tile);
	        			 Player2.findMove(tile, Player1);
	        			 Player2.makeMove(tile, turnSeed, frame3);
	        			 endTurn(numPlayers);
	        		 }
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
		 
	        if(whosTurn(numPlayers)==2) {
				 Player2.findPieceLocs(tile);
				 Player2.findMove(tile, Player1);
				 Player2.makeMove(tile, turnSeed, frame3);
				 endTurn(numPlayers);
			 }
	        
	        
	}//end of vs complayer GUI	
	
    public static void main(String[] args){
    	JFrame frame1= new JFrame();
    	JFrame frame2= new JFrame();
    	JFrame frame3= new JFrame();
    	createGui1(frame1, frame2, frame3);
    	//createGui3(frame2, frame3, 4);
    }  
  }

        
