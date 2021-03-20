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
	warrior(){ //warrior is slow, strong and has a lot of health
		name = "Warrior"; //added name to each piece class
		token = "Circle";
		maxHp = 60;
		currHp = maxHp;
		attack = 6;
		variance = 4;
		range = 1;
		crit = 10;
		move = 3;
	}
}

class warriorClone extends Piece{
	warriorClone(){ //This is a clone of warrior with a different name to be used if a team needs two warriors
		name = "Warrior"; 
		token = "Circle2";
		maxHp = 60;
		currHp = maxHp;
		attack = 6;
		variance = 4;
		range = 1;
		crit = 10;
		move = 3;
	}
}

class ranger extends Piece{
	ranger(){// ranger is fast, has range, but has lower damage
		name = "Ranger";
		token = "Square";
		maxHp = 35;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 4;
		crit = 15;
		move = 5;
	}
}

class rangerClone extends Piece{
	rangerClone(){//a clone of ranger to be used if a team has multiple rangers
		name = "Ranger";
		token = "Square2";
		maxHp = 35;
		currHp =maxHp;
		attack = 3;
		variance = 2;
		range = 4;
		crit = 15;
		move = 5;
	}
}

class rogue extends Piece{
	rogue(){//rogue is the fastest, has low damage, but had the highest crit chance
		name = "Rogue";
		token = "Triangle";
		maxHp = 40;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 1;
		crit = 25;
		move = 6;
	}
}

class rogueClone extends Piece{
	rogueClone(){// a clone of rogue to be used if a team has multiple rogues
		name = "Rogue";
		token = "Triangle2";
		maxHp = 40;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 1;
		crit = 25;
		move = 6;
	}
}

class healer extends Piece{
	healer(){//healer has bad stats all around but will have the ability to heal allies instead of taking the attack action
		name = "Healer";
		token = "Star";
		maxHp = 25;
		currHp = maxHp;
		attack = 3;
		variance = 2;
		range = 3;
		crit = 10;
		move = 4;
	}
}

class damageMage extends Piece{
	damageMage(){//damage mage has high damage, moderate range, but has significantly fewer hit points than the warrior
		name = "Damage Mage";
		token = "Pentagon";
		maxHp = 30;
		currHp = maxHp;
		attack = 4;
		variance = 6;
		crit = 10;
		move = 4;
	}
}

class damageMageClone extends Piece{
	damageMageClone(){// a clone of damage mage to be used if a team has multiple damage mages
		name = "Damage Mage";
		token = "Pentagon2";
		maxHp = 30;
		currHp = maxHp;
		attack = 4;
		variance = 6;
		crit = 10;
		move = 4;
	}
}