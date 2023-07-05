
import java.util.ArrayList;
import java.util.Random;

import ReadWriteFile.PGPFile;

public class Enemy extends Creature {
	int element;
	ArrayList<Integer> weakness;
	Random rand = new Random();
	// grade used to decide the stat. of enemy 
	int grade;
	
	public void createEnemy(Dungeon d, int gra) {
		
		lev = 0;
		grade = gra;
		
		if (d.ele !=0)
			element = rand.nextInt(1,15);
		else 
		{
			int dice = rand.nextInt(1,20);
			if (dice <=15)
				element = dice;
			else
				element = d.ele;
		}
		
		weakness = new ArrayList<Integer>();
		for (int i = 1; i <16; i++)
		{
			int dice = rand.nextInt(1,100);
			if (dice <=30)
			{
				if (i!=element)
					weakness.add(i);
			}
		}
		
		rank = d.rank;
		status = new ArrayList<Integer>();
		
		int dice = rand.nextInt(1,6);
		if (gra == 1)
			{
				for (int i=0; i<6; i++)
					status.add(rand.nextInt(1,6));
				
				status.set(dice, status.get(dice)+rand.nextInt(1,3));
			}
		
		name = "Enemy-" + Guild.enemylist.size() + "-" + Element.readele(element)+"-"+dice ;
		d.enemyList.add(Guild.enemylist.size());
		Guild.enemylist.add(this);
		
	}
	public void printInfo() {
		System.out.println("\nEnemy Name: "+ name + " Rank: " + rank);
		System.out.println("Element: "+ Element.readele(element));
		StringBuilder builder = new StringBuilder();
		for (int weak:weakness)
		{
			builder.append(Element.readele(weak)+ " ");
		}	
		System.out.println("Weakness: " + builder.toString());
		System.out.println("Strength: " + status.get(0));
		System.out.println("Constituton: " + status.get(1));
		System.out.println("Dexterity: " + status.get(2));
		System.out.println("Intelligence: " + status.get(3));
		System.out.println("Mana: " + status.get(4));
		System.out.println("Perception: " + status.get(5));
	}
	
	public void createRealEnemy() {
		int  key = 10;
		if (grade == 1)
			key = 7;
		else if (grade == 2)
			key = 8;
		else if (grade == 3)
			key = 9;
		else 
			key = 10;
			
		for (int i=0;i<6;i++)
			status.set(i, (int) Math.floor(status.get(i)*lev*key/10));
		
	}
	
	
	public void save(PGPFile file) {
		file.writeLine(name + " "+ grade + " " + rank + " " + element);
		
		StringBuilder builder = new StringBuilder();
		for (int weak: weakness)
			builder.append(weak + " ");
		file.writeLine(builder.toString()+ " ");
		
		builder = new StringBuilder();
		for (int stat:status)
			builder.append(stat + " ");
		file.writeLine(builder.toString());
	}
	
	public void load (PGPFile file, String line) {
		String[] words = line.split(" ");
		name = words[0];
		grade = Integer.valueOf(words[1]);
		rank = Integer.valueOf(words[2]);
		element = Integer.valueOf(words[3]);
		line = file.readNextLine();
		words = line.split(" ");
		weakness = new ArrayList<Integer>();
		for (String word: words)
			weakness.add(Integer.valueOf(word));
		line = file.readNextLine();
		words = line.split(" ");
		for (String word: words)
			status.add(Integer.valueOf(word));
		
	}
	
}


