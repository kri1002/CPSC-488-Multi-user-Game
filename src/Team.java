
public abstract class Team {//generic class for a team of pieces 
	Piece[] teamPieces = new Piece[5];//each team is an array of pieces 
}

class Team1 extends Team{
	Team1(int playerNum) {//team 1 is the basic team having one of each piece
		teamPieces[0] = new warrior(playerNum);//each piece needs the number of the player the team belongs to
		teamPieces[1] = new ranger(playerNum);
		teamPieces[2] = new rogue(playerNum);
		teamPieces[3] = new healer(playerNum);
		teamPieces[4] = new damageMage(playerNum);
	}
}

class Team2 extends Team{
	Team2(int playerNum){//team 2 is a reckless team, having no healer, but 2 warriors
		teamPieces[0] = new warrior(playerNum);
		teamPieces[1] = new warriorClone(playerNum);
		teamPieces[2] = new ranger(playerNum);
		teamPieces[3] = new rogue(playerNum);
		teamPieces[4] = new damageMage(playerNum);
	}
}

class Team3 extends Team{
	Team3(int playerNum){//team 3 is a fast team that focuses on mobility
		teamPieces[0] = new ranger(playerNum);
		teamPieces[1] = new rangerClone(playerNum);//clone is created if this team already has a piece of this type
		teamPieces[2] = new rogue(playerNum);
		teamPieces[3] = new rogueClone(playerNum);
		teamPieces[4] = new healer(playerNum);
	}
}

class Team4 extends Team{
	Team4(int playerNum){//team 4 is a team that focuses around dealing damage
		teamPieces[0] = new warrior(playerNum);
		teamPieces[1] = new warriorClone(playerNum);
		teamPieces[2] = new damageMage(playerNum);
		teamPieces[3] = new damageMageClone(playerNum);
		teamPieces[4] = new healer(playerNum);
	}
}