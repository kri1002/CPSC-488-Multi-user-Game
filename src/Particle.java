import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Particle {
	Player opponent;
	int fitness=0;//fitness of each particle
	int partXCoord;//coordinates for each particle
	int partYCoord;
	ImageIcon[] teamPieceImages;
	Piece[] teamPieces;
	Piece bestTarget;
	int lowestHp=100;
	int currTarget=0;
	String bestAction="attack";
	int targetX;
	int targetY;
	
	Particle (int homeX, int homeY, int range, String pieceName, int partNum, JButton[][] tile, Player passedPlayer, ImageIcon[] passedPieceImages, Piece[] passedPieces){
		opponent = passedPlayer;
		teamPieceImages = passedPieceImages;
		teamPieces = passedPieces;
		if (pieceName == "Warrior") {
			assignWarriorParticle(homeX, homeY, range, tile, partNum);
		}
		if (pieceName == "Ranger") {
			assignRangerParticle(homeX, homeY, range, tile, partNum);
		}
		if (pieceName == "Rogue") {
			assignRogueParticle(homeX, homeY, range, tile, partNum);
		}
		if (pieceName == "Healer") {
			assignHealerParticle(homeX, homeY, range, tile, partNum);
		}
		if (pieceName == "Damage Mage") {
			assignMageParticle(homeX, homeY, range, tile, partNum);
		}
	}
	
		// I think we'll a different function to assign a particle to a tile depending on the piece's movement range
		// I was also thinking to have the particle constructor find its fitness, but if you have a better idea go for it
		// dont need to check for actions bc is computer player so we can just end the turn after movement and/or attack/heal if we get that far
		//probably want to determine fitness based on how close piece can be to the human player's pieces??
	
	public void assignWarriorParticle(int homeX, int homeY, int range, JButton[][] tile, int partNum){ 
		partYCoord = partNum/10;//y coord is the 10s place
		partXCoord = partNum%10;//x coord is the 1s place
		if(partYCoord <= homeY+3 && partYCoord >= homeY-3 && partXCoord <= homeX+3 && partXCoord >=homeX-3 ){//check to see if particle is in Piece range
				if (tile[partYCoord][partXCoord].getIcon() == null) {//if there is no piece on that tile
					for (int i=partYCoord-range; i<partYCoord+range; i++) {//loop through the tiles within that piece's range
						for (int j=partXCoord-range; j<partXCoord+range; j++) {
							if (i>9 || i<0 || j>9 || j<0) {//if it attempts to check a tile that is off the board
								//skip the iteration
							}
							else {
								if (tile[i][j].getIcon()==opponent.PieceImages[0]) {//if that tile has an enemy
									fitness++;//add to fitness
									if (opponent.playersTeam.teamPieces[0].name=="Healer") {//if its a healer
										fitness++;//add to fitness again
									}
									if (opponent.playersTeam.teamPieces[0].currHp < opponent.playersTeam.teamPieces[0].maxHp) {
										//if the enemy piece is below full^
										fitness++;
										if (opponent.playersTeam.teamPieces[0].currHp <= 15) {//if they are low
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[0].currHp < lowestHp) {
										currTarget=0;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[1]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[1].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[1].currHp < opponent.playersTeam.teamPieces[1].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[1].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[1].currHp < lowestHp) {
										currTarget=1;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[2]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[2].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[2].currHp < opponent.playersTeam.teamPieces[2].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[2].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[2].currHp < lowestHp) {
										currTarget=2;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[3]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[3].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[3].currHp < opponent.playersTeam.teamPieces[3].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[3].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[3].currHp < lowestHp) {
										currTarget=3;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[4]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[4].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[4].currHp < opponent.playersTeam.teamPieces[4].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[4].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[4].currHp < lowestHp) {
										currTarget=4;
										targetY=i;
										targetX=j;
									}
								}
							}//end of else
						}//end of inner loop
					}//end of outer loop
				}
				else {//if there is, you can't move there, so set fitness to worst
					fitness=0;
				}
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}
		bestTarget=opponent.playersTeam.teamPieces[currTarget];
	}
	
	public void assignRangerParticle(int homeX, int homeY, int range, JButton[][] tile, int partNum){
		partYCoord = partNum/10;//y coord is the 10s place
		partXCoord = partNum%10;//x coord is the 1s place
		if(partYCoord <= homeY+5 && partYCoord >= homeY-5 && partXCoord <= homeX+5 && partXCoord >=homeX-5 ){//check to see if particle is in Piece range
				if (tile[partYCoord][partXCoord].getIcon() == null) {//if there is no piece on that tile
					for (int i=partYCoord-range; i<partYCoord+range; i++) {//loop through the tiles within that piece's range
						for (int j=partXCoord-range; j<partXCoord+range; j++) {
							if (i>9 || i<0 || j>9 || j<0) {//if it attempts to check a tile that is off the board
								//skip the iteration
							}
							else {
								if (tile[i][j].getIcon()==opponent.PieceImages[0]) {//if that tile has an enemy
									fitness++;//add to fitness
									if (opponent.playersTeam.teamPieces[0].name=="Healer") {//if its a healer
										fitness++;//add to fitness again
									}
									if (opponent.playersTeam.teamPieces[0].currHp < opponent.playersTeam.teamPieces[0].maxHp) {
										// if that enemy piece is below full^
										fitness++;
										if (opponent.playersTeam.teamPieces[0].currHp <= 15) {//if they are low
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[0].currHp < lowestHp) {
										currTarget=0;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[1]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[1].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[1].currHp < opponent.playersTeam.teamPieces[1].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[1].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[1].currHp < lowestHp) {
										currTarget=1;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[2]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[2].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[2].currHp < opponent.playersTeam.teamPieces[2].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[2].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[2].currHp < lowestHp) {
										currTarget=2;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[3]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[3].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[3].currHp < opponent.playersTeam.teamPieces[3].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[3].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[3].currHp < lowestHp) {
										currTarget=3;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[4]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[4].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[4].currHp < opponent.playersTeam.teamPieces[4].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[4].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[4].currHp < lowestHp) {
										currTarget=4;
										targetY=i;
										targetX=j;
									}
								}
							}//end of else
						}//end of inner loop
					}//end of outer loop
				}
				else {//if there is, you can't move there, so set fitness to worst
					fitness=0;
				}
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}		
		bestTarget=opponent.playersTeam.teamPieces[currTarget];
	}
	
	public void assignRogueParticle(int homeX, int homeY, int range, JButton[][] tile, int partNum){
		partYCoord = partNum/10;//y coord is the 10s place
		partXCoord = partNum%10;//x coord is the 1s place
		if(partYCoord <= homeY+6 && partYCoord >= homeY-6 && partXCoord <= homeX+6 && partXCoord >=homeX-6 ){//check to see if particle is in Piece range
				if (tile[partYCoord][partXCoord].getIcon() == null) {//if there is no piece on that tile
					for (int i=partYCoord-range; i<partYCoord+range; i++) {//loop through the tiles within that piece's range
						for (int j=partXCoord-range; j<partXCoord+range; j++) {
							if (i>9 || i<0 || j>9 || j<0) {//if it attempts to check a tile that is off the board
								//skip the iteration
							}
							else {
								if (tile[i][j].getIcon()==opponent.PieceImages[0]) {//if that tile has an enemy
									fitness++;//add to fitness
									if (opponent.playersTeam.teamPieces[0].name=="Healer") {//if its a healer
										fitness++;//add to fitness again
									}
									if (opponent.playersTeam.teamPieces[0].currHp < opponent.playersTeam.teamPieces[0].maxHp) {
										//if the enemy piece is below full^
										fitness++;
										if (opponent.playersTeam.teamPieces[0].currHp <= 15) {//if they are low
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[0].currHp < lowestHp) {
										currTarget=0;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[1]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[1].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[1].currHp < opponent.playersTeam.teamPieces[1].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[1].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[1].currHp < lowestHp) {
										currTarget=1;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[2]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[2].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[2].currHp < opponent.playersTeam.teamPieces[2].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[2].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[2].currHp < lowestHp) {
										currTarget=2;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[3]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[3].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[3].currHp < opponent.playersTeam.teamPieces[3].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[3].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[3].currHp < lowestHp) {
										currTarget=3;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[4]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[4].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[4].currHp < opponent.playersTeam.teamPieces[4].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[4].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[4].currHp < lowestHp) {
										currTarget=4;
										targetY=i;
										targetX=j;
									}
								}
							}//end of else
						}//end of inner loop
					}//end of outer loop
				}
				else {//if there is, you can't move there, so set fitness to worst
					fitness=0;
				}
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}	
		bestTarget=opponent.playersTeam.teamPieces[currTarget];
	}
	
	public void assignHealerParticle(int homeX, int homeY, int range, JButton[][] tile, int partNum){
		partYCoord = partNum/10;//y coord is the 10s place
		partXCoord = partNum%10;//x coord is the 1s place
		if(partYCoord <= homeY+4 && partYCoord >= homeY-4 && partXCoord <= homeX+4 && partXCoord >=homeX-4 ){//check to see if particle is in Piece range
				if (tile[partYCoord][partXCoord].getIcon() == null) {//if there is no piece on that tile
					for (int i=partYCoord-range; i<partYCoord+range; i++) {//loop through the tiles within that piece's range
						for (int j=partXCoord-range; j<partXCoord+range; j++) {
							if (i>9 || i<0 || j>9 || j<0) {//if it attempts to check a tile that is off the board
								//skip the iteration
							}
							else {
								if (tile[i][j].getIcon()==teamPieceImages[0]) {//if the tile has an ally
									//fitness++;//There is an ally within range, add to fitness
									if (teamPieces[0].currHp < teamPieces[0].maxHp) {//if that ally is below full
										fitness++;//there is an ally withing range that can be healed, add to fitness further
										if (teamPieces[0].currHp <= 15) {//if that ally is low on health
											fitness=fitness+2;//there is an ally within range who is low on health, add to fitness further
										}
										if (teamPieces[0].currHp < lowestHp) {
											currTarget=0;
											bestAction="heal";
											targetY=i;
											targetX=j;
										}
									}
								}
								if (tile[i][j].getIcon()==teamPieceImages[1]) {
									//fitness++;
									if (teamPieces[1].currHp < teamPieces[1].maxHp) {
										fitness++;
										if (teamPieces[1].currHp <= 15) {
											fitness=fitness+2;
										}
										if (teamPieces[1].currHp < lowestHp) {
											currTarget=1;
											bestAction="heal";
											targetY=i;
											targetX=j;
										}
									}
								}
								if (tile[i][j].getIcon()==teamPieceImages[2]) {
									//fitness++;
									if (teamPieces[2].currHp < teamPieces[2].maxHp) {
										fitness++;
										if (teamPieces[2].currHp <= 15) {
											fitness=fitness+2;
										}
										if (teamPieces[2].currHp < lowestHp) {
											currTarget=2;
											bestAction="heal";
											targetY=i;
											targetX=j;
										}
									}
								}
								if (tile[i][j].getIcon()==teamPieceImages[3]) {
									//fitness++;
									if (teamPieces[3].currHp < teamPieces[3].maxHp) {
										fitness++;
										if (teamPieces[3].currHp <= 15) {
											fitness=fitness+2;
										}
										if (teamPieces[3].currHp < lowestHp) {
											currTarget=3;
											bestAction="heal";
											targetY=i;
											targetX=j;
										}
									}
								}
								if (tile[i][j].getIcon()==teamPieceImages[4]) {
									//fitness++;
									if (teamPieces[4].currHp < teamPieces[4].maxHp) {
										fitness++;
										if (teamPieces[4].currHp <= 15) {
											fitness=fitness+2;
										}
										if (teamPieces[4].currHp < lowestHp) {
											currTarget=4;
											bestAction="heal";
											targetY=i;
											targetX=j;
										}
									}
								}
								//the healer naturally should prioritize healing, but if they cant, they can attack
								if (fitness==0) {
									if (tile[i][j].getIcon()==opponent.PieceImages[0]) {//if that tile has an enemy
										fitness++;//add to fitness
										if (opponent.playersTeam.teamPieces[0].name=="Healer") {//if its a healer
											fitness++;//add to fitness again
										}
										if (opponent.playersTeam.teamPieces[0].currHp < opponent.playersTeam.teamPieces[0].maxHp) {
											//if the enemy piece is below full^
											fitness++;
											if (opponent.playersTeam.teamPieces[0].currHp <= 15) {//if they are low
												fitness++;
											}
										}
										if (opponent.playersTeam.teamPieces[0].currHp < lowestHp) {
											currTarget=0;
											targetY=i;
											targetX=j;
										}
									}
									if (tile[i][j].getIcon()==opponent.PieceImages[1]) {
										fitness++;
										if (opponent.playersTeam.teamPieces[1].name=="Healer") {
											fitness++;
										}
										if (opponent.playersTeam.teamPieces[1].currHp < opponent.playersTeam.teamPieces[1].maxHp) {
											fitness++;
											if (opponent.playersTeam.teamPieces[1].currHp <= 15) {
												fitness++;
											}
										}
										if (opponent.playersTeam.teamPieces[1].currHp < lowestHp) {
											currTarget=1;
											targetY=i;
											targetX=j;
										}
									}
									if (tile[i][j].getIcon()==opponent.PieceImages[2]) {
										fitness++;
										if (opponent.playersTeam.teamPieces[2].name=="Healer") {
											fitness++;
										}
										if (opponent.playersTeam.teamPieces[2].currHp < opponent.playersTeam.teamPieces[2].maxHp) {
											fitness++;
											if (opponent.playersTeam.teamPieces[2].currHp <= 15) {
												fitness++;
											}
										}
										if (opponent.playersTeam.teamPieces[2].currHp < lowestHp) {
											currTarget=2;
											targetY=i;
											targetX=j;
										}
									}
									if (tile[i][j].getIcon()==opponent.PieceImages[3]) {
										fitness++;
										if (opponent.playersTeam.teamPieces[3].name=="Healer") {
											fitness++;
										}
										if (opponent.playersTeam.teamPieces[3].currHp < opponent.playersTeam.teamPieces[3].maxHp) {
											fitness++;
											if (opponent.playersTeam.teamPieces[3].currHp <= 15) {
												fitness++;
											}
										}
										if (opponent.playersTeam.teamPieces[3].currHp < lowestHp) {
											currTarget=3;
											targetY=i;
											targetX=j;
										}
									}
									if (tile[i][j].getIcon()==opponent.PieceImages[4]) {
										fitness++;
										if (opponent.playersTeam.teamPieces[4].name=="Healer") {
											fitness++;
										}
										if (opponent.playersTeam.teamPieces[4].currHp < opponent.playersTeam.teamPieces[4].maxHp) {
											fitness++;
											if (opponent.playersTeam.teamPieces[4].currHp <= 15) {
												fitness++;
											}
										}
										if (opponent.playersTeam.teamPieces[4].currHp < lowestHp) {
											currTarget=4;
											targetY=i;
											targetX=j;
										}
									}
								}
							}//end of else
						}//end of inner loop
					}//end of outer loop
				}
				else {//if there is, you can't move there, so set fitness to worst
					fitness=0;
				}
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}	
		if(bestAction=="heal") {
			bestTarget=teamPieces[currTarget];
		}
		else {
			bestTarget=opponent.playersTeam.teamPieces[currTarget];
		}
	}
	
	public void assignMageParticle(int homeX, int homeY, int range, JButton[][] tile, int partNum){
		partYCoord = partNum/10;//y coord is the 10s place
		partXCoord = partNum%10;//x coord is the 1s place
		if(partYCoord <= homeY+4 && partYCoord >= homeY-4 && partXCoord <= homeX+4 && partXCoord >=homeX-4 ){//check to see if particle is in Piece range
				if (tile[partYCoord][partXCoord].getIcon() == null) {//if there is no piece on that tile
					for (int i=partYCoord-range; i<partYCoord+range; i++) {//loop through the tiles within that piece's range
						for (int j=partXCoord-range; j<partXCoord+range; j++) {
							if (i>9 || i<0 || j>9 || j<0) {//if it attempts to check a tile that is off the board
								//skip the iteration
							}
							else {
								if (tile[i][j].getIcon()==opponent.PieceImages[0]) {//if that tile has an enemy
									fitness++;//add to fitness
									if (opponent.playersTeam.teamPieces[0].name=="Healer") {//if its a healer
										fitness++;//add to fitness again
									}
									if (opponent.playersTeam.teamPieces[0].currHp < opponent.playersTeam.teamPieces[0].maxHp) {
										//if the enemy piece is below full^
										fitness++;
										if (opponent.playersTeam.teamPieces[0].currHp <= 15) {//if they are low
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[0].currHp < lowestHp) {
										currTarget=0;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[1]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[1].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[1].currHp < opponent.playersTeam.teamPieces[1].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[1].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[1].currHp < lowestHp) {
										currTarget=1;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[2]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[2].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[2].currHp < opponent.playersTeam.teamPieces[2].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[2].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[2].currHp < lowestHp) {
										currTarget=2;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[3]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[3].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[3].currHp < opponent.playersTeam.teamPieces[3].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[3].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[3].currHp < lowestHp) {
										currTarget=3;
										targetY=i;
										targetX=j;
									}
								}
								if (tile[i][j].getIcon()==opponent.PieceImages[4]) {
									fitness++;
									if (opponent.playersTeam.teamPieces[4].name=="Healer") {
										fitness++;
									}
									if (opponent.playersTeam.teamPieces[4].currHp < opponent.playersTeam.teamPieces[4].maxHp) {
										fitness++;
										if (opponent.playersTeam.teamPieces[4].currHp <= 15) {
											fitness++;
										}
									}
									if (opponent.playersTeam.teamPieces[4].currHp < lowestHp) {
										currTarget=4;
										targetY=i;
										targetX=j;
									}
								}
							}//end of else
						}//end of inner loop
					}//end of outer loop
				}
				else {//if there is, you can't move there, so set fitness to worst
					fitness=0;
				}
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}	
		bestTarget=opponent.playersTeam.teamPieces[currTarget];
	}
}