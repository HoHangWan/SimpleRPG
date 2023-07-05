import ReadWriteFile.*;

public class MyProgram {

	public static void main(String[] args) {
		System.out.println("Start of the Program:");
		
		/*
		player1.newPlayer();
		player1.printStat();
		player1.save();
		*/
		
		/*
		Guild.initialise();
		Guild.save();
		*/
		/*Guild.load();
		Guild.printAllStatus();
		*/
		Guild.load();
		Guild.printAllPlayer();
		Enemy newE = new Enemy();
		newE.createEnemy(Guild.dungeonlist.get(0), 1);
		newE.printInfo();
		Guild.playerList.get(0).levelUp();
		Journey jour = new Journey(Guild.dungeonlist.get(0));
		Battle testbattle = new Battle();
		testbattle.start(newE, 1, jour);
		
		//Guild.save();
		
		
	}

}
