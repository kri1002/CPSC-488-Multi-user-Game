import javax.swing.JButton;

public class Particle {
	int fitness;//fitness of each particle
	int partXCoord;//coordinates for each particle
	int partYCoord;
	
	Particle (int homeX, int homeY, int range, int partNum, JButton[][] tile){
		// I think we'll a different function to assign a particle to a tile depending on the piece's movement range
		// I was also thinking to have the particle constructor find its fitness, but if you have a better idea go for it
	}
}
