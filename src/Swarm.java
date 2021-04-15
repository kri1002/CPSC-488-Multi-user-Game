import javax.swing.JButton;

public class Swarm {
	Piece swarmPoint;//piece at the center of the swarm
	int swarmStartXCoord;//that piece's coords
	int swarmStartYCoord;
	int bestFitness=0;//the best found fitness
	Particle bestPart;//best particle 
	Particle[] swarmParts;//array to hold the particles, varies in size for each swarm
	
	public Swarm(Piece givenPiece, int pieceXCoord, int pieceYCoord) {//constructor 
		swarmPoint = givenPiece;
		swarmStartXCoord = pieceXCoord;
		swarmStartYCoord = pieceYCoord;
	}
	
	public void findBestMove(JButton[][] tile) {
		int bestPartNum=0;//array ID of best particle
		swarmParts = new Particle[(swarmPoint.move*swarmPoint.move)];//initialize the array of particles
		for (int i = 0; i < swarmParts.length; i++) {//loop through the array
			swarmParts[i] = new Particle (swarmStartXCoord, swarmStartYCoord, swarmPoint.move, i, tile);//construct particle
			if(swarmParts[i].fitness >= swarmParts[bestPartNum].fitness) {//if it is the most fit
				bestPartNum = i;//set it as the best
			}
		}
		bestFitness = swarmParts[bestPartNum].fitness;//set best fitness
		bestPart = swarmParts[bestPartNum];//set best particle once the loop is done
	}
}
