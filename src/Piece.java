
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
}
//more specific stats are given to specific kinds of pieces 
class warrior extends Piece{ 
	warrior(){ //warrior is slow, strong and has a lot of health
		name = "Warrior"; //added name to each piece class
		token = "Circle";
		maxHp = 60;
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
		attack = 4;
		variance = 6;
		crit = 10;
		move = 4;
	}
}