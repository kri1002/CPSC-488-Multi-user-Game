import javax.swing.JButton;

public class Particle {
	int fitness;//fitness of each particle
	int partXCoord;//coordinates for each particle
	int partYCoord;
	
	Particle (int homeX, int homeY, int range, int partNum, JButton[][] tile){
		
	}
	
		// I think we'll a different function to assign a particle to a tile depending on the piece's movement range
		// I was also thinking to have the particle constructor find its fitness, but if you have a better idea go for it
		// dont need to check for actions bc is computer player so we can just end the turn after movement and/or attack/heal if we get that far
		//probably want to determine fitness based on how close piece can be to the human player's pieces??
	
	public void assignParticle(int homeX, int homeY, int range, JButton[][] tile){
		if(partYCoord <= homeY+range && partYCoord >= homeY-range && partXCoord <= homeX+range && partXCoord >=homeX-range ){//check to see if particle is in Piece range
			//assign particle to tile
			//determine and set fitness
				
			}
			else {//if not in range set fitness to worst 
				fitness=0;
			}		
	}
	
}