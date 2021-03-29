import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Player {
int playerNum; //for player turn order
int teamChosen; //keep track of what team was chosen so can make the proper pieces
Team playersTeam; //had to make playersTeam non static, otherwise every players team was stored as the first players team
ImageIcon[] PieceImages = new ImageIcon[5]; //this array keeps track of the icons of each piece in each team
int piecesLeft = 5;

public Player(int playerNum, int teamChosen){ //constructor
	this.playerNum=playerNum;
	this.teamChosen=teamChosen;
}
public Player(int playerNum) {
	this.playerNum=playerNum;
}

public int getPlayerNum(){
	return playerNum;
}

public int getTeamChosen() {
	return teamChosen;
}

public void setTeamChosen(int teamChosen) {
	this.teamChosen=teamChosen;
}


//takes button, color, and locations of where pieces should be generated to create them for the player
//will need to take teamChosen as a parameter so can make those specific pieces
public void createGamePieces(JButton tile[][], String color, int loc1, int loc2, int loc3, int loc4, int loc5) {
	int tempLoc1;//these store the x coordinate based on the location passed
	int tempLoc2;
	int tempLoc3;
	int tempLoc4;
	int tempLoc5;
	
	if(teamChosen == 1) {
		playersTeam = new Team1(this.playerNum);//each team needs the number of the player it belongs to
	}
	else if(teamChosen == 2) {
		playersTeam = new Team2(this.playerNum);
	}
	else if(teamChosen == 3) {
		playersTeam = new Team3(this.playerNum);
	}
	else if(teamChosen == 4) {
		playersTeam = new Team4(this.playerNum);
	}
	else {
		System.out.print("enter valid team number");
	}
	
//	try { //try catch was not longer needed by doing it like this
		String imgSrc= "images/" + color + playersTeam.teamPieces[0].token + ".png";
		String imgSrc2= "images/" + color + playersTeam.teamPieces[1].token + ".png";
		String imgSrc3= "images/" + color + playersTeam.teamPieces[2].token + ".png";
		String imgSrc4= "images/" + color + playersTeam.teamPieces[3].token + ".png";
		String imgSrc5= "images/" + color + playersTeam.teamPieces[4].token + ".png";
		//by declaring these images initally as icons, there is not type mismatch to cause problems
		tempLoc1 = loc1 % 10; //isolate the x coordinates
		tempLoc2 = loc2 % 10;
		tempLoc3 = loc3 % 10;
		tempLoc4 = loc4 % 10;
		tempLoc5 = loc5 % 10;
		loc1 = (loc1 - tempLoc1) / 10; //isolate the y coordinates
		loc2 = (loc2 - tempLoc2) / 10;
		loc3 = (loc3 - tempLoc3) / 10;
		loc4 = (loc4 - tempLoc4) / 10;
		loc5 = (loc5 - tempLoc5) / 10;
		ImageIcon Piece1Img = new ImageIcon(Game.class.getResource(imgSrc)); 
		this.PieceImages[0] = Piece1Img;
		//now locations are determined with two coordinates rather than 1 location
        tile[loc1][tempLoc1].setIcon(Piece1Img);
        ImageIcon Piece2Img = new ImageIcon(Game.class.getResource(imgSrc2));
        this.PieceImages[1] = Piece2Img;
        tile[loc2][tempLoc2].setIcon(Piece2Img);
        ImageIcon Piece3Img = new ImageIcon(Game.class.getResource(imgSrc3));
        tile[loc3][tempLoc3].setIcon(Piece3Img);
        this.PieceImages[2] = Piece3Img;
        ImageIcon Piece4Img = new ImageIcon(Game.class.getResource(imgSrc4));
        tile[loc4][tempLoc4].setIcon(Piece4Img);
        this.PieceImages[3] = Piece4Img;
        ImageIcon Piece5Img = new ImageIcon(Game.class.getResource(imgSrc5));
        tile[loc5][tempLoc5].setIcon(Piece5Img);
        this.PieceImages[4] = Piece5Img;
//}catch (IOException ex) {}
}
}

