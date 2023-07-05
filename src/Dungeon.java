import java.util.ArrayList;
import java.util.Random;

import ReadWriteFile.PGPFile;

public class Dungeon {
	int rank;
	int chaos;
	int terri;
	Random rand = new Random();
	int encounterRate;
	int depth;
	int ele;
	int tag;
	ArrayList<Integer> enemyList;
	
	public void createDungeon(int rank) {
		
		this.rank = rank;
		chaos = rand.nextInt(1,100);
		
		if (chaos >30)
			encounterRate = chaos;
		else
			encounterRate = 30;
		if (chaos > 50 - 10*rank)
			ele = rand.nextInt(1,15);
		else 
			ele = 0;
		
		terri = rand.nextInt(1, 10 + rank*10);
		if (terri<21)
			depth = 5;
		else if (terri < 41)
			depth = 8;
		else if (terri < 61)
			depth = 12;
		else if (terri <81)
			depth = 15;
		else 
			depth = 20;
		
		tag = Guild.dungeonlist.size()+1; 
		
		enemyList = new ArrayList<Integer> ();
		
		Guild.dungeonlist.add(this);
	}
	
	public void printInfo() {
		System.out.println("Dungeon "+tag + " Rank: " + rank);
		System.out.println("Element: "+ Element.readele(ele));
		System.out.println("Chaos Value: " + chaos + " Encounter Rate: " + encounterRate);
		System.out.println("Terri Value: " + terri + " Depth: " + depth);
		
	}
	
	public void save (PGPFile file) {
		file.writeLine(tag + " " + ele + " " + rank);
		file.writeLine(chaos + " " + encounterRate + " " + terri + " " + depth);
		StringBuilder builder = new StringBuilder();
		for (int i: enemyList)
			builder.append(i + " ");
		file.writeLine(" ");
	}	
	
	public void load (PGPFile file, String line) {
		String [] words = line.split(" ");
		tag = Integer.valueOf(words[0]);
		ele = Integer.valueOf(words[1]);
		rank = Integer.valueOf(words[2]);
		line = file.readNextLine();
		words = line.split(" ");
		chaos = Integer.valueOf(words[0]);
		encounterRate = Integer.valueOf(words[1]);
		terri = Integer.valueOf(words[2]);
		depth = Integer.valueOf(words[3]);	
		enemyList = new ArrayList<Integer> ();
		line = file.readNextLine();
		words = line.split(" ");
		for (String word: words)
			enemyList.add(Integer.valueOf(word));
	}
	
	
}
