
public abstract class Team {//generic class for a team of pieces 
	Piece[] teamPieces = new Piece[5];//each team is an array of pieces 
}

class Team1 extends Team{
	Team1() {//team 1 is the basic team having one of each piece
		teamPieces[0] = new warrior();
		teamPieces[1] = new ranger();
		teamPieces[2] = new rogue();
		teamPieces[3] = new healer();
		teamPieces[4] = new damageMage();
	}
}

class Team2 extends Team{
	Team2(){//team 2 is a reckless team, having no healer, but 2 warriors
		teamPieces[0] = new warrior();
		teamPieces[1] = new warrior();
		teamPieces[2] = new ranger();
		teamPieces[3] = new rogue();
		teamPieces[4] = new damageMage();
	}
}

class Team3 extends Team{
	Team3(){//team 3 is a fast team that focuses on mobility
		teamPieces[0] = new ranger();
		teamPieces[1] = new ranger();
		teamPieces[2] = new rogue();
		teamPieces[3] = new rogue();
		teamPieces[4] = new healer();
	}
}

class Team4 extends Team{
	Team4(){//team 4 is a team that focuses around dealing damage
		teamPieces[0] = new warrior();
		teamPieces[1] = new warrior();
		teamPieces[2] = new damageMage();
		teamPieces[3] = new damageMage();
		teamPieces[4] = new healer();
	}
}