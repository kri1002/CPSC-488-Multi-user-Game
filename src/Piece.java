import java.util.Random;

public abstract class Piece { //generic game piece class with stats all pieces will have and methods to access them
	String token;
	String name;
	int maxHp;
	int currHp;
	int attack;
	int variance;
	int range;
	int crit;
	int move;
	int team;//each piece knows what team it is a part of
	int tookAction=0;//these keep track of what actions a piece has taken during a turn
	int moved=0;
	
	int getMaxHp() {
		return maxHp;
	}
	int getCurrHp() {
		return currHp;
	}
	int getAttackStat() {
		return attack;
	}
	int getVarianceStat() {
		return variance;
	}
	int getRangeStat() {
		return range;
	}
	int getCritRangeStat() {
		return crit;
	}
	int getMovementStat() {
		return move;
	}
	
	void attack(Piece targetPiece) {//attack function
		Random rand = new Random();//instantiate java random library
		int varRand = rand.nextInt(this.variance)+1;//create random number between 1 and variance
		int critRand = rand.nextInt(100)+1;//create a number between 1 and 100 to compare with crit
		if (critRand <= this.crit) {//if critRand is within crit range
			targetPiece.currHp = targetPiece.currHp - ((this.attack + varRand)*2);//deal twice normal damage
			System.out.println("Critical Hit!");//inform user of crit
		}
		else {
			targetPiece.currHp = targetPiece.currHp - (this.attack + varRand);//else deal normal damage
		}
		/*System.out.println("Attacker: ");
		System.out.println("Name: " + this.name);
		System.out.println("Team: " + this.team);
		System.out.println("HP: " + this.getCurrHp());
		System.out.println("Attack: " + this.getAttackStat());
		System.out.println("Variance: " + this.getVarianceStat());
		System.out.println("Range: " + this.getRangeStat());
		System.out.println("Crit: " + this.getCritRangeStat());
		System.out.println("Move: " + this.getMovementStat());
		System.out.println("Target: ");
		System.out.println("Name: " + targetPiece.name);
		System.out.println("Team: " + targetPiece.team);
		System.out.println("HP: " + targetPiece.getCurrHp());
		System.out.println("Attack: " + targetPiece.getAttackStat());
		System.out.println("Variance: " + targetPiece.getVarianceStat());
		System.out.println("Range: " + targetPiece.getRangeStat());
		System.out.println("Crit: " + targetPiece.getCritRangeStat());
		System.out.println("Move: " + targetPiece.getMovementStat());*/
	}
	
	void heal(Piece targetPiece) {
		int heal =4; //realized the healer piece doesnt have these in their 
		int healVariance =6;
		
		Random rand= new Random();
		int varRand=rand.nextInt(healVariance)+1;
		int critRand=rand.nextInt(100)+1;
		if(critRand <= this.crit) {
			targetPiece.currHp = targetPiece.currHp + ((heal + varRand)*2);//deal twice healing hp
			System.out.println("Critical Heal");//inform user of crit
		}
		else {
			targetPiece.currHp = targetPiece.currHp + (4 + varRand);//else heal normally
		}
	}
		
}

//more specific stats are given to specific kinds of pieces 
class warrior extends Piece{ 
	warrior(int playerNum){ //warrior is slow, strong and has a lot of health
		name = "Warrior"; //added name to each piece class
		token = "Circle";
		maxHp = 60;
		currHp = maxHp;
		attack = 6;
		variance = 4;
		range = 1;
		crit = 10;
		move = 3;
		team = playerNum;//each piece now gets passed the number of the player who created it
	}
}

class warriorClone extends Piece{
	warriorClone(int playerNum){ //This is a clone of warrior with a different name to be used if a team needs two warriors
		name = "Warrior"; 
		token = "Circle2";
		maxHp = 60;
		currHp = maxHp;
		attack = 6;
		variance = 4;
		range = 1;
		crit = 10;
		move = 3;
		team = playerNum;
	}
}

class ranger extends Piece{
	ranger(int playerNum){// ranger is fast, has range, but has lower damage
		name = "Ranger";
		token = "Square";
		maxHp = 35;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 4;
		crit = 15;
		move = 5;
		team = playerNum;
	}
}

class rangerClone extends Piece{
	rangerClone(int playerNum){//a clone of ranger to be used if a team has multiple rangers
		name = "Ranger";
		token = "Square2";
		maxHp = 35;
		currHp =maxHp;
		attack = 3;
		variance = 2;
		range = 4;
		crit = 15;
		move = 5;
		team = playerNum;
	}
}

class rogue extends Piece{
	rogue(int playerNum){//rogue is the fastest, has low damage, but had the highest crit chance
		name = "Rogue";
		token = "Triangle";
		maxHp = 40;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 1;
		crit = 25;
		move = 6;
		team = playerNum;
	}
}

class rogueClone extends Piece{
	rogueClone(int playerNum){// a clone of rogue to be used if a team has multiple rogues
		name = "Rogue";
		token = "Triangle2";
		maxHp = 40;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 1;
		crit = 25;
		move = 6;
		team = playerNum;
	}
}

class healer extends Piece{
	healer(int playerNum){//healer has bad stats all around but will have the ability to heal allies instead of taking the attack action
		name = "Healer";
		token = "Star";
		maxHp = 25;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 3;
		crit = 10;
		move = 4;
		team = playerNum;
	}
}

class damageMage extends Piece{
	damageMage(int playerNum){//damage mage has high damage, moderate range, but has significantly fewer hit points than the warrior
		name = "Damage Mage";
		token = "Pentagon";
		maxHp = 30;
		currHp = maxHp;
		attack = 4;
		variance = 6;
		range = 3;
		crit = 10;
		move = 4;
		team = playerNum;
	}
}

class damageMageClone extends Piece{
	damageMageClone(int playerNum){// a clone of damage mage to be used if a team has multiple damage mages
		name = "Damage Mage";
		token = "Pentagon2";
		maxHp = 30;
		currHp = maxHp;
		attack = 4;
		variance = 6;
		range = 3;
		crit = 10;
		move = 4;
		team = playerNum;
	}
}