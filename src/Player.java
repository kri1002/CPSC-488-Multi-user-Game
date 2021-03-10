import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Player {
int playerNum; //for player turn order
int teamChosen; //keep track of what team was chosen so can make the proper pieces
static Team playersTeam;


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
public void createGamePieces(JButton tile[], String color, int loc1, int loc2, int loc3, int loc4, int loc5) {
	
	if(teamChosen == 1) {
		playersTeam = new Team1();
	}
	else if(teamChosen == 2) {
		playersTeam = new Team2();
	}
	else if(teamChosen == 3) {
		playersTeam = new Team3();
	}
	else if(teamChosen == 4) {
		playersTeam = new Team4();
	}
	else {
		System.out.print("enter valid team number");
	}
	
	try {
		String imgSrc= "images/" + color + playersTeam.teamPieces[0].token + ".png";
		String imgSrc2= "images/" + color + playersTeam.teamPieces[1].token + ".png";
		String imgSrc3= "images/" + color + playersTeam.teamPieces[2].token + ".png";
		String imgSrc4= "images/" + color + playersTeam.teamPieces[3].token + ".png";
		String imgSrc5= "images/" + color + playersTeam.teamPieces[4].token + ".png";
		
		Image CircleImg = ImageIO.read(Game.class.getResource(imgSrc)); 
        tile[loc1].setIcon(new ImageIcon(CircleImg));
        Image TriangleImg = ImageIO.read(Game.class.getResource(imgSrc2));
        tile[loc2].setIcon(new ImageIcon(TriangleImg));
        Image StarImg = ImageIO.read(Game.class.getResource(imgSrc3));
        tile[loc3].setIcon(new ImageIcon(StarImg));
        Image PentagonImg = ImageIO.read(Game.class.getResource(imgSrc4));
        tile[loc4].setIcon(new ImageIcon(PentagonImg));
        Image SquareImg = ImageIO.read(Game.class.getResource(imgSrc5));
        tile[loc5].setIcon(new ImageIcon(SquareImg));
}catch (IOException ex) {}
}
}

