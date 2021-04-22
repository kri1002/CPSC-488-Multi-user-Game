import javax.swing.*;

public class Swarm {
	Player opponent;
	Piece swarmPoint;//piece at the center of the swarm
	int swarmStartXCoord;//that piece's coords
	int swarmStartYCoord;
	int bestFitness=0;//the best found fitness
	Particle bestPart;//best particle 
	Particle[] swarmParts;//array to hold the particles, varies in size for each swarm
	ImageIcon[] PieceImages;
	Piece[] teamPieces;
	
	public Swarm(Piece givenPiece, int pieceXCoord, int pieceYCoord, Player passedPlayer, ImageIcon[] passedPieceImages, Piece[] passedPieces) {//constructor 
		swarmPoint = givenPiece;
		swarmStartXCoord = pieceXCoord;
		swarmStartYCoord = pieceYCoord;
		opponent = passedPlayer;
		PieceImages = passedPieceImages;
		teamPieces = passedPieces;
	}
	
	public void findBestMove(JButton[][] tile, int pieceFound) {
		if(pieceFound==1) {
			int bestPartNum=0;//array ID of best particle
			swarmParts = new Particle[100];//initialize the array of particles
			for (int i = 0; i < swarmParts.length; i++) {//loop through the array
				swarmParts[i] = new Particle (swarmStartXCoord, swarmStartYCoord, swarmPoint.range, swarmPoint.name, i, tile, opponent, PieceImages, teamPieces);//construct particle
				if(swarmParts[i].fitness >= swarmParts[bestPartNum].fitness) {//if it is the most fit
					bestPartNum = i;//set it as the best
				}
			}
			bestFitness = swarmParts[bestPartNum].fitness;//set best fitness
			bestPart = swarmParts[bestPartNum];//set best particle once the loop is done
		}
		else {
			bestFitness = 0;
		}
	}
}
