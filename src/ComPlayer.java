import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ComPlayer extends Player{//inherits from player
	public Swarm moveFinder1;//swarm for piece1
	public Swarm moveFinder2;//swarm for piece2
	public Swarm moveFinder3;//swarm for piece3
	public Swarm moveFinder4;//swarm for piece4
	public Swarm moveFinder5;//swarm for piece5
	public int[] pieceXCoords = new int[5];//array for the coords of the pieces
	public int[] pieceYCoords = new int[5];
	public Piece bestMovePiece;//place to hold the best piece to move
	public int bestXCoord;//coords of the best place to move
	public int bestYCoord;
	public int startXCoord; //coords of piece start location so can keep track to move piece
	public int startYCoord;
	
	public ComPlayer(int playerNum) {//same constructor as before
		super(playerNum);
		this.playerNum=playerNum;
	}
	
	
	public void findPieceLocs (JButton[][] tile) {
		for (int i=0; i<tile.length; i++) {//This loop gets the coords of each piece the comPlayer has
			 for(int j=0; j<tile[i].length; j++) {
				 if (tile[i][j].getIcon() == this.PieceImages[0]) {
					 pieceXCoords[0] = j;
					 pieceYCoords[0] = i;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[1]) {
					 pieceXCoords[1] = j;
					 pieceYCoords[1] = i;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[2]) {
					 pieceXCoords[2] = j;
					 pieceYCoords[2] = i;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[3]) {
					 pieceXCoords[3] = j;
					 pieceYCoords[3] = i;
				 }
				 else if (tile[i][j].getIcon() == this.PieceImages[4]) {
					 pieceXCoords[4] = j;
					 pieceYCoords[4] = i;
				 }
			 }
		}
	}

	
	public void makeMove(JButton[][]tile, int turnSeed) {//similar to swapImg in Game.java
		tile[startYCoord][startXCoord].setIcon(null); //sets tile with original piece null
		tile[bestYCoord][bestXCoord].setIcon(tile[startYCoord][startXCoord].getIcon()); //move piece icon to best x & y
		tile[startYCoord][startXCoord].revalidate(); //reset & update tiles
		tile[bestYCoord][bestXCoord].revalidate();
		
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
		moveFinder1.findBestMove(tile);//tell them to find the best move
		moveFinder2.findBestMove(tile);
		moveFinder3.findBestMove(tile);
		moveFinder4.findBestMove(tile);
		moveFinder5.findBestMove(tile);
		if (moveFinder1.bestFitness >= moveFinder2.bestFitness && moveFinder1.bestFitness >= moveFinder3.bestFitness
				&& moveFinder1.bestFitness >= moveFinder4.bestFitness && moveFinder1.bestFitness >= moveFinder5.bestFitness) {
			//if piece 1 has the best move to make^
			bestMovePiece = moveFinder1.swarmPoint;//set it as the best piece to move
			bestXCoord = moveFinder1.bestPart.partXCoord;//set its best coords to move to
			bestYCoord = moveFinder1.bestPart.partYCoord;
			startXCoord = pieceXCoords[0];
			startYCoord = pieceYCoords[0];
		}
		if (moveFinder2.bestFitness >= moveFinder1.bestFitness && moveFinder2.bestFitness >= moveFinder3.bestFitness
				&& moveFinder2.bestFitness >= moveFinder4.bestFitness && moveFinder2.bestFitness >= moveFinder5.bestFitness) {
			bestMovePiece = moveFinder2.swarmPoint;
			bestXCoord = moveFinder2.bestPart.partXCoord;
			bestYCoord = moveFinder2.bestPart.partYCoord;
			startXCoord = pieceXCoords[1];
			startYCoord = pieceYCoords[1];
		}
		if (moveFinder3.bestFitness >= moveFinder1.bestFitness && moveFinder3.bestFitness >= moveFinder2.bestFitness
				&& moveFinder3.bestFitness >= moveFinder4.bestFitness && moveFinder3.bestFitness >= moveFinder5.bestFitness) {
			bestMovePiece = moveFinder3.swarmPoint;
			bestXCoord = moveFinder3.bestPart.partXCoord;
			bestYCoord = moveFinder3.bestPart.partYCoord;
			startXCoord = pieceXCoords[2];
			startYCoord = pieceYCoords[2];
		}
		if (moveFinder4.bestFitness >= moveFinder1.bestFitness && moveFinder4.bestFitness >= moveFinder2.bestFitness
				&& moveFinder4.bestFitness >= moveFinder3.bestFitness && moveFinder4.bestFitness >= moveFinder5.bestFitness) {
			bestMovePiece = moveFinder4.swarmPoint;
			bestXCoord = moveFinder4.bestPart.partXCoord;
			bestYCoord = moveFinder4.bestPart.partYCoord;
			startXCoord = pieceXCoords[3];
			startYCoord = pieceYCoords[3];
		}
		if (moveFinder5.bestFitness >= moveFinder1.bestFitness && moveFinder5.bestFitness >= moveFinder2.bestFitness
				&& moveFinder5.bestFitness >= moveFinder3.bestFitness && moveFinder5.bestFitness >= moveFinder4.bestFitness) {
			bestMovePiece = moveFinder5.swarmPoint;
			bestXCoord = moveFinder5.bestPart.partXCoord;
			bestYCoord = moveFinder5.bestPart.partYCoord;
			startXCoord = pieceXCoords[4];
			startYCoord = pieceXCoords[4];
		}
	}
}
