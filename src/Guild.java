import java.util.ArrayList;

import ReadWriteFile.PGPFile;

public class Guild {
	static int playno;
	static int capital;
	static int missionComplete;
	static ArrayList<Player> playerList;
	static ArrayList<Enemy> enemylist;
	static ArrayList<Dungeon> dungeonlist;
	
	static void initialise() {
		playno = 1;
		capital = 100;
		missionComplete = 0;
		playerList = new ArrayList<Player>();
		dungeonlist = new ArrayList<Dungeon>();
		enemylist = new ArrayList<Enemy>();
	}
	
	static void save() {
		PGPFile file = new PGPFile();
		file.openWriteFile("Guild.txt");
		file.writeLine(playno + " " + capital + " " + missionComplete);
		file.closeWriteFile();	
		
		file.openWriteFile("Dungeon.txt");
		for (Dungeon dun : dungeonlist)
		{
			dun.save(file);
		}
		
		file.closeWriteFile();
		
		file.openWriteFile("Enemy.txt");
		for (Enemy ene: enemylist)
		{
			ene.save(file);
		}
		
		file.closeWriteFile();	
	}
	
	static void load() {
		PGPFile file = new PGPFile();
		file.openReadFile("Guild.txt");
		String line = file.readNextLine();
		String[] words = line.split(" ");
		playno = Integer.valueOf(words[0]);
		capital = Integer.valueOf(words[1]);
		missionComplete = Integer.valueOf(words[2]);
		playerList = new ArrayList<Player>();
		
		file.closeReadFile();
		
		for (int i=1; i<playno+1; i++) {
			Player newPlayer = new Player();
			newPlayer.read("Player" + i + ".txt");
			playerList.add(newPlayer);
		}
		
		dungeonlist = new ArrayList<Dungeon>();
		file.openReadFile("Dungeon.txt");
		while((line = file.readNextLine()) != null)
		{
			Dungeon dun = new Dungeon();
			dun.load(file,line);
			dungeonlist.add(dun);
		}
		file.closeReadFile();
		
		file.openReadFile("Enemy.txt");
		enemylist = new ArrayList<Enemy>();
		while((line = file.readNextLine()) != null)
		{
			Enemy ene = new Enemy();
			ene.load(file,line);
			enemylist.add(ene);
		}
		file.closeReadFile();
		
	}
	
	static void printAllPlayer() {
		for (Player p : playerList)
			p.printStat();
	}
	
	static void printAllDungeon() {
		for (Dungeon d : dungeonlist)
			d.printInfo();
	}

	static void printAllEnemy() {
		for (Enemy e : enemylist)
			e.printInfo();
	
}
}
