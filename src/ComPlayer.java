import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ComPlayer extends Player{//inherits from player
	public Swarm moveFinder1;//swarm for piece1
	public Swarm moveFinder2;//swarm for piece2
	public Swarm moveFinder3;//swarm for piece3
	public Swarm moveFinder4;//swarm for piece4
	public Swarm moveFinder5;//swarm for piece5
	public Swarm bestSwarm;
	public int[] pieceXCoords = new int[5];//array for the coords of the pieces
	public int[] pieceYCoords = new int[5];
	ImageIcon[] compPieceImages = new ImageIcon[5]; //this array keeps track of the icons of each piece in each team
	public Piece bestMovePiece;//place to hold the best piece to move
	public int bestXCoord;//coords of the best place to move
	public int bestYCoord;
	public int startXCoord; //coords of piece start location so can keep track to move piece
	public int startYCoord;
	Icon tempImg;
	public int[] piecesFound = new int[5];
	
	public ComPlayer(int playerNum) {//same constructor as before
		super(playerNum);
		this.playerNum=playerNum;
	}
	
	
	public void findPieceLocs (JButton[][] tile) {
		for(int i=0; i<5; i++) {
			piecesFound[i]=0;
		}
		for (int i=0; i<tile.length; i++) {//This loop gets the coords of each piece the comPlayer has
			 for(int j=0; j<tile[i].length; j++) {
				 if (tile[i][j].getIcon() == this.PieceImages[0]) {
					 pieceXCoords[0] = j;
					 pieceYCoords[0] = i;
					 compPieceImages[0]=this.PieceImages[0];
					 piecesFound[0]=1;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[1]) {
					 pieceXCoords[1] = j;
					 pieceYCoords[1] = i;
					 compPieceImages[1]=this.PieceImages[1];
					 piecesFound[1]=1;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[2]) {
					 pieceXCoords[2] = j;
					 pieceYCoords[2] = i;
					 compPieceImages[2]=this.PieceImages[2];
					 piecesFound[2]=1;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[3]) {
					 pieceXCoords[3] = j;
					 pieceYCoords[3] = i;
					 compPieceImages[3]=this.PieceImages[3];
					 piecesFound[3]=1;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[4]) {
					 pieceXCoords[4] = j;
					 pieceYCoords[4] = i;
					 compPieceImages[4]=this.PieceImages[4];
					 piecesFound[4]=1;
				 }
			 }
		}
	}
	
	public void makeMove(JButton[][]tile, int turnSeed, JFrame currFrame) {//similar to swapImg in Game.java
		tile[startYCoord][startXCoord].setIcon(null); //sets tile with original piece null
		tile[bestYCoord][bestXCoord].setIcon(tempImg); //move piece icon to best x & y
		tile[startYCoord][startXCoord].revalidate(); //reset & update tiles
		tile[bestYCoord][bestXCoord].revalidate();
		if(bestSwarm.bestPart.bestAction=="heal") {
			System.out.println("Player 2's healer is healing");
			int tempHp = bestSwarm.bestPart.bestTarget.currHp;
			bestSwarm.swarmPoint.heal(bestSwarm.bestPart.bestTarget);
			if(bestSwarm.bestPart.bestTarget.getCurrHp() > bestSwarm.bestPart.bestTarget.getMaxHp()) {
				bestSwarm.bestPart.bestTarget.currHp = bestSwarm.bestPart.bestTarget.getMaxHp();
				System.out.println("Player 2's " + bestSwarm.bestPart.bestTarget.name + " went from " + tempHp + " hit points to " + bestSwarm.bestPart.bestTarget.currHp + " hit points.");
			}
			else {
				System.out.println("Player 2's " + bestSwarm.bestPart.bestTarget.name + " went from " + tempHp + " hit points to " + bestSwarm.bestPart.bestTarget.currHp + " hit points.");
			}
		}
		else {
			System.out.println("Player 2's " + bestMovePiece.name + " attacks!");
			int tempHp = bestSwarm.bestPart.bestTarget.currHp;
			bestSwarm.swarmPoint.attack(bestSwarm.bestPart.bestTarget);
			if(bestSwarm.bestPart.bestTarget.getCurrHp() <= 0) {
				System.out.println("Player 1's " + bestSwarm.bestPart.bestTarget.name + " went from " + tempHp + " hit points to 0 hit points.");
				System.out.println("Player 1's " + bestSwarm.bestPart.bestTarget.name + " has been defeated!");
				tile[bestSwarm.bestPart.targetY][bestSwarm.bestPart.targetX].setIcon(null);
				tile[bestSwarm.bestPart.targetY][bestSwarm.bestPart.targetX].revalidate();
				bestSwarm.opponent.piecesLeft--;
				System.out.println("Player 1 has " + bestSwarm.opponent.piecesLeft + " pieces left!");
				if(bestSwarm.opponent.piecesLeft==0) {
					JOptionPane.showMessageDialog(currFrame, "The Game is is over. Closing game...", "Game Over", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
			}
			else {
				System.out.println("Player 1's " + bestSwarm.bestPart.bestTarget.name + " went from " + tempHp + " hit points to " + bestSwarm.bestPart.bestTarget.currHp + " hit points.");
			}
		}
		//sets turn to other player after moving piece
		//will have to move where this is done if get to computer attacking
		if(turnSeed==1) {
			turnSeed=2;
		}
		else if(turnSeed==2){
			turnSeed=1;
		}
		}
	
	
	public void findMove (JButton[][] tile, Player opponent) {
		moveFinder1 = new Swarm(this.playersTeam.teamPieces[0], pieceXCoords[0], pieceYCoords[0], opponent, this.PieceImages, this.playersTeam.teamPieces);//initialize the swarms
		moveFinder2 = new Swarm(this.playersTeam.teamPieces[1], pieceXCoords[1], pieceYCoords[1], opponent, this.PieceImages, this.playersTeam.teamPieces);
		moveFinder3 = new Swarm(this.playersTeam.teamPieces[2], pieceXCoords[2], pieceYCoords[2], opponent, this.PieceImages, this.playersTeam.teamPieces);
		moveFinder4 = new Swarm(this.playersTeam.teamPieces[3], pieceXCoords[3], pieceYCoords[3], opponent, this.PieceImages, this.playersTeam.teamPieces);
		moveFinder5 = new Swarm(this.playersTeam.teamPieces[4], pieceXCoords[4], pieceYCoords[4], opponent, this.PieceImages, this.playersTeam.teamPieces);
		moveFinder1.findBestMove(tile, piecesFound[0]);//tell them to find the best move
		moveFinder2.findBestMove(tile, piecesFound[1]);
		moveFinder3.findBestMove(tile, piecesFound[2]);
		moveFinder4.findBestMove(tile, piecesFound[3]);
		moveFinder5.findBestMove(tile, piecesFound[4]);
		if (moveFinder1.bestFitness >= moveFinder2.bestFitness && moveFinder1.bestFitness >= moveFinder3.bestFitness
				&& moveFinder1.bestFitness >= moveFinder4.bestFitness && moveFinder1.bestFitness >= moveFinder5.bestFitness) {
			//if piece 1 has the best move to make^
			bestSwarm = moveFinder1;
			bestMovePiece = moveFinder1.swarmPoint;//set it as the best piece to move
			bestXCoord = moveFinder1.bestPart.partXCoord;//set its best coords to move to
			bestYCoord = moveFinder1.bestPart.partYCoord;
			startXCoord = pieceXCoords[0];
			startYCoord = pieceYCoords[0];
			tempImg= compPieceImages[0];
		}
		if (moveFinder2.bestFitness >= moveFinder1.bestFitness && moveFinder2.bestFitness >= moveFinder3.bestFitness
				&& moveFinder2.bestFitness >= moveFinder4.bestFitness && moveFinder2.bestFitness >= moveFinder5.bestFitness) {
			bestSwarm = moveFinder2;
			bestMovePiece = moveFinder2.swarmPoint;
			bestXCoord = moveFinder2.bestPart.partXCoord;
			bestYCoord = moveFinder2.bestPart.partYCoord;
			startXCoord = pieceXCoords[1];
			startYCoord = pieceYCoords[1];
			tempImg= compPieceImages[1];
		}
		if (moveFinder3.bestFitness >= moveFinder1.bestFitness && moveFinder3.bestFitness >= moveFinder2.bestFitness
				&& moveFinder3.bestFitness >= moveFinder4.bestFitness && moveFinder3.bestFitness >= moveFinder5.bestFitness) {
			bestSwarm = moveFinder3;
			bestMovePiece = moveFinder3.swarmPoint;
			bestXCoord = moveFinder3.bestPart.partXCoord;
			bestYCoord = moveFinder3.bestPart.partYCoord;
			startXCoord = pieceXCoords[2];
			startYCoord = pieceYCoords[2];
			tempImg= compPieceImages[2];
		}
		if (moveFinder4.bestFitness >= moveFinder1.bestFitness && moveFinder4.bestFitness >= moveFinder2.bestFitness
				&& moveFinder4.bestFitness >= moveFinder3.bestFitness && moveFinder4.bestFitness >= moveFinder5.bestFitness) {
			bestSwarm = moveFinder4;
			bestMovePiece = moveFinder4.swarmPoint;
			bestXCoord = moveFinder4.bestPart.partXCoord;
			bestYCoord = moveFinder4.bestPart.partYCoord;
			startXCoord = pieceXCoords[3];
			startYCoord = pieceYCoords[3];
			tempImg= compPieceImages[3];
		}
		if (moveFinder5.bestFitness >= moveFinder1.bestFitness && moveFinder5.bestFitness >= moveFinder2.bestFitness
				&& moveFinder5.bestFitness >= moveFinder3.bestFitness && moveFinder5.bestFitness >= moveFinder4.bestFitness) {
			bestSwarm = moveFinder4;
			bestMovePiece = moveFinder5.swarmPoint;
			bestXCoord = moveFinder5.bestPart.partXCoord;
			bestYCoord = moveFinder5.bestPart.partYCoord;
			startXCoord = pieceXCoords[4];
			startYCoord = pieceXCoords[4];
			tempImg= compPieceImages[4];
		}
	}
}
